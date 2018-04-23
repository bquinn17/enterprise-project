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

public class AddWholesaleOrderStepDefs extends TestSetup
{
    private ResponseEntity<WholesaleOrder> response;

    @Before
    public void before()
    {
        init();
    }

    @Given("^a wholesale order missing top level information$")
    public void aWholesaleOrderMissingTopLevelInformation() throws Throwable
    {
        createMockWholesaleOrder();
        wholesaleOrder.setOrderMap(new ArrayList<>());
    }

    @Given("^a wholesale order where salesrep is missing information$")
    public void aWholesaleOrderWhereSalesrepIsMissingInformation() throws Throwable
    {
        createMockWholesaleOrder();
        wholesaleOrder.getSalesRep().setFirstName("");
    }

    @Given("^a wholesale order where our system fails to call an external endpoint$")
    public void aWholesaleOrderWhereOurSystemFailsToCallAnExternalEndpoint() throws Throwable
    {
        createMockWholesaleOrder();
    }

    @Given("^a valid wholesale order$")
    public void aValidWholesaleOrder() throws Throwable
    {
        WholesaleOrder returnOrder = createMockWholesaleOrder();
        returnOrder.setStatus(WholesaleOrder.StatusEnum.PLACED);
        createMockWholesaleOrder();
        when(restTemplate.postForObject(anyString(), any(WholesaleOrder.class), anyObject())).thenReturn(returnOrder);
    }

    @When("^addWholesaleOrder is called$")
    public void addwholesaleorderIsCalled() throws Throwable
    {
        response = ordersApiController.addWholesaleOrder(wholesaleOrder);
    }

    @Then("^addWholesaleOrder will return the offending order$")
    public void addwholesaleorderWillReturnTheOffendingOrder() throws Throwable
    {
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), wholesaleOrder);
    }

    @Then("^addWholesaleOrder will return the offending order with the status changed to placed$")
    public void addwholesaleorderWillReturnTheOffendingOrderWithTheStatusChangedToPlaced() throws Throwable
    {
        WholesaleOrder wholesaleOrder = response.getBody();
        assertNotNull(wholesaleOrder);
        wholesaleOrder.setStatus(WholesaleOrder.StatusEnum.FULLFILLED);
        assertEquals(wholesaleOrder, this.wholesaleOrder);
    }
}