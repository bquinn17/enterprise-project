package io.swagger.api;

import io.swagger.model.*;
import io.swagger.repository.*;
import io.swagger.annotations.*;

import io.swagger.repository.WholesaleAccountRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-30T18:00:05.067Z")

@RestController
public class OrdersApiController implements OrdersApi {

    @Autowired
    RetailOrderRepository retailOrderRepository;

    @Autowired
    WholesaleOrderRepository wholesaleOrderRepository;

    @Autowired
    WholesaleAccountRepository wholesaleAccountRepository;

    @Autowired
    ModelCountRepository modelCountRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    ProductRepository productRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public static final String ACCCOUNTING_ENDPOINT = "http://127.0.0.1:8080";
    public static final String INVENTORY_ENDPOINT = "https://inventory343.azurewebsites.net";


    public void setRetailOrderRepository(RetailOrderRepository retailOrderRepository){this.retailOrderRepository = retailOrderRepository;}
    public void setWholesaleOrderRepository(WholesaleOrderRepository wholesaleOrderRepository){this.wholesaleOrderRepository = wholesaleOrderRepository;}
    public void setProductRepository(ProductRepository productRepository){this.productRepository = productRepository;}
    public void setRestTemplate(RestTemplate restTemplate){this.restTemplate = restTemplate;}

