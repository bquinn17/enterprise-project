package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.SalesRep;
import io.swagger.model.RetailOrder;
import io.swagger.model.WholesaleOrder;
import io.swagger.repository.RetailOrderRepository;
import io.swagger.repository.SalesRepRepository;
import io.swagger.repository.WholesaleAccountRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.sasl.Sasl;
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

    @Autowired
    private WholesaleOrderRepository wholesaleOrderRepository;

    @Autowired
    private RetailOrderRepository retailOrderRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;

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
        double revenue;
        if(dateFrom != null && dateTo != null){
            revenue = getWholeSaleRevenueDateRange(dateTo, dateFrom, wholesaleOrders);
        }
        else{
            revenue = getWholeSaleRevenueNoDate(wholesaleOrders);
        }

        ResponseEntity<Double> response = revenue >= 0 ? new ResponseEntity<Double>(revenue, HttpStatus.OK) : new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);
        return response;
    }

    public ResponseEntity<Double> getRevenueFromRegion(@ApiParam(value = "",required=true) @PathVariable("region") String region,@ApiParam(value = "") @Valid @RequestParam(value = "date_from", required = false) String dateFrom,@ApiParam(value = "") @Valid @RequestParam(value = "date_to", required = false) String dateTo) {

        SalesRep.RegionEnum regionEnum = SalesRep.RegionEnum.fromValue(region);
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findBySalesRepRegion(regionEnum);
        double revenue = getWholeSaleRevenueNoDate(wholesaleOrders);

        return new ResponseEntity<Double>(revenue, HttpStatus.OK);
    }

    public ResponseEntity<Double> getTotalRevenue(@ApiParam(value = "") @Valid @RequestParam(value = "date_from", required = false) String dateFrom, @ApiParam(value = "") @Valid @RequestParam(value = "date_to", required = false) String dateTo) {
        List<WholesaleOrder> wholesaleOrders = wholesaleOrderRepository.findAll();
        List<RetailOrder> retailOrders = retailOrderRepository.findAll();
        double revenue;
        if(dateTo != null && dateFrom != null){
            revenue = getWholeSaleRevenueDateRange(dateTo, dateFrom, wholesaleOrders) + getRetailRevenueDateRange(dateTo, dateFrom, retailOrders);
        }
        else{
            revenue = getWholeSaleRevenueNoDate(wholesaleOrders) + getRetailRevenueNoDate(retailOrders);
        }

        ResponseEntity<Double> response = revenue >= 0 ? new ResponseEntity<Double>(revenue, HttpStatus.OK) : new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);
        return response;
    }

    private double getWholeSaleRevenueDateRange(String dateTo, String dateFrom, List<WholesaleOrder> wholesaleOrders){
        Date to;
        Date from;
        double result = 0.0;

        try{
            to = dateFormat.parse(dateTo);
            from = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            return -1;
        }
        wholesaleOrders.stream().filter(order -> order.getDateCreated().after(from) && order.getDateCreated().before(to)).mapToDouble(WholesaleOrder::getTotalPrice).sum();
        return result;
    }

    private double  getWholeSaleRevenueNoDate(List<WholesaleOrder> wholesaleOrders){
        double result = 0.0;
        wholesaleOrders.stream().mapToDouble(WholesaleOrder::getTotalPrice).sum();
        return result;
    }

    private double getRetailRevenueDateRange(String dateTo, String dateFrom, List<RetailOrder> retailOrders){
        Date to;
        Date from;
        double result;

        try{
            to = dateFormat.parse(dateTo);
            from = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            return -1;
        }
        result = retailOrders.stream().filter(order -> order.getDateCreated().after(from) && order.getDateCreated().before(to)).mapToDouble(RetailOrder::getTotalPrice).sum();
        return result;
    }

    private double getRetailRevenueNoDate(List<RetailOrder> retailOrders){
        double result = 0.0;
        retailOrders.stream().mapToDouble(RetailOrder::getTotalPrice).sum();
        return result;
    }

}
