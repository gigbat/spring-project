package spring.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @GetMapping(value = "/create")
    public void addUser(@RequestParam String name, @RequestParam String surname,
                        @RequestParam String email) {
        User user = new User(name, surname, email);
        userService.add(user);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(UserMapperServiceImpl::dtoBoxing)
                .collect(Collectors.toList());
    }
}
