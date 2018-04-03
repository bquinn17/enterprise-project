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

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-30T18:00:05.067Z")

@RestController
@Controller
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

        // Check if the order contains at least one product.
        if(body.getProducts().isEmpty()){
            return new ResponseEntity<RetailOrder>(body, HttpStatus.BAD_REQUEST);
        }

        // Check to see if any fields are empty
        if(body.getCustomerEmail().isEmpty() || body.getCustomerShippingState().isEmpty() ||
                body.getCustomerShippingStreetAddress().isEmpty() ||
                body.getCustomerShippingTown().isEmpty() ||
                body.getCustomerShippingZip().isEmpty()){
            return new ResponseEntity<RetailOrder>(body, HttpStatus.BAD_REQUEST);
        }

        // Create the Retail Order object with the info from body
        RetailOrder retailOrder = new RetailOrder();
        retailOrder.setCustomerEmail(body.getCustomerEmail());
        retailOrder.setCustomerShippingState(body.getCustomerShippingState());
        retailOrder.setCustomerShippingStreetAddress(body.getCustomerShippingStreetAddress());
        retailOrder.setCustomerShippingState(body.getCustomerShippingState());
        retailOrder.setCustomerShippingTown(body.getCustomerShippingTown());
        retailOrder.setCustomerShippingZip(body.getCustomerShippingZip());
        retailOrder.setStatus(RetailOrder.StatusEnum.FULFILLED);

        //
        double productPrice = 0.0;
        double totalPrice = 0.0;
        String serialNumber;

        for (Product product: body.getProducts()) {

            product.setRetailOrder(retailOrder);
            serialNumber = "Fudge"; // TODO query Inventory for a serial number. For now it is random
            product.setSerialNumber(serialNumber);

            productPrice = 100.99; // TODO query Inventory for price. For now it is random

            product.setPriceSoldAt(productPrice);
            retailOrder.addProductsItem(product);

            totalPrice += productPrice;
            retailOrder.setTotalPrice(totalPrice);
            productRepository.save(product); // This also saves the RetailOrder.
        }

        // Return status code
        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.POST},value={"/orders/wholesale/new"})
    public ResponseEntity<WholesaleOrder> addWholesaleOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody WholesaleOrder body) {
        WholesaleOrder order = new WholesaleOrder();

        order.setStatus(WholesaleOrder.StatusEnum.PLACED);

        order.setWholesaleAccount(body.getWholesaleAccount()); // need to find a way to identify WholesaleAccountRepository.findOne();

        order.setOrderMap(body.getOrderMap());

        // Create SalesRep associated with this wholesale, save into db
        SalesRep salesRep = new SalesRep();
        salesRep.setFirstName(body.getSalesRep().getFirstName()); // need to find a way to identify SalesRep using repo
        salesRep.setLastName(body.getSalesRep().getLastName());
        salesRep.setRegion(body.getSalesRep().getRegion());
        salesRep.setEmployeeId(body.getSalesRep().getEmployeeId());
        Long employeeID = new Long(123); // TODO query HR for employee ID
        salesRep.setEmployeeId(employeeID);
        salesRepRepository.save(salesRep);

        order.setSalesRep(salesRep);
        wholesaleOrderRepository.save(order);

        for (ModelCount modelCount: body.getOrderMap()) {
            modelCount.setOrder_id(order.getId());
            modelCountRepository.save(modelCount);
        }

        return new ResponseEntity<WholesaleOrder>(order, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.GET},value={"/orders/update/status"})
    public ResponseEntity<RetailOrder> changeOrderStatus(@ApiParam(value = "ID identifying the Order" ,required=true )  @Valid @RequestBody Long id,
                                                  @ApiParam(value = "Status to change on the Order" ,required=true )  @Valid @RequestBody RetailOrder.StatusEnum status) {

        RetailOrder retailOrder = retailOrderRepository.getOne(id);

        if(retailOrder == null) {
            return new ResponseEntity<RetailOrder>(new RetailOrder(), HttpStatus.NOT_FOUND);
        }

        if(status.isEmpty()) {
            return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.NOT_MODIFIED);
        }

        retailOrder.setStatus(status);
        retailOrderRepository.save(retailOrder);

        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.GET},value={"/orders/completed"})
    public ResponseEntity<RetailOrder> getOrder( @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "serial_num", required = true) String serialNum) throws NotFoundException {

        List<RetailOrder> retailOrders = retailOrderRepository.findAll();
        for(RetailOrder ro : retailOrders) {
            for(Product p : ro.getProducts()){
                if(p.getSerialNumber().equals(serialNum)){
                    return new ResponseEntity<RetailOrder>(ro, HttpStatus.FOUND);
                }

            }
        }
        throw new NotFoundException(404, "no orders containing serial number found");
    }

    @CrossOrigin
    @RequestMapping(method={RequestMethod.GET},value={"/orders/byrep"})
    public ResponseEntity<List<WholesaleOrder>> getOrdersByRep(@NotNull@ApiParam(value = "", required = true) @RequestParam(value = "sales_rep_id", required = true) String salesRepId) throws NotFoundException {
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findAll();
        List<WholesaleOrder> response = new ArrayList<>();
        for(WholesaleOrder wo : wholesaleOrders){
            if (wo.getSalesRep().getEmployeeId().toString().equals(salesRepId)){
                response.add(wo);
            }
        }
        if (response.size() != 0){
            return new ResponseEntity<List<WholesaleOrder>>(response, HttpStatus.FOUND);
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
        if(body.getTotalPrice() == null || !body.getTotalPrice().equals(0)) {
            return new ResponseEntity<RetailOrder>(HttpStatus.BAD_REQUEST);
        }

        return addRetailOrder(body);
    }

}
