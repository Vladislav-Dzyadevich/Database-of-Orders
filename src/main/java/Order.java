public class Order {
    private int clintId;
    private String productName;
    private int qyantity;
    private int orderPrice;

    public Order() {

    }

    public Order(int clientId, String productName, int qyantity, int orderPrice) {
        this.clintId = clintId;
        this.productName = productName;
        this.qyantity = qyantity;
        this.orderPrice = orderPrice;
    }

    public int getClientId() {
        return clintId;
    }

    public void setClientId(int clientId) {
        this.clintId = clientId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return qyantity;
    }

    public void setQuantity(int quantity) {
        this.qyantity = quantity;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order " +
                "clientId=" + clintId +
                ", productName='" + productName + '\'' +
                ", quantity=" + qyantity +
                ", orderPrice=" + orderPrice +
                '.' + "\n";
    }
}
