package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import io.swagger.api.RevenueApiController;
import io.swagger.api.WholesaleApiController;
import io.swagger.model.*;
import io.swagger.repository.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RevenueApiStepDefs
{
    private RevenueApiController revenueApiController;
    private WholesaleOrderRepository wholesaleOrderRepository;
    private RetailOrderRepository retailOrderRepository;
    private SalesRepRepository salesRepRepository;

    private WholesaleOrder wholesaleOrder;
    private RetailOrder retailOrder;

    private ResponseEntity<Double> response;

    @Before
    public void before()
    {
        revenueApiController = new RevenueApiController();
        wholesaleOrderRepository = mock(WholesaleOrderRepository.class);
        retailOrderRepository = mock(RetailOrderRepository.class);
        salesRepRepository = mock(SalesRepRepository.class);

        revenueApiController.setWholesaleOrderRepository(wholesaleOrderRepository);
        revenueApiController.setRetailOrderRepository(retailOrderRepository);
        revenueApiController.setSalesRepRepository(salesRepRepository);
    }

    @Given("^a list of wholesale and retail orders$")
    public void aListOfWholesaleAndRetailOrders() throws Throwable
    {
        ArrayList<WholesaleOrder> wholesaleOrders = new ArrayList<>();
        ArrayList<RetailOrder> retailOrders = new ArrayList<>();

        wholesaleOrders.add(createMockWholesaleOrder());
        retailOrders.add(createMockRetailOrder());

        when(wholesaleOrderRepository.findAll()).thenReturn(wholesaleOrders);
        when(retailOrderRepository.findAll()).thenReturn(retailOrders);
    }

    @When("^getTotalRevenue is called$")
    public void gettotalrevenueIsCalled() throws Throwable
    {
        response = revenueApiController.getTotalRevenue(null, null);
    }

    @Then("^getTotalRevenue will return the total revenue$")
    public void gettotalrevenueWillReturnTheTotalRevenue() throws Throwable
    {
        Double expected = 123.0;
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), expected, .01);
    }

    @Given("^a valid sales rep and a range of dates$")
    public void aValidSalesRepAndARangeOfDates() throws Throwable
    {
        ArrayList<WholesaleOrder> wholesaleOrders = new ArrayList<>();
        wholesaleOrders.add(createMockWholesaleOrder());
        when(wholesaleOrderRepository.findBySalesRepEmployeeId(anyLong())).thenReturn(wholesaleOrders);
    }

    @When("^getRevenueForSalesRep is called$")
    public void getrevenueforsalesrepIsCalled() throws Throwable
    {
        response = revenueApiController.getRevenueForSalesRep("1", null, null);
    }

    @Then("^getRevenueForSalesRep will return the total revenue for that rep for that period$")
    public void getrevenueforsalesrepWillReturnTheTotalRevenueForThatRepForThatPeriod() throws Throwable
    {
        Double expected = 23.0;
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), expected, .01);
    }

    @Given("^a region has wholesale orders$")
    public void aRegionHasWholesaleOrders() throws Throwable
    {
        ArrayList<WholesaleOrder> wholesaleOrders = new ArrayList<>();
        WholesaleOrder order = createMockWholesaleOrder();
        order.setTotalPrice(42.0);
        wholesaleOrders.add(order);
        when(wholesaleOrderRepository.findBySalesRepRegion(any())).thenReturn(wholesaleOrders);
    }

    @When("^getRevenueFromRegion is called$")
    public void getrevenuefromregionIsCalled() throws Throwable
    {
        String region = "ROCHESTER";
        response = revenueApiController.getRevenueFromRegion(region, null, null);
    }

    @Then("^getRevenueFromRegion will return the total revenue from that region$")
    public void getrevenuefromregionWillReturnTheTotalRevenueFromThatRegion() throws Throwable
    {
        Double expected = 42.0;
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), expected, .01);
    }

    private SalesRep createMockSalesRep()
    {
        SalesRep salesRep = new SalesRep();

        salesRep.setEmployeeId(1L);
        salesRep.setFirstName("Jim");
        salesRep.setLastName("Jam");
        salesRep.setRegion(SalesRep.RegionEnum.ROCHESTER);

        return salesRep;
    }

    private RetailOrder createMockRetailOrder()
    {
        Product product = new Product();
        product.setModel("KennUWare Watch");
        product.setRefurbished(false);
        product.setSerialNumber("7331");
        product.priceSoldAt(100.0);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        retailOrder = new RetailOrder();
        retailOrder.setCustomerEmail("test@email.com");
        retailOrder.setCustomerShippingState("NY");
        retailOrder.setCustomerShippingStreetAddress("1 Lomb Memorial Dr");
        retailOrder.setCustomerShippingTown("Rochester");
        retailOrder.setCustomerShippingZip("14623");
        retailOrder.setStatus(RetailOrder.StatusEnum.FULFILLED);
        retailOrder.setProducts(productList);
        retailOrder.setTotalPrice(100.0);

        return retailOrder;
    }

    private WholesaleOrder createMockWholesaleOrder()
    {
        ArrayList<ModelCount> orderMap = new ArrayList<>();
        orderMap.add(new ModelCount());

        wholesaleOrder = new WholesaleOrder();
        wholesaleOrder.setSalesRep(createMockSalesRep());
        wholesaleOrder.setStatus(WholesaleOrder.StatusEnum.FULLFILLED);
        wholesaleOrder.setTotalPrice(23.0);
        wholesaleOrder.setWholesaleAccount(createMockWholesaleAccount());
        wholesaleOrder.setOrderMap(orderMap);

        return wholesaleOrder;
    }

    private WholesaleAccount createMockWholesaleAccount()
    {
        WholesaleAccount wholesaleAccount = new WholesaleAccount();
        List<ConfiguredPrice> configuredPrice = new ArrayList<>();
        ConfiguredPrice tempConfigPrice = new ConfiguredPrice();
        tempConfigPrice.setAccount(wholesaleAccount);
        tempConfigPrice.setId(11L);
        tempConfigPrice.setModel("Te One");
        tempConfigPrice.setPrice(new BigDecimal(5));
        configuredPrice.add(tempConfigPrice);


        wholesaleAccount.setName("Jim Jam");
        wholesaleAccount.setEmail("jim@jam.com");
        wholesaleAccount.setShippingState("NY");
        wholesaleAccount.setShippingAddress("1 Lomb Memorial Dr");
        wholesaleAccount.setShippingTown("Rochester");
        wholesaleAccount.setShippingZip("14623");
        wholesaleAccount.configuredPrice(configuredPrice);

        return wholesaleAccount;
    }
}
