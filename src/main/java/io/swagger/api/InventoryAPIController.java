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
public class InventoryAPIController {
    @RequestMapping(value = "/mocked/inventory/products",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<MockedModel>> getSalesRepByRegion() {
        List<MockedModel> response = new ArrayList<>();
        response.add(new MockedModel("Kenn-U-Style", 100, ""));
        response.add(new MockedModel("Kenn-U-Flex", 200, ""));
        response.add(new MockedModel("Kenn-U-Active", 150, ""));
        return new ResponseEntity<List<MockedModel>>(response, HttpStatus.FOUND);
    }
}