    @CrossOrigin
    @RequestMapping(method={RequestMethod.POST},value={"/orders/retail/new"})
    public ResponseEntity<RetailOrder> addRetailOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {

        // Check to see if any fields are empty
        if(body.getCustomerEmail().isEmpty() || body.getCustomerShippingState().isEmpty() ||
                body.getCustomerShippingStreetAddress().isEmpty() ||
                body.getCustomerShippingTown().isEmpty() ||
                body.getCustomerShippingZip().isEmpty() ||
                body.getProducts().isEmpty()){
            return new ResponseEntity<RetailOrder>(body, HttpStatus.BAD_REQUEST);
        }

        // Create the Retail Order object with the info from body
        body.setStatus(RetailOrder.StatusEnum.FULFILLED);

        String uri = INVENTORY_ENDPOINT + "/api/Products/order/";

        for (Product product: body.getProducts()) {

            product.setRetailOrder(body);

            String productName = null;
            try {
                productName = URLEncoder.encode(product.getModel(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return new ResponseEntity<RetailOrder>(body, HttpStatus.BAD_REQUEST);
            }

            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            String serialNumber;
            try {
                serialNumber = restTemplate.postForObject(uri + productName + "/" + "1", entity, String.class);
            } catch (Exception ex) {
                return new ResponseEntity<RetailOrder>(body, HttpStatus.FAILED_DEPENDENCY);
            }

            product.setSerialNumber(serialNumber);
            product.setPriceSoldAt(product.getPriceSoldAt());

            productRepository.save(product); // This also saves the RetailOrder.
        }

        uri = ACCCOUNTING_ENDPOINT + "/accounting/retailOrder";

        try {
            RetailOrder accountingResponse = restTemplate.postForObject(uri, body, RetailOrder.class);
        } catch (Exception ex){
            for (Product product: body.getProducts()) {
                productRepository.delete(product);
            }
            retailOrderRepository.delete(body);
            // rollback endpoint call
            return new ResponseEntity<RetailOrder>(body, HttpStatus.FAILED_DEPENDENCY);
        }

        // Dont need this because product.save saves the retailorder
        // retailOrderRepository.save(body);

        // Return status code
        return new ResponseEntity<RetailOrder>(body, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.POST},value={"/orders/wholesale/new"})
    public ResponseEntity<WholesaleOrder> addWholesaleOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody WholesaleOrder body) {

        // Check to see if any fields are empty
        if(body.getSalesRep() == null || body.getOrderMap().isEmpty() ||
                body.getWholesaleAccount() == null ||
                body.getTotalPrice() == 0.0){
            return new ResponseEntity<WholesaleOrder>(body, HttpStatus.BAD_REQUEST);
        }

        body.setStatus(WholesaleOrder.StatusEnum.PLACED);

        // Create SalesRep associated with this wholesale, save into db
        if(body.getSalesRep().getFirstName().isEmpty() ||
            body.getSalesRep().getLastName().isEmpty() ||
            body.getSalesRep().getRegion() == null ||
            body.getSalesRep().getEmployeeId() == 0) {
            return new ResponseEntity<WholesaleOrder>(body, HttpStatus.BAD_REQUEST);
        }

        String uri = INVENTORY_ENDPOINT + "/api/Products/order/";

        try {
            for (ModelCount modelCount: body.getOrderMap()){
                restTemplate.postForObject(uri + modelCount.getModel() + "/" + modelCount.getQuantity(), "", String.class);
            }
        } catch (Exception ex) {
            return new ResponseEntity<WholesaleOrder>(body, HttpStatus.FAILED_DEPENDENCY);
        }

        uri = ACCCOUNTING_ENDPOINT + "/accounting/wholesaleOrder";
        try {
            WholesaleOrder accountingResponse = restTemplate.postForObject(uri, body, WholesaleOrder.class);
        } catch (Exception ex){
            // rollback endpoint call
            return new ResponseEntity<WholesaleOrder>(body, HttpStatus.FAILED_DEPENDENCY);
        }

        salesRepRepository.save(body.getSalesRep());

        wholesaleOrderRepository.save(body);

        for (ModelCount modelCount: body.getOrderMap()) {
            modelCount.setOrder_id(body.getId());
            modelCountRepository.save(modelCount);
        }

        return new ResponseEntity<WholesaleOrder>(body, HttpStatus.CREATED);
    }

    @CrossOrigin
    public ResponseEntity<RetailOrder> changeOrderStatus(@NotNull @ApiParam(value = "ID of the order", required = true) @Valid @RequestParam(value = "id", required = true) String id, @NotNull @ApiParam(value = "New status of the order", required = true) @Valid @RequestParam(value = "status", required = true) String status) {

        RetailOrder retailOrder;
        RetailOrder.StatusEnum statusEnum = RetailOrder.StatusEnum.fromValue(status);
        Long longId = new Long(id);
        retailOrder = retailOrderRepository.findOne(longId);
        if(retailOrder == null) {
            return new ResponseEntity<RetailOrder>(HttpStatus.NOT_FOUND);
        }

        if(statusEnum == null) {
            return new ResponseEntity<RetailOrder>(HttpStatus.NOT_MODIFIED);
        }

        retailOrder.setStatus(statusEnum);
        retailOrderRepository.save(retailOrder);

        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.GET},value={"/orders/completed"})
    public ResponseEntity<RetailOrder> getOrder( @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "serial_num", required = true) String serialNum) throws NotFoundException {

        List<RetailOrder> retailOrders = retailOrderRepository.findAll();
        for(RetailOrder retailOrder : retailOrders) {
            for(Product product : retailOrder.getProducts()){
                if(product.getSerialNumber().equals(serialNum)){
                    return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<RetailOrder>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.GET},value={"/orders/byrep"})
    public ResponseEntity<List<WholesaleOrder>> getOrdersByRep(@NotNull@ApiParam(value = "", required = true) @RequestParam(value = "sales_rep_id", required = true) String salesRepId) throws NotFoundException {
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findBySalesRepEmployeeId(Long.valueOf(salesRepId));

        if (wholesaleOrders.size() != 0){
            return new ResponseEntity<List<WholesaleOrder>>(wholesaleOrders, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<WholesaleOrder>>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "", nickname = "getAllOrders", notes = "Endpoint to receive all orders. This is used by the inventory silo for querying our orders, finding out which ones they need to fufill, and then they fufill and update orders")
    @RequestMapping(method={RequestMethod.GET},value={"/orders/all"})
    public ResponseEntity<List<BasicOrder>> getAllOrders() {

        List<RetailOrder> retailOrders = retailOrderRepository.findAll();
        ArrayList<BasicOrder> orders = new ArrayList<>();
        for (RetailOrder retailOrder: retailOrders) {
            BasicOrder basicOrder = new BasicOrder();
            basicOrder.setID(retailOrder.getID());
            basicOrder.setStatus(retailOrder.getStatus());
            basicOrder.setProducts(retailOrder.getProducts());
            orders.add(basicOrder);
        }

        return new ResponseEntity<List<BasicOrder>>(orders, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.POST},value={"/orders/new/refund"})
    public ResponseEntity<RetailOrder> zeroDollarOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {
        //When Pricing is added to the RetailOrder model then it will check for $0 in the pricing
        if(body.getTotalPrice() != 0.0) {
            return new ResponseEntity<RetailOrder>(HttpStatus.BAD_REQUEST);
        }

        return addRetailOrder(body);
    }

}
