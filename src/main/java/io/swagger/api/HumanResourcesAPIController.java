package io.swagger.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import io.swagger.model.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HumanResourcesAPIController {
    @RequestMapping(value = "/mocked/hr/salesreps",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<MockedSalesRepList> getSalesRepByRegion(@RequestParam(value = "region", required = true) String region) {
        List<MockedSalesRep> response = new ArrayList<>();
        switch(region){
            case "North":
                response.add(new MockedSalesRep(1, "James", "Bond", "North"));
                response.add(new MockedSalesRep(2, "Kenn", "Martinez", "North"));
                response.add(new MockedSalesRep(3, "Matt", "Damon", "North"));
                break;

            case "South":
                response.add(new MockedSalesRep(4, "Sandra", "Bullock", "South"));
                response.add(new MockedSalesRep(5, "Jennifer", "Lawrence", "South"));
                response.add(new MockedSalesRep(6, "Matthew", "McConaughey", "South"));
                break;

            case "East":
                response.add(new MockedSalesRep(7, "Chuck", "Norris", "East"));
                response.add(new MockedSalesRep(8, "Carrie", "Underwood", "East"));
                response.add(new MockedSalesRep(9, "Logan", "Paul", "East"));
                break;

            case "West":
                response.add(new MockedSalesRep(10, "Jon", "Stewart", "West"));
                response.add(new MockedSalesRep(11, "Stephen", "Colbert", "West"));
                response.add(new MockedSalesRep(12, "Samantha", "Bee", "West"));
                break;
        }
        MockedSalesRepList listResponse = new MockedSalesRepList(response);
        return new ResponseEntity<MockedSalesRepList>(listResponse, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/mocked/hr/login",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("Authenticated", HttpStatus.CREATED);
    }
}
