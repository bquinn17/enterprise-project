package io.swagger.api;

import io.swagger.model.*;
import io.swagger.repository.*;
import io.swagger.annotations.*;

import io.swagger.repository.WholesaleAccountRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.ArrayList;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-03T19:46:44.474Z")

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

    @CrossOrigin(origins = "http://localhost:3000")
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
        retailOrder.setStatus(RetailOrder.StatusEnum.FULLFILLED);
        retailOrder.setProducts(body.getProducts());

        // Save Object into database
        retailOrderRepository.save(retailOrder);

        // Return status code
        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<WholesaleOrder> addWholesaleOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody WholesaleOrder body) {
        // do some magic!
        /*
        {
          "orderMap": [
            {
              "model": "string",
              "quantity": 0
            }
          ],
          "status": "placed",
          "wholesaleAccount": {
            "email": "string",
            "salesRep": {
              "firstName": "string",
              "lastName": "string",
              "region": "north"
            },
            "shippingAddress": "string",
            "shippingState": "string",
            "shippingTown": "string",
            "shippingZip": "string"
          }
        }
         */
        WholesaleOrder order = new WholesaleOrder();

        // order.status(body.getStatus() != null ? body.getStatus() : WholesaleOrder.StatusEnum.PLACED);
        order.setStatus(WholesaleOrder.StatusEnum.PLACED);

        order.setWholesaleAccount(body.getWholesaleAccount()); // need to find a way to identify WholesaleAccountRepository.findOne();

        order.setOrderMap(body.getOrderMap());

        wholesaleOrderRepository.save(order);

        for (ModelCount modelCount: body.getOrderMap()) {
            modelCount.setOrder_id(order.getId());
            modelCountRepository.save(modelCount);
        }

        return new ResponseEntity<WholesaleOrder>(order, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> changeOrderStatus(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<RetailOrder> getOrder( @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "serial_num", required = true) String serialNum) {
        Product product = new Product();
        product.setModel("KennUWare Watch");
        product.setRefurbished(false);
        product.setSerialNumber("123456789");
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);

        RetailOrder retailOrder = new RetailOrder();
        retailOrder.setCustomerEmail("goodguy@gmail.com");
        retailOrder.setCustomerShippingState("NY");
        retailOrder.setCustomerShippingStreetAddress("1 Lomb Memorial Dr");
        retailOrder.setCustomerShippingTown("Rochester");
        retailOrder.setCustomerShippingZip("14623");
        retailOrder.setStatus(RetailOrder.StatusEnum.FULLFILLED);
        retailOrder.setProducts(productList);
        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.FOUND);
    }

    public ResponseEntity<SalesRep> getSalesRep( @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "sales_rep_id", required = true) String salesRepId,
         @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "date_from", required = true) String dateFrom,
         @NotNull@ApiParam(value = "", required = true) @RequestParam(value = "date_to", required = true) String dateTo) {

        SalesRep rep = new SalesRep();
        rep.setFirstName("Selly");
        rep.setLastName("McSellsit");
        rep.setRegion(SalesRep.RegionEnum.EAST);
        return new ResponseEntity<SalesRep>(rep, HttpStatus.FOUND);
    }

    public ResponseEntity<Void> zeroDollarOrder(@ApiParam(value = "Retail order object that needs to be added to the Sales System" ,required=true )  @Valid @RequestBody RetailOrder body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
