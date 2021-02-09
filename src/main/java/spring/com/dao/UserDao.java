package spring.com.dao;

import java.util.List;
import spring.com.model.User;

public interface UserDao {
    void add(User user);

    List<User> listUsers();
}
