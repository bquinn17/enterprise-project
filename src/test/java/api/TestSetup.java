package api;

import cucumber.api.java.Before;
import io.swagger.api.OrdersApiController;
import io.swagger.model.Product;
import io.swagger.model.RetailOrder;
import io.swagger.repository.ProductRepository;
import io.swagger.repository.RetailOrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestSetup
{
    protected OrdersApiController ordersApiController;
    protected RetailOrderRepository retailOrderRepository;
    protected ProductRepository productRepository;

    protected RetailOrder retailOrder;

    protected void init()
    {
        retailOrderRepository = mock(RetailOrderRepository.class);
        productRepository = mock(ProductRepository.class);

        ordersApiController = new OrdersApiController();
        ordersApiController.setRetailOrderRepository(retailOrderRepository);
        ordersApiController.setProductRepository(productRepository);
    }

    protected RetailOrder createMockOrder()
    {
        Product product = new Product();
        product.setModel("KennUWare Watch");
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
}
