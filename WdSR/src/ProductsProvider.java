import java.util.List;


public class ProductsProvider {
    private ProductStorage storage;

    public ProductsProvider(ProductStorage storage) {
        this.storage = storage;
    }

    public List<Product> getAllProducts() {
        return storage.getProducts();
    }
}
