package api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.swagger.api.OrdersApiController;
import io.swagger.api.WholesaleApiController;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"api"},
                 features = {"src/test/java/api/resources/AddRetailOrder.feature",
                             "src/test/java/api/resources/AddWholesaleOrder.feature",
                             "src/test/java/api/resources/GetTotalRevenue.feature",
                             "src/test/java/api/resources/GetRevenueFromRegion.feature",
                             "src/test/java/api/resources/GetRevenueForSalesRep.feature",
                             "src/test/java/api/resources/ChangeOrderStatus.feature",
                             "src/test/java/api/resources/GetOrder.feature",
                             "src/test/java/api/resources/AddWholesaleAccount.feature",
                             "src/test/java/api/resources/GetWholesaleAccounts.feature",
                             "src/test/java/api/resources/ZeroDollarOrder.feature",
                             "src/test/java/api/resources/GetAllOrders.feature",
                             "src/test/java/api/resources/GetOrdersByRep.feature"})
@ContextConfiguration(value = "resources/cucumber.xml", classes={OrdersApiController.class, WholesaleApiController.class})
public class RunCukeTests
{
}