import java.util.List;

public interface ProductDao {
    void addNewProduct(String name, int price);

    List<Product> showAllProducts();
}
