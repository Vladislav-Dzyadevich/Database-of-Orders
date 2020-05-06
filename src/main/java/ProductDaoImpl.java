import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public void addNewProduct(String name, int price) {
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO Products(name, price) VALUES(?, ?)")) {
                st.setString(1, name);
                st.setInt(2, price);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> showAllProducts() {
        List<Product> res = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT  * FROM Products");
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                res.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
