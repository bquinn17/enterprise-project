package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.swagger.model.RetailOrder;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:cucumber.xml")
public class AddRetailOrderStepDefs
{
    private RetailOrder testRetailOrder;

    @Given("^a client submits an order without a product$")
    public void aClientSubmitsAnOrderWithoutAProduct() throws Throwable
    {
        throw new PendingException();
    }

    @Then("^we expect to receive a (\\d+) Bad Request response$")
    public void weExpectToReceiveABadRequestResponse(int arg0) throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
