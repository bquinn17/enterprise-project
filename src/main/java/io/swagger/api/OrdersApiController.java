package io.swagger.api;

import io.swagger.model.*;
import io.swagger.repository.*;
import io.swagger.annotations.*;

import io.swagger.repository.WholesaleAccountRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

        String uri = "http://127.0.0.1:8080/inventory/getDeviceId";

        for (Product product: body.getProducts()) {

            product.setRetailOrder(body);

            String productName = product.getModel();

            RestTemplate restTemplate = new RestTemplate();
            String serialNumber;
            try {
                serialNumber = restTemplate.postForObject(uri, productName, String.class);
            } catch (Exception ex) {
                return new ResponseEntity<RetailOrder>(body, HttpStatus.FAILED_DEPENDENCY);
            }

            product.setSerialNumber(serialNumber);
            product.setPriceSoldAt(product.getPriceSoldAt());

            productRepository.save(product); // This also saves the RetailOrder.
        }

        uri = "http://127.0.0.1:8080/accounting/retailOrder";
        RestTemplate restTemplate = new RestTemplate();

        try {
            RetailOrder accountingResponse = restTemplate.postForObject(uri, body, RetailOrder.class);
        } catch (Exception ex){
            for (Product product: body.getProducts()) {
                productRepository.delete(product);
            }
            retailOrderRepository.delete(body);
            // TODO rollback endpoint call
            return new ResponseEntity<RetailOrder>(body, HttpStatus.FAILED_DEPENDENCY);
        }

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

        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://127.0.0.1:8080/inventory/wholesaleOrder";

        try {
            WholesaleOrder inventoryResponse = restTemplate.postForObject(uri, body, WholesaleOrder.class);
        } catch (Exception ex) {
            return new ResponseEntity<WholesaleOrder>(body, HttpStatus.FAILED_DEPENDENCY);
        }

        uri = "http://127.0.0.1:8080/accounting/wholesaleOrder";
        try {
            WholesaleOrder accountingResponse = restTemplate.postForObject(uri, body, WholesaleOrder.class);
        } catch (Exception ex){
            // TODO rollback endpoint call
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
    @RequestMapping(method={RequestMethod.GET},value={"/orders/update/status"})
    public ResponseEntity<RetailOrder> changeOrderStatus(@ApiParam(value = "ID identifying the Order" ,required=true )  @Valid @RequestBody Long id,
                                                         @ApiParam(value = "Status to change on the Order" ,required=true )  @Valid @RequestBody RetailOrder.StatusEnum status) {

        RetailOrder retailOrder;
        Long intId = Long.valueOf(id);
        RetailOrder.StatusEnum statusEnum = status;
        if(intId.toString().equals(id)) {
            retailOrder = retailOrderRepository.getOne(intId);
        }
        else {
            return new ResponseEntity<RetailOrder>(new RetailOrder(), HttpStatus.BAD_REQUEST);
        }

        if(retailOrder == null) {
            return new ResponseEntity<RetailOrder>(new RetailOrder(), HttpStatus.NOT_FOUND);
        }

        if(statusEnum == null) {
            return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.NOT_MODIFIED);
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
                    return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.FOUND);
                }
            }
        }
        return new ResponseEntity<RetailOrder>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.GET},value={"/orders/byrep"})
    public ResponseEntity<List<WholesaleOrder>> getOrdersByRep(@NotNull@ApiParam(value = "", required = true) @RequestParam(value = "sales_rep_id", required = true) String salesRepId) throws NotFoundException {
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findBySalesRepEmployeeId(Long.parseLong(salesRepId));

        if (wholesaleOrders.size() != 0){
            return new ResponseEntity<List<WholesaleOrder>>(wholesaleOrders, HttpStatus.FOUND);
        }else {
            throw new NotFoundException(404, "no orders found for sales rep id");
        }
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.GET},value={"/orders"})
    public ResponseEntity<SalesRep> getSalesRep( @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "sales_rep_id", required = true) String salesRepId,
         @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "date_from", required = true) String dateFrom,
         @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "date_to", required = true) String dateTo) {

        SalesRep rep = new SalesRep();
        rep.setFirstName("Selly");
        rep.setLastName("McSellsit");
        rep.setRegion(SalesRep.RegionEnum.EAST);
        return new ResponseEntity<SalesRep>(rep, HttpStatus.FOUND);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.POST},value={"/orders/new/refund"})
    public ResponseEntity<RetailOrder> zeroDollarOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {
        //When Pricing is added to the RetailOrder model then it will check for $0 in the pricing
        if(body.getTotalPrice() == null || !body.getTotalPrice().equals(0.0)) {
            return new ResponseEntity<RetailOrder>(HttpStatus.BAD_REQUEST);
        }

        return addRetailOrder(body);
    }

}
