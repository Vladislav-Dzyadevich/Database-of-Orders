import java.util.List;

public interface ClietDao {
    void findUserByEmail(String email);

    void addNewClient(String name, String surname, String email, String phone);

    List<Client> showAllClient();
}
