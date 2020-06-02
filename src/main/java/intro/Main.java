package intro;

import intro.config.AppConfig;
import intro.dao.MyService;
import intro.model.User;
import intro.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        User user1 = new User("Andrii", 24);
        User user2 = new User("Sasha", 23);
        User user3 = new User("Dima", 22);
        User user4 = new User("Masha", 1);
        User user5 = new User("Olga", 4);
        User user6 = new User("Marina", 67);
        UserService userService = context.getBean(UserService.class);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        userService.add(user5);
        userService.add(user6);
        userService.listUsers().forEach(System.out::println);
        System.out.println("Tested @Bean annotation below...");
        MyService bean = context.getBean(MyService.class);
        bean.sayHello();
    }
}
