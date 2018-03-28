package io.swagger.api;

import io.swagger.model.WholesaleAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.WholesaleAccountRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-28T15:47:58.543Z")

@Controller
public class WholesaleApiController implements WholesaleApi {

    private static final Logger log = LoggerFactory.getLogger(WholesaleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    WholesaleOrderRepository wholesaleOrderRepository;

    @Autowired
    WholesaleAccountRepository wholesaleAccountRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public WholesaleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @CrossOrigin
    public ResponseEntity<WholesaleAccount> addWholesaleAccount(@ApiParam(value = "Add a new wholesale account to the sale system" ,required=true )  @Valid @RequestBody WholesaleAccount body) {
        String accept = request.getHeader("Accept");
        WholesaleAccount wholesaleAccount = new WholesaleAccount();
        return new ResponseEntity<WholesaleAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

    @CrossOrigin
    public ResponseEntity<List> getWholesaleAccounts() {
        String accept = request.getHeader("Accept");

        // Get all the wholesale accounts in the database.
        List wholesaleAccounts = wholesaleAccountRepository.findAll();

        return new ResponseEntity<List>(wholesaleAccounts, HttpStatus.FOUND);
    }

}
