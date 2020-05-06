import java.util.List;

public interface OrderDao {
    void addNewOrder(int clintId, String productName, int quantity, int orderPrice);

    List<Order> showAllOrders();

    boolean isUserPresentInDb(int id);

    boolean isProductPresentInDb(String product);

    boolean isPricePresentInDb(int price);
}
