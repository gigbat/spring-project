package spring.com.service;

import spring.com.model.User;
import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();
}