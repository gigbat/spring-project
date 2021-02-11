package spring.com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.com.dao.UserDao;
import spring.com.model.User;
import spring.com.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow(()
                -> new RuntimeException("Can't get user by id " + id));
    }
}
