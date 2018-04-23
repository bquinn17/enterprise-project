package api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import io.swagger.api.WholesaleApiController;
import io.swagger.model.ConfiguredPrice;
import io.swagger.model.RetailOrder;
import io.swagger.model.WholesaleAccount;
import io.swagger.repository.ConfiguredPriceRepository;
import io.swagger.repository.WholesaleAccountRepository;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WholesaleApiStepDefs
{
    private WholesaleApiController wholesaleApiController;
    private WholesaleAccountRepository wholesaleAccountRepository;
    private ConfiguredPriceRepository configuredPriceRepository;

    private WholesaleAccount wholesaleAccount;
    private String firstName = "Greg";
    private String secondName = "Stevo";

    private ResponseEntity<WholesaleAccount> response;
    private ResponseEntity<List<WholesaleAccount>> listResponse;

    @Before
    public void before()
    {
        wholesaleApiController = new WholesaleApiController();
        wholesaleAccountRepository = mock(WholesaleAccountRepository.class);
        configuredPriceRepository = mock(ConfiguredPriceRepository.class);
        wholesaleApiController.setWholesaleAccountRepository(wholesaleAccountRepository);
        wholesaleApiController.setConfiguredPriceRepository(configuredPriceRepository);

        ArrayList<WholesaleAccount> wholesaleAccounts = new ArrayList<>();

        createMockWholesaleAccount();
        wholesaleAccount.setName(firstName);
        wholesaleAccounts.add(wholesaleAccount);
        createMockWholesaleAccount();
        wholesaleAccount.setName(secondName);
        wholesaleAccounts.add(wholesaleAccount);

        when(wholesaleAccountRepository.findAll()).thenReturn(wholesaleAccounts);
    }

    @When("^getWholesaleAccounts is called$")
    public void getwholesaleaccountsIsCalled() throws Throwable
    {
        listResponse = wholesaleApiController.getWholesaleAccounts();
    }

    @Then("^getWholesaleAccounts will return a list of all wholesale accounts$")
    public void getwholesaleaccountsWillReturnAListOfAllWholesaleAccounts() throws Throwable
    {
        List<WholesaleAccount> wholesaleAccounts = listResponse.getBody();
        assertNotNull(wholesaleAccounts);
        assertEquals(wholesaleAccounts.get(0).getName(), firstName);
        assertEquals(wholesaleAccounts.get(1).getName(), secondName);
    }

    @When("^addWholesaleAccount is called$")
    public void addwholesaleaccountIsCalled() throws Throwable
    {
        response = wholesaleApiController.addWholesaleAccount(createMockWholesaleAccount());
    }

    @Then("^addWholesaleAccount will return a newly created wholesaleAccount$")
    public void addwholesaleaccountWillReturnANewlyCreatedWholesaleAccount() throws Throwable
    {
        WholesaleAccount wholesaleAccount = response.getBody();
        assertNotNull(wholesaleAccount);
        assertEquals(wholesaleAccount.getName(), firstName);
    }

    private WholesaleAccount createMockWholesaleAccount()
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

        return wholesaleAccount;
    }
}
