package io.swagger.api;

import io.swagger.model.ConfiguredPrice;
import io.swagger.model.SalesRep;
import io.swagger.model.WholesaleAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.ConfiguredPriceRepository;
import io.swagger.repository.SalesRepRepository;
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
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-30T18:00:05.067Z")

@Controller
public class WholesaleApiController implements WholesaleApi {

    private static final Logger log = LoggerFactory.getLogger(WholesaleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    WholesaleOrderRepository wholesaleOrderRepository;

    @Autowired
    WholesaleAccountRepository wholesaleAccountRepository;

    @Autowired
    ConfiguredPriceRepository configuredPriceRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public WholesaleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @CrossOrigin
    public ResponseEntity<WholesaleAccount> addWholesaleAccount(@ApiParam(value = "Add a new wholesale account to the sale system" ,required=true )  @Valid @RequestBody WholesaleAccount body) {
        String accept = request.getHeader("Accept");
        // Set all fields
        WholesaleAccount wholesaleAccount = new WholesaleAccount();
        wholesaleAccount.setName(body.getName());
        wholesaleAccount.setEmail(body.getEmail());
        wholesaleAccount.setShippingAddress(body.getShippingAddress());
        wholesaleAccount.setShippingState(body.getShippingState());
        wholesaleAccount.setShippingTown(body.getShippingTown());
        wholesaleAccount.setShippingZip(body.getShippingZip());

        // Save into database
        wholesaleAccountRepository.save(wholesaleAccount);

        for (ConfiguredPrice configuredPrice: body.getConfiguredPrice()) {
            configuredPrice.setAccountId(wholesaleAccount.getId());
            wholesaleAccount.addConfiguredPriceItem(configuredPrice);
            configuredPriceRepository.save(configuredPrice);
        }

        return new ResponseEntity<WholesaleAccount>(wholesaleAccount, HttpStatus.CREATED);
    }

    @CrossOrigin
    public ResponseEntity<List> getWholesaleAccounts() {
        String accept = request.getHeader("Accept");

        // Get all the wholesale accounts in the database.
        List<WholesaleAccount> wholesaleAccounts = wholesaleAccountRepository.findAll();
        List<WholesaleAccount> response = new ArrayList<>();
        for(WholesaleAccount wholesaleAccount : wholesaleAccounts){
            List<ConfiguredPrice> configuredPricesList = configuredPriceRepository.findAll();
            for(ConfiguredPrice configuredPrice : configuredPricesList){
                if(configuredPrice.getAccountId() == wholesaleAccount.getId()){
                    wholesaleAccount.addConfiguredPriceItem(configuredPrice);

                }
            }
            response.add(wholesaleAccount);
        }

        return new ResponseEntity<List>(response, HttpStatus.FOUND);
    }

}
