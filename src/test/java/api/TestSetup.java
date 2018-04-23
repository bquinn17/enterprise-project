package api;

import cucumber.api.java.Before;
import io.swagger.api.OrdersApiController;
import io.swagger.model.*;
import io.swagger.repository.ProductRepository;
import io.swagger.repository.RetailOrderRepository;
import io.swagger.repository.WholesaleAccountRepository;
import io.swagger.repository.WholesaleOrderRepository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestSetup
{
    protected OrdersApiController ordersApiController;
    protected RetailOrderRepository retailOrderRepository;
    protected WholesaleOrderRepository wholesaleOrderRepository;
    protected ProductRepository productRepository;
    protected RestTemplate restTemplate;

    protected RetailOrder retailOrder;
    protected WholesaleOrder wholesaleOrder;

    protected void init()
    {
        retailOrderRepository = mock(RetailOrderRepository.class);
        wholesaleOrderRepository = mock(WholesaleOrderRepository.class);
        productRepository = mock(ProductRepository.class);
        restTemplate = mock(RestTemplate.class);

        ordersApiController = new OrdersApiController();
        ordersApiController.setRetailOrderRepository(retailOrderRepository);
        ordersApiController.setWholesaleOrderRepository(wholesaleOrderRepository);
        ordersApiController.setProductRepository(productRepository);
        ordersApiController.setRestTemplate(restTemplate);
    }

    protected RetailOrder createMockOrder()
    {
        Product product = new Product();
        product.setModel("Kenn-U-Active");
        product.setRefurbished(false);
        product.setSerialNumber("7331");
        product.priceSoldAt(100.0);
        List<Product> productList = new ArrayList<>();
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

        return retailOrder;
    }

    protected WholesaleOrder createMockWholesaleOrder()
    {
        SalesRep salesRep = new SalesRep();

        salesRep.setEmployeeId(1L);
        salesRep.setFirstName("Jim");
        salesRep.setLastName("Jam");
        salesRep.setRegion(SalesRep.RegionEnum.ROCHESTER);

        ArrayList<ModelCount> orderMap = new ArrayList<>();
        orderMap.add(new ModelCount());

        wholesaleOrder = new WholesaleOrder();
        wholesaleOrder.setSalesRep(salesRep);
        wholesaleOrder.setStatus(WholesaleOrder.StatusEnum.FULLFILLED);
        wholesaleOrder.setTotalPrice(0.0);
        wholesaleOrder.setWholesaleAccount(createMockWholesaleAccount());
        wholesaleOrder.setOrderMap(orderMap);

        return wholesaleOrder;
    }

    private WholesaleAccount createMockWholesaleAccount()
    {
        WholesaleAccount wholesaleAccount = new WholesaleAccount();
        List<ConfiguredPrice> configuredPrice = new ArrayList<>();
        ConfiguredPrice tempConfigPrice = new ConfiguredPrice();
        tempConfigPrice.setAccount(wholesaleAccount);
        tempConfigPrice.setId(11L);
        tempConfigPrice.setModel("Te One");
        tempConfigPrice.setPrice(new BigDecimal(5));
        configuredPrice.add(tempConfigPrice);


        wholesaleAccount.setName("Jim Jam");
        wholesaleAccount.setEmail("jim@jam.com");
        wholesaleAccount.setShippingState("NY");
        wholesaleAccount.setShippingAddress("1 Lomb Memorial Dr");
        wholesaleAccount.setShippingTown("Rochester");
        wholesaleAccount.setShippingZip("14623");
        wholesaleAccount.configuredPrice(configuredPrice);

        return wholesaleAccount;
    }
}
