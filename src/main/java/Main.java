import java.sql.Connection;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/orders?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Vlad19191990";

    public static void main(String[] args) {
        ConnectionFactory f = new ConnectionFactory(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );
        Connection conn = f.getConnection();
        ProductDaoImpl productDao = new ProductDaoImpl(conn);
        OrderDaoImpl orderDao = new OrderDaoImpl(conn);
        Scanner scn = new Scanner(System.in);
        boolean exit = true;
        ClientDaoImpl clientDao = new ClientDaoImpl(conn);
        System.out.println("Введите вашу почту");
        String email = scn.nextLine();
        clientDao.findUserByEmail(email);
        while (exit) {
            System.out.println("Add new client - type 1");
            System.out.println("Show all clients - type 2");
            System.out.println("Add new product  - type 3");
            System.out.println("Show all Products - type 4");
            System.out.println("Add new order - type 5");
            System.out.println("Show all orders - type 6");
            System.out.println("if you want to finish - type 7");
            String choice = scn.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter name, surname, email, and phone");
                    String name = scn.nextLine();
                    String surname = scn.nextLine();
                    String enteredEmail = scn.nextLine();
                    String phone = scn.nextLine();
                    clientDao.addNewClient(name, surname, enteredEmail, phone);
                    break;
                case "2":
                    System.out.println(clientDao.showAllClient());
                    break;
                case "3":
                    System.out.println("Enter name of product and his price in UAN");
                    String nameProducts = scn.nextLine();
                    int price = scn.nextInt();
                    productDao.addNewProduct(nameProducts, price);
                    break;
                case "4":
                    System.out.println(productDao.showAllProducts());
                    break;
                case "5":
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter your id");
                    int clintId = scanner.nextInt();
                    System.out.println("Choose a product");
                    System.out.println("Choose the name of product in list below");
                    System.out.println(productDao.showAllProducts());
                    String productName = scn.nextLine();
                    System.out.println("How much?");
                    int quantity = scn.nextInt();
                    System.out.println("Choose the price of product in list below");
                    System.out.println(productDao.showAllProducts());
                    System.out.println("What price is your choice");
                    int orderPrice = scanner.nextInt();
                    if (orderDao.isUserPresentInDb(clintId)) {
                        orderDao.addNewOrder(clintId, productName, quantity, orderPrice);
                    } else {
                        System.out.println("There is no client with this id, please, register");
                    }
                    if (orderDao.isProductPresentInDb(productName)) {
                        orderDao.addNewOrder(clintId, productName, quantity, orderPrice);
                    } else {
                        System.out.println("There is no product with this name");
                    }
                    if (orderDao.isPricePresentInDb(orderPrice)) {
                        orderDao.addNewOrder(clintId, productName, quantity, orderPrice);
                    } else {
                        System.out.println("There is no such price");
                    }
                    break;
                case "6":
                    System.out.println(orderDao.showAllOrders());
                    break;
                case "7":
                    System.out.println("See you later, bye :)");
                    exit = false;
                    break;
            }
        }
    }
}
