package api;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import io.swagger.model.RetailOrder;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ZeroDollarOrderStepDefs extends TestSetup
{
    private ResponseEntity<RetailOrder> response;

    @Before
    public void before()
    {
        init();
    }

    @Given("^a retail order with the price set to (-?\\d+) dollars$")
    public void aRetailOrderWithThePriceSetToDollars(double dollarValue) throws Throwable
    {
        createMockOrder();
        retailOrder.setTotalPrice(dollarValue);
    }

    @When("^zeroDollarOrder is called$")
    public void zerodollarorderIsCalled() throws Throwable
    {
        response = ordersApiController.zeroDollarOrder(retailOrder);
    }

    @Then("^zeroDollarOrder will return null$")
    public void zerodollarorderWillReturnNull() throws Throwable
    {
        assertNull(response.getBody());
    }

    @Then("^zeroDollarOrder will return a newly created order$")
    public void zerodollarorderWillReturnANewlyCreatedOrder() throws Throwable
    {
        RetailOrder retailOrder = response.getBody();

        assertNotNull(retailOrder);
        assertEquals(retailOrder.getTotalPrice(), 0.0, .1);
    }
}