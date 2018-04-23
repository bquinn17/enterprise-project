package api;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import io.swagger.model.RetailOrder;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class GetOrderStepDefs extends TestSetup
{
    private ResponseEntity<RetailOrder> response;
    private String serial_num;

    @Before
    public void before()
    {
        init();
    }

    @Given("^a serial number is in the database mock$")
    public void aSerialNumberIsInTheDatabaseMock() throws Throwable
    {
        ArrayList<RetailOrder> retailOrders = new ArrayList<>();
        retailOrders.add(createMockOrder());
        when(retailOrderRepository.findAll()).thenReturn(retailOrders);
    }

    @When("^getOrder is called with serial number \"([^\"]*)\"$")
    public void getorderIsCalled(String serial_num) throws Throwable
    {
        this.serial_num = serial_num;
        response = ordersApiController.getOrder(serial_num);
    }

    @Then("^getOrder will return an order with a matching product id$")
    public void getorderWillReturnAnOrderWithAMatchingProductId() throws Throwable
    {
        RetailOrder responseOrder = response.getBody();

        if(serial_num.equals("not_in_database"))
            assertNull(responseOrder);
        else
            assertEquals(responseOrder.getProducts().get(0).getSerialNumber(), serial_num);
    }
}
