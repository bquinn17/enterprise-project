package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.RetailOrder;
import io.swagger.model.WholesaleOrder;
import io.swagger.repository.RetailOrderRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-19T15:57:43.975Z")

@Controller
public class RevenueApiController implements RevenueApi {

    private static final Logger log = LoggerFactory.getLogger(RevenueApiController.class);

    private SimpleDateFormat dateFormat;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private WholesaleOrderRepository wholesaleOrderRepository;

    private RetailOrderRepository retailOrderRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public RevenueApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.dateFormat = new SimpleDateFormat("yyyy-dd-MM");
    }

    @CrossOrigin
    public ResponseEntity<Double> getRevenueForSalesRep(@PathVariable(value = "the sales rep's ID") String sales_rep_id,
                                                        @NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "date_from", required = false) String dateFrom,
                                                        @NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "date_to", required = false) String dateTo) {
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findBySalesRepEmployeeId(Long.parseLong(sales_rep_id));
        double revenue = 0.0;

        try{
            Date to = dateFormat.parse(dateTo);
            Date from = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            return new ResponseEntity<Double>(0.0, HttpStatus.BAD_REQUEST);
        }
        for(WholesaleOrder order : wholesaleOrders) {

            revenue += order.getTotalPrice();
        }

        return new ResponseEntity<Double>(revenue, HttpStatus.OK);
    }

    public ResponseEntity<Void> getRevenueFromRegion(@ApiParam(value = "",required=true) @PathVariable("region") String region) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Double> getTotalRevenue(@ApiParam(value = "") @Valid @RequestParam(value = "date_from", required = false) String dateFrom, @ApiParam(value = "") @Valid @RequestParam(value = "date_to", required = false) String dateTo) {
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findAll();
        List<RetailOrder> retailOrders = retailOrderRepository.findAll();

        double revenue = 0.0;

        try{
            Date to = dateFormat.parse(dateTo);
            Date from = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            return new ResponseEntity<Double>(0.0, HttpStatus.BAD_REQUEST);
        }

        for(WholesaleOrder order : wholesaleOrders) {

            revenue += order.getTotalPrice();
        }
        for(RetailOrder order : retailOrders) {

            revenue += order.getTotalPrice();
        }

        return new ResponseEntity<Double>(revenue, HttpStatus.OK);
    }

}
