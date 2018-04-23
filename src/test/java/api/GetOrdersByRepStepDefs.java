package api;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.swagger.model.RetailOrder;
import io.swagger.model.WholesaleAccount;
import io.swagger.model.WholesaleOrder;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class GetOrdersByRepStepDefs extends TestSetup
{
    private ResponseEntity<List<WholesaleOrder>> response;
    private String sales_rep_id;
    private String firstName = "Jim";
    private String secondName = "Bjorn";

    @Before
    public void before()
    {
        init();
    }

    @Given("^an incorrect sales rep id$")
    public void anIncorrectSalesRepId() throws Throwable
    {
        sales_rep_id = "5";
        when(wholesaleOrderRepository.findBySalesRepEmployeeId(5L)).thenReturn(new ArrayList<>());
    }

    @Given("^a valid sales rep id$")
    public void aValidSalesRepId() throws Throwable
    {
        sales_rep_id = "4";

        ArrayList<WholesaleOrder> wholesaleOrders = new ArrayList<>();
        wholesaleOrders.add(createMockWholesaleOrder());
        createMockWholesaleOrder();
        wholesaleOrder.getSalesRep().setFirstName(secondName);
        wholesaleOrders.add(wholesaleOrder);

        when(wholesaleOrderRepository.findBySalesRepEmployeeId(anyLong())).thenReturn(wholesaleOrders);
    }

    @When("^getOrdersByRep is called$")
    public void getordersbyrepIsCalled() throws Throwable
    {
        response = ordersApiController.getOrdersByRep(sales_rep_id);
    }

    @Then("^getOrdersByRep will return nothing$")
    public void getordersbyrepWillReturnNothing() throws Throwable
    {
        assertNull(response.getBody());
    }

    @Then("^getOrdersByRep will return a list of wholesaleOrders$")
    public void getordersbyrepWillReturnAListOfWholesaleOrders() throws Throwable
    {
        List<WholesaleOrder> wholesaleOrders = response.getBody();
        assertNotNull(wholesaleOrders);
        assertEquals(wholesaleOrders.get(0).getSalesRep().getFirstName(), firstName);
        assertEquals(wholesaleOrders.get(1).getSalesRep().getFirstName(), secondName);
    }
}
