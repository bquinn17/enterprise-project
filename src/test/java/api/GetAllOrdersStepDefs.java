package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import io.swagger.model.BasicOrder;
import io.swagger.model.RetailOrder;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class GetAllOrdersStepDefs extends TestSetup
{
    private ResponseEntity<List<BasicOrder>> response;
    private String serialNum;

    @Before
    public void before()
    {
        init();
    }

    @Given("^orders exist$")
    public void ordersExist() throws Throwable
    {
        ArrayList<RetailOrder> retailOrders = new ArrayList<>();
        createMockOrder();
        serialNum = retailOrder.getProducts().get(0).getSerialNumber();
        retailOrders.add(retailOrder);

        when(retailOrderRepository.findAll()).thenReturn(retailOrders);
    }

    @When("^getAllOrders is called$")
    public void getallordersIsCalled() throws Throwable
    {
        response = ordersApiController.getAllOrders();
    }

    @Then("^getAllOrders will return a list of basic order objects$")
    public void getallordersWillReturnAListOfBasicOrderObjects() throws Throwable
    {
        List<BasicOrder> orders = response.getBody();
        assertNotNull(orders);
        assertEquals(orders.get(0).getProducts().get(0).getSerialNumber(), serialNum);
    }
}
