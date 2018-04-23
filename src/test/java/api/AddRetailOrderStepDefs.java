package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import io.swagger.model.RetailOrder;
import io.swagger.model.WholesaleOrder;
import org.springframework.http.ResponseEntity;

import javax.websocket.MessageHandler;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class AddRetailOrderStepDefs extends TestSetup
{
    private ResponseEntity<RetailOrder> response;

    @Before
    public void before()
    {
        init();
    }

    @Given("^a retail order missing top level information$")
    public void aRetailOrderMissingTopLevelInformation() throws Throwable
    {
        createMockOrder();
        retailOrder.setCustomerEmail("");
    }

    @Given("^a valid retail order$")
    public void aValidRetailOrder() throws Throwable
    {
        createMockOrder();
    }

    @When("^addRetailOrder is called$")
    public void addretailorderIsCalled() throws Throwable
    {
        response = ordersApiController.addRetailOrder(retailOrder);
    }

    @Then("^addRetailOrder will return the offending order$")
    public void addretailorderWillReturnTheOffendingOrder() throws Throwable
    {
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), retailOrder);
    }

    @Given("^a retail order where our system fails to call an external endpoint$")
    public void aRetailOrderWhereOurSystemFailsToCallAnExternalEndpoint() throws Throwable
    {
        createMockOrder();
        when(restTemplate.postForObject(anyString(), anyObject(), anyObject())).thenReturn(retailOrder);
    }

    @Then("^addRetailOrder will return a valid order$")
    public void addretailorderWillReturnAValidOrder() throws Throwable
    {
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), retailOrder);
    }
}