package spring.com.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.com.dto.UserResponseDto;
import spring.com.model.User;
import spring.com.service.UserService;
import spring.com.service.impl.UserMapper;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return userMapper.dtoBoxing(userService.get(userId));
    }

    @GetMapping(value = "/inject")
    public String addUser() {
        User user = new User("Bob", "Blob", "bbob@gmail.com");
        User user1 = new User("Alice", "Eila", "ealice@gmail.com");
        User user2 = new User("John", "Jhonatan", "jhonat@gmail.com");
        User user3 = new User("Mark", "Phyrs", "pmark@gmail.com");
        userService.add(user);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        return "Inject successful";
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(userMapper::dtoBoxing)
                .collect(Collectors.toList());
    }
}
