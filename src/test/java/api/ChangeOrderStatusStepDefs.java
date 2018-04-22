package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import io.swagger.model.RetailOrder;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class ChangeOrderStatusStepDefs extends TestSetup
{
    private ResponseEntity<RetailOrder> response;
    private Long id;
    private String status;

    @Before
    public void before()
    {
        init();
    }

    @Given("^an existing order$")
    public void anExistingOrder() throws Throwable
    {
        createMockOrder();
        id = retailOrder.getID();
        when(retailOrderRepository.getOne(id)).thenReturn(retailOrder);
    }

    @When("^changeOrderStatus is called with a status of \"([^\"]*)\"$")
    public void changeorderstatusIsCalledWithAStatusOf(String status) throws Throwable
    {
        if(id == null)
            id = -1L;
        this.status = status;
        response = ordersApiController.changeOrderStatus(id.toString(), status);
    }

    @Then("^changeOrderStatus will not return an order$")
    public void changeorderstatusWillNotReturnAnOrder() throws Throwable
    {
        assertNull(response.getBody());
    }

    @Then("^changeOrderStatus will return an updated order$")
    public void changeorderstatusWillReturnAnUpdatedOrder() throws Throwable
    {
        RetailOrder retailOrder = response.getBody();
        assertNotNull(retailOrder);
        assertEquals(retailOrder.getStatus(), RetailOrder.StatusEnum.valueOf(status));
    }
}