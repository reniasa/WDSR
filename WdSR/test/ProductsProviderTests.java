import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductsProviderTests {


    private ProductStorage storage;

    @Before
    public void setUp() {
        storage = mock(ProductStorage.class);
    }

    @Test
    public void productsAreTakenFromStorage(){
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());

        setStorageProducts(expectedProducts);

        List<Product> actualProducts = getActualProducts();

        Assert.assertEquals(expectedProducts, actualProducts);
    }

    private List<Product> getActualProducts() {
        return new ProductsProvider(storage).getAllProducts();
    }

    private void setStorageProducts(List<Product> expectedProducts) {
        when(storage.getProducts()).thenReturn(expectedProducts);
    }

    @Test
    public void productsHaveCorrectNames() {
        List<Product> expectedProducts = new ArrayList<>();
        Product expectedProduct = new Product();
        expectedProduct.Name = "Gold";
        expectedProducts.add(expectedProduct);

        setStorageProducts(expectedProducts);

        List<Product> actualProducts = getActualProducts();
        Product actualProduct = actualProducts.get(0);

        Assert.assertEquals(expectedProduct.Name, actualProduct.Name);
    }
}
