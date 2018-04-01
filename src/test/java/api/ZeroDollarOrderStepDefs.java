package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.swagger.model.Product;
import io.swagger.model.RetailOrder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

public class ZeroDollarOrderStepDefs
{
    private RetailOrder retailOrder;
    private ResultActions apiResult;

    @Given("^a retail order with price of the order not set$")
    public void aRetailOrderWithPriceOfTheOrderNotSet() throws Throwable
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
        retailOrder.setStatus(RetailOrder.StatusEnum.FULLFILLED);
        retailOrder.setProducts(productList);
    }

    @Given("^a retail order with the price set to (\\d+) dollars$")
    public void aRetailOrderWithThePriceSetToDollars(int dollarValue) throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^a user calls \"([^\"]*)\"$")
    public void aUserCalls(String apiUrl) throws Throwable
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup().build();
        apiResult = mockMvc.perform(get(apiUrl));
    }

    @Then("^the api will return (\\d+)$")
    public void theApiWillReturnCreated(int expectedReturnCode) throws Throwable
    {
        apiResult.andExpect(status().is(expectedReturnCode));
    }
}
