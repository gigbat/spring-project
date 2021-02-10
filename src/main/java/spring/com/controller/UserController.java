package spring.com.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.com.config.AppConfig;
import spring.com.dto.UserResponseDto;
import spring.com.model.User;
import spring.com.service.UserService;
import spring.com.service.impl.UserMapperServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private static final UserService userService = context.getBean(UserService.class);

    @GetMapping(value = "/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        Optional<User> optionalUser = userService.get(userId);
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            return UserMapperServiceImpl.dtoBoxing(user);
        }
        throw new RuntimeException("User " + user + " isn't exist");
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
                .map(UserMapperServiceImpl::dtoBoxing)
                .collect(Collectors.toList());
    }
}
