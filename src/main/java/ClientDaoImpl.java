import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientDaoImpl implements ClietDao {

    private final Connection conn;

    public ClientDaoImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public void findUserByEmail(String email) {
        int userId = 0;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT id FROM Clients WHERE email=?");
            st.setString(1, email);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userId != 0) {
            System.out.println("Ваш id = " + userId + " запомните его и введите при оформлении заказа");
        } else {
            System.out.println("You are not in the database, press 1 and register");
        }
    }

    @Override
    public void addNewClient(String name, String surname, String email, String phone) {
        try {
            try (PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO Clients( name, surname, email, phone) VALUES(?, ?, ?, ?)")) {
                st.setString(1, name);
                st.setString(2, surname);
                st.setString(3, email);
                st.setString(4, phone);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> showAllClient() {
        List<Client> res = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Clients");
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getString("phone"));
                res.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
