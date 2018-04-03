package io.swagger.api;

import io.swagger.annotations.*;

import io.swagger.model.RetailOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;


import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-30T18:00:05.067Z")

@RestController
public class MockedEndpointsController {

    @ApiOperation(value = "", nickname = "getSerialNumber", notes = "Get a unique serial number that represents a device", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found") })
    @RequestMapping(value = "/inventory/getDeviceId",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<String> getSerialNumber(@ApiParam(value = "The name of a product that the inventory should have on hand (eg Kenn-U-Flex)." ,required=true )  @Valid @RequestBody String modelName) {

        return new ResponseEntity<String>(UUID.randomUUID().toString(), HttpStatus.FOUND);
    }

    @ApiOperation(value = "", nickname = "getEmployeedIds", notes = "Get a unique ID for the ", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found") })
    @RequestMapping(value = "/hr/getEmployeeIds",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<Long> getEmployeedId(@ApiParam(value = "The name of a product that the inventory should have on hand (eg Kenn-U-Flex)." ,required=true )  @Valid @RequestBody String modelName) {

        Random r = new Random();
        return new ResponseEntity<Long>(r.nextLong(), HttpStatus.FOUND);
    }

    @ApiOperation(value = "", nickname = "Order created", notes = "Send the information about a newly created order", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created") })
    @RequestMapping(value = "/accounting/retailOrder",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<RetailOrder> retailOrder(@ApiParam(value = "The json object representation of the order." ,required=true )  @Valid @RequestBody RetailOrder retailOrder) {

        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.CREATED);
    }

    @ApiOperation(value = "", nickname = "Order created", notes = "Send the information about a newly created order", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created") })
    @RequestMapping(value = "/inventory/wholesaleOrder",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<RetailOrder> wholesaleOrderInventory(@ApiParam(value = "The json object representation of the order." ,required=true )  @Valid @RequestBody RetailOrder retailOrder) {

        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.CREATED);
    }

    @ApiOperation(value = "", nickname = "Order created", notes = "Send the information about a newly created order", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created") })
    @RequestMapping(value = "/accounting/wholesaleOrder",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<RetailOrder> wholesaleOrderAccounting(@ApiParam(value = "The json object representation of the order." ,required=true )  @Valid @RequestBody RetailOrder retailOrder) {

        return new ResponseEntity<RetailOrder>(retailOrder, HttpStatus.CREATED);
    }

}
