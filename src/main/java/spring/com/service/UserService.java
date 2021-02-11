package spring.com.service;

import java.util.List;
import spring.com.model.User;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User get(Long id);
}
