import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private final Connection conn;

    public OrderDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean isUserPresentInDb(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Clients WHERE id=?");
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isProductPresentInDb(String name) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Products WHERE name=?");
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPricePresentInDb(int price) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Products WHERE price=?");
            st.setInt(1, price);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addNewOrder(int clintId, String productName, int qyantity, int orderPrice) {
        try {
            try (PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO Orders(clintId, productName, qyantity, orderPrice) VALUES(?, ?, ?, ?)")) {
                st.setInt(1, clintId);
                st.setString(2, productName);
                st.setInt(3, qyantity);
                st.setInt(4, orderPrice * qyantity);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> res = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Orders");
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setClientId(resultSet.getInt("clintId"));
                order.setProductName(resultSet.getString("productName"));
                order.setQuantity(resultSet.getInt("qyantity"));
                order.setOrderPrice(resultSet.getInt("orderPrice"));
                res.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
