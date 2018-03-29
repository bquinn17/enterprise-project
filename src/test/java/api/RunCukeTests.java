package api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"api"},
                 features = {"src/test/java/api/resources/AddRetailOrder.feature",
                             "src/test/java/api/resources/AddWholesaleOrder.feature",
                             "src/test/java/api/resources/ChangeOrderStatus.feature",
                             "src/test/java/api/resources/GetOrder.feature",
                             "src/test/java/api/resources/GetSalesRep.feature",
                             "src/test/java/api/resources/ZeroDollarOrder.feature"})
public class RunCukeTests
{
}