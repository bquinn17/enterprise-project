package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.swagger.model.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SpringIntegrationStepDefs
{
    private RetailOrder retailOrder;
    private WholesaleOrder wholesaleOrder;
    private WholesaleAccount wholesaleAccount;
    private String orderId;
    private String salesRepId;
    private RetailOrder.StatusEnum orderStatus;
    private ResponseEntity apiResult;
    private RestClientException apiError;
    private ResponseEntity<SalesRep> salesRepResponse;
    private String serial_num;
    private String uri = "http://127.0.0.1:8080";

    @Given("^a retail order with the price set to (-*\\d+) dollars$")
    public void aRetailOrderWithThePriceSetToDollars(double dollarValue) throws Throwable
    {
        createMockOrder();

        retailOrder.setTotalPrice(dollarValue);
    }

    @When("^a user adds a new \"([^\"]*)\" \"([^\"]*)\"$")
    public void aUserAddsANew(String orderType, String endpoint) throws Throwable
    {
        RestTemplate restTemplate = new RestTemplate();

        endpoint = uri + endpoint;

        try {
            if (orderType.equals("retailOrder")) {
                apiResult = restTemplate.postForEntity(endpoint, retailOrder, RetailOrder.class);
            }
        } catch (RestClientException ex) {
            apiError = ex;
        }


    }

    @When("^a user gets a \"([^\"]*)\" \"([^\"]*)\"$")
    public void aUserGetsA(String queryType, String endpoint) throws Throwable
    {
        RestTemplate restTemplate = new RestTemplate();

        endpoint = uri + endpoint;

        try {
            if(queryType.equals("retailOrder")) {
                if (serial_num != null) {
                    apiResult = restTemplate.getForEntity(endpoint + "?serial_num=" + serial_num, String.class, "");
                } else {
                    apiResult = restTemplate.getForEntity(endpoint, RetailOrder.class, orderId);
                }
            }
            else if(queryType.equals("wholesaleOrder")) {
                apiResult = restTemplate.getForEntity(endpoint, WholesaleOrder.class, orderId);
            }
            else if(queryType.equals("wholesaleAccount")) {
                apiResult = restTemplate.getForEntity(endpoint, ArrayList.class);
            }
        }
        catch(RestClientException e){
            apiError = e;
        }
    }

    @When("^a user changes a \"([^\"]*)\" status \"([^\"]*)\"$")
    public void aUserChangesAStatus(String orderType, String endpoint) throws Throwable
    {
        RestTemplate restTemplate = new RestTemplate();

        endpoint = uri + endpoint;

        try {
            if(orderType.equals("retailOrder")) {
                apiResult = restTemplate.postForEntity(endpoint, null, RetailOrder.class);
            }
            else if(orderType.equals("wholesaleOrder")) {
                throw new PendingException();
            }
        }
        catch(RestClientException e){
            apiError = e;
        }
    }

    @When("^a user gets a salesRep \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void aUserGetsASalesRep(String endpoint, String startDate, String endDate) throws Throwable
    {
        RestTemplate restTemplate = new RestTemplate();

        endpoint = uri + endpoint;

        try {
            apiResult = restTemplate.getForEntity(endpoint + "?sales_rep_id=" + salesRepId, String.class);
        }
        catch(RestClientException e){
            apiError = e;
        }
    }

    @When("^a user creates a wholesale account \"([^\"]*)\"$")
    public void aUserCreatesAWholesaleAccount(String endpoint) throws Throwable
    {
        RestTemplate restTemplate = new RestTemplate();

        endpoint = uri + endpoint;

        try {
            apiResult = restTemplate.postForEntity(endpoint, wholesaleAccount, WholesaleAccount.class);
        }
        catch(RestClientException e){
            apiError = e;
        }
    }

    @Then("^the api will return (\\d+)$")
    public void theApiWillReturnCreated(int expectedReturnCode) throws Throwable
    {
        if (apiResult == null) {
            Assert.assertNotNull(apiError);
            Integer statusCode = null;
            try {
                statusCode = ((HttpClientErrorException) apiError).getRawStatusCode();
                Assert.assertEquals(expectedReturnCode, statusCode.intValue());
            } catch (ClassCastException ex) {
                Assert.assertNotNull(statusCode);
            }
        } else {
            Assert.assertNotNull(apiResult.getBody());
            Assert.assertEquals(expectedReturnCode, apiResult.getStatusCode().value());
        }
        apiResult = null;
        apiError = null;
        serial_num = null;
    }

    @Given("^an order without products$")
    public void anOrderWithoutProducts() throws Throwable
    {
        createMockOrder();

        retailOrder.setProducts(new ArrayList<>());
    }

    @Given("^an order without an email$")
    public void anOrderWithoutAnEmail() throws Throwable
    {
        createMockOrder();

        retailOrder.setCustomerEmail("");
    }

    @Given("^an order without an address$")
    public void anOrderWithoutAnAddress() throws Throwable
    {
        createMockOrder();

        retailOrder.setCustomerShippingStreetAddress("");
        retailOrder.setCustomerShippingZip("");
        retailOrder.setCustomerShippingState("");
        retailOrder.setCustomerShippingTown("");
    }

    @Given("^a valid order$")
    public void aValidOrder() throws Throwable
    {
        createMockOrder();
    }

    @Given("^an order id not in the database$")
    public void anOrderIdNotInTheDatabase() throws Throwable
    {
        serial_num = "100";
        orderId = "999999999";
        orderStatus = RetailOrder.StatusEnum.FULFILLED;
    }

    @Given("^an invalid order status$")
    public void anInvalidOrderStatus() throws Throwable
    {
        orderId = "1";
        orderStatus = null;
    }

    @Given("^a valid status of an existing order$")
    public void aValidStatusOfAnExistingOrder() throws Throwable
    {
        orderId = "1";
        orderStatus = RetailOrder.StatusEnum.FULFILLED;
    }

    @Given("^a serial number is in the database$")
    public void anOrderIdInTheDatabase() throws Throwable
    {
        createMockOrder();
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = uri + "/orders/retail/new";
        ResponseEntity<RetailOrder> responseEntity = restTemplate.postForEntity(endpoint, retailOrder, RetailOrder.class);
        serial_num = responseEntity.getBody().getProducts().get(0).getSerialNumber();
    }

    @Given("^an invalid sales rep id$")
    public void anInvalidSalesRepId() throws Throwable
    {
        salesRepId = "999999999";
    }

    @Given("^a valid sales rep id$")
    public void aValidSalesRepId() throws Throwable
    {
        salesRepId = "1";
    }

    @Given("^a correctly defined wholesale order$")
    public void aCorrectlyDefinedWholesaleOrder() throws Throwable
    {
        throw new PendingException();
    }

    private void createMockOrder()
    {
        Product product = new Product();
        product.setModel("KennUWare Watch");
        product.setRefurbished(false);
        product.setSerialNumber("123456789");
        product.priceSoldAt(100.0);
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);

        retailOrder = new RetailOrder();
        retailOrder.setCustomerEmail("test@email.com");
        retailOrder.setCustomerShippingState("NY");
        retailOrder.setCustomerShippingStreetAddress("1 Lomb Memorial Dr");
        retailOrder.setCustomerShippingTown("Rochester");
        retailOrder.setCustomerShippingZip("14623");
        retailOrder.setStatus(RetailOrder.StatusEnum.FULFILLED);
        retailOrder.setProducts(productList);
        retailOrder.setTotalPrice(100.0);
    }

    @Given("^an valid wholesale account$")
    public void anValidWholesaleAccount() throws Throwable
    {
        wholesaleAccount = new WholesaleAccount();
        List<ConfiguredPrice> configuredPrice = new ArrayList<>();
        ConfiguredPrice tempConfigPrice = new ConfiguredPrice();
        tempConfigPrice.setAccount(wholesaleAccount);
        tempConfigPrice.setId(11L);
        tempConfigPrice.setModel("Te One");
        tempConfigPrice.setPrice(new BigDecimal(5));
        configuredPrice.add(tempConfigPrice);


        wholesaleAccount.setName("Greg");
        wholesaleAccount.setEmail("greg@greg.greg");
        wholesaleAccount.setShippingState("NY");
        wholesaleAccount.setShippingAddress("1 Lomb Memorial Dr");
        wholesaleAccount.setShippingTown("Rochester");
        wholesaleAccount.setShippingZip("14623");
        wholesaleAccount.configuredPrice(configuredPrice);
    }

    @Given("^there are no orders by a rep$")
    public void thereAreNoOrdersByARep() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^there is at least one order$")
    public void thereIsAtLeastOneOrder() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}