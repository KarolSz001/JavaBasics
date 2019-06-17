package iface;
import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    void removeUserById(Integer userId);
}
