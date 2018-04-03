package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.swagger.api.OrdersApiController;
import io.swagger.api.WholesaleApiController;
import io.swagger.model.Product;
import io.swagger.model.RetailOrder;
import io.swagger.model.WholesaleOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest({OrdersApiController.class, WholesaleApiController.class})
@SpringBootTest
@EnableWebMvc
public class SpringIntegrationStepDefs
{
    private RetailOrder retailOrder;
    private WholesaleOrder wholesaleOrder;
    private Long orderId;
    private Long salesRepId;
    private RetailOrder.StatusEnum orderStatus;
    private ResultActions apiResult;

    @Given("^a retail order with price of the order not set$")
    public void aRetailOrderWithPriceOfTheOrderNotSet() throws Throwable
    {
        createMockOrder();

        retailOrder.setTotalPrice(null);
    }

    @Given("^a retail order with the price set to (\\d+) dollars$")
    public void aRetailOrderWithThePriceSetToDollars(double dollarValue) throws Throwable
    {
        createMockOrder();

        retailOrder.setTotalPrice(dollarValue);
    }

    @When("^a user calls \"([^\"]*)\"$")
    public void aUserCalls(String apiUrl) throws Throwable
    {
        MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(OrdersApiController.class).build();
        apiResult = mockMvc.perform(get(apiUrl));
    }

    @Then("^the api will return (\\d+)$")
    public void theApiWillReturnCreated(int expectedReturnCode) throws Throwable
    {
        apiResult.andExpect(status().is(expectedReturnCode));
    }

    @Given("^an order without products$")
    public void anOrderWithoutProducts() throws Throwable
    {
        createMockOrder();

        retailOrder.setProducts(null);
    }

    @Given("^an order without an email$")
    public void anOrderWithoutAnEmail() throws Throwable
    {
        createMockOrder();

        retailOrder.setCustomerEmail(null);
    }

    @Given("^an order without an address$")
    public void anOrderWithoutAnAddress() throws Throwable
    {
        createMockOrder();

        retailOrder.setCustomerShippingStreetAddress(null);
        retailOrder.setCustomerShippingZip(null);
        retailOrder.setCustomerShippingState(null);
        retailOrder.setCustomerShippingTown(null);
    }

    @Given("^a valid order$")
    public void aValidOrder() throws Throwable
    {
        createMockOrder();
    }

    @Given("^an order id not in the database$")
    public void anOrderIdNotInTheDatabase() throws Throwable
    {
        orderId = 999999999L;
    }

    @Given("^an invalid order status$")
    public void anInvalidOrderStatus() throws Throwable
    {
        orderStatus = null;
    }

    @Given("^a valid status of an existing order$")
    public void aValidStatusOfAnExistingOrder() throws Throwable
    {
        orderId = 1L;
        orderStatus = RetailOrder.StatusEnum.FULFILLED;
    }

    @Given("^an order id in the database$")
    public void anOrderIdInTheDatabase() throws Throwable
    {
        orderId = 1L;
    }

    @Given("^an invalid sales rep id$")
    public void anInvalidSalesRepId() throws Throwable
    {
        salesRepId = 999999999L;
    }

    @Given("^a valid sales rep id$")
    public void aValidSalesRepId() throws Throwable
    {
        salesRepId = 1L;
    }

    @Given("^a correctly defined wholesale order$")
    public void aCorrectlyDefinedWholesaleOrder() throws Throwable
    {
        throw new PendingException();
    }

    private void createMockOrder()
    {
        Product product = new Product();
        product.setModel("KennUWare Watch");
        product.setRefurbished(false);
        product.setSerialNumber("123456789");
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);

        retailOrder = new RetailOrder();
        retailOrder.setCustomerEmail("test@email.com");
        retailOrder.setCustomerShippingState("NY");
        retailOrder.setCustomerShippingStreetAddress("1 Lomb Memorial Dr");
        retailOrder.setCustomerShippingTown("Rochester");
        retailOrder.setCustomerShippingZip("14623");
        retailOrder.setStatus(RetailOrder.StatusEnum.FULFILLED);
        retailOrder.setProducts(productList);
        retailOrder.setTotalPrice(0.0);
    }

    @Given("^an valid wholesale account$")
    public void anValidWholesaleAccount() throws Throwable
    {
        throw new PendingException();
    }

    @Given("^there are no orders by a rep$")
    public void thereAreNoOrdersByARep() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^there is at least one order$")
    public void thereIsAtLeastOneOrder() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}