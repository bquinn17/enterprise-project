package api;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.swagger.model.RetailOrder;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class CucumberTests
{
    @When("^the client calls /orders/new/refund$")
    public void theClientCallsOrdersNewRefund() throws Throwable
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/order/new/refund";

        RetailOrder retailOrder = restTemplate.getForEntity(url, RetailOrder.class).getBody();
        assertNotNull(null);
    }

    @Then("^the server creates a new order$")
    public void theServerCreatesANewOrder() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCodeOf(int arg0) throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
