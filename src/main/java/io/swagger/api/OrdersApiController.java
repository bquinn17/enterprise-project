package io.swagger.api;

import io.swagger.model.RetailOrder;
import io.swagger.model.WholesaleOrder;
import io.swagger.repository.*;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-03T19:46:44.474Z")

@Controller
public class OrdersApiController implements OrdersApi {

    @Autowired
    RetailOrderRepository retailOrderRepository;


    public ResponseEntity<Void> addRetailOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {

        // Create the Retail Order object with the info from body
        RetailOrder retailOrder = new RetailOrder();
        retailOrder.setCustomerEmail(body.getCustomerEmail());
        retailOrder.setCustomerShippingState(body.getCustomerShippingState());
        retailOrder.setCustomerShippingStreetAddress(body.getCustomerShippingStreetAddress());
        retailOrder.setCustomerShippingState(body.getCustomerShippingState());
        retailOrder.setCustomerShippingTown(body.getCustomerShippingTown());
        retailOrder.setCustomerShippingZip(body.getCustomerShippingZip());
        retailOrder.setStatus(RetailOrder.StatusEnum.FULLFILLED);
        retailOrder.setProducts(body.getProducts());

        // Save Object into database
        retailOrderRepository.save(retailOrder);

        // Return status code
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> addWholesaleOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody WholesaleOrder body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> changeOrderStatus(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {
        //Currently just return that everything is okay
        //Plans to implement an actual order update
        //Will require further/more precise definition of update
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> getOrder( @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "serial_num", required = true) String serialNum) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> getSalesRep( @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "sales_rep_id", required = true) String salesRepId,
         @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "date_from", required = true) String dateFrom,
         @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "date_to", required = true) String dateTo) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> zeroDollarOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {
        // Create the Retail Order object with the info from body
        RetailOrder retailOrder = new RetailOrder();
        retailOrder.setCustomerEmail(body.getCustomerEmail());
        retailOrder.setCustomerShippingState(body.getCustomerShippingState());
        retailOrder.setCustomerShippingStreetAddress(body.getCustomerShippingStreetAddress());
        retailOrder.setCustomerShippingState(body.getCustomerShippingState());
        retailOrder.setCustomerShippingTown(body.getCustomerShippingTown());
        retailOrder.setCustomerShippingZip(body.getCustomerShippingZip());
        retailOrder.setStatus(RetailOrder.StatusEnum.FULLFILLED);
        retailOrder.setProducts(body.getProducts());

        // Save Object into database
        retailOrderRepository.save(retailOrder);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
