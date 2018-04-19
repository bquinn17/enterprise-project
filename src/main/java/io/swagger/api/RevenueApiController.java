package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.WholesaleOrder;
import io.swagger.repository.WholesaleAccountRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-19T15:57:43.975Z")

@Controller
public class RevenueApiController implements RevenueApi {

    private static final Logger log = LoggerFactory.getLogger(RevenueApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private WholesaleOrderRepository wholesaleOrderRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public RevenueApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @CrossOrigin
    public ResponseEntity<Double> getRevenueForSalesRep(@PathVariable(value = "the sales rep's ID") String sales_rep_id,
                                                        @NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "date_from", required = false) String dateFrom,
                                                        @NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "date_to", required = false) String dateTo) {
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findBySalesRepEmployeeId(Long.parseLong(sales_rep_id));
        double revenue = 0.0;

        for(WholesaleOrder order : wholesaleOrders) {

            revenue += order.getTotalPrice();
        }
        return null;
    }

    public ResponseEntity<Void> getRevenueFromRegion(@ApiParam(value = "",required=true) @PathVariable("region") String region) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> getTotalRevenue(@ApiParam(value = "") @Valid @RequestParam(value = "date_from", required = false) String dateFrom,@ApiParam(value = "") @Valid @RequestParam(value = "date_to", required = false) String dateTo) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
