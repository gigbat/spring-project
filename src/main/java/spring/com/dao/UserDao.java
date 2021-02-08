package spring.com.dao;

import spring.com.model.User;
import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();
}
