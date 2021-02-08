package spring.com;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.com.config.AppConfig;
import spring.com.model.User;
import spring.com.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Bob", "Check", "bob@gmail.com");
        User user2 = new User("Alice", "Check", "alice@gmail.com");
        User user3 = new User("John", "Fuck", "john@gmail.com");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(user.getSurname());
            System.out.println(user.getEmail());
            System.out.println("----------------------------");
        }
    }
}
