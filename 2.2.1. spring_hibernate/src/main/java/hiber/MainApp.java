package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "LastName1", "user1@mail.ru");
        user1.setUserCar(new Car("Nissan", 1000));

        User user2 = new User("User2", "LastName2", "user2@mail.ru");
        user2.setUserCar(new Car("Toyota", 2000));

        User user3 = new User("User3", "LastName3", "user3@mail.ru");
        user3.setUserCar(new Car("Dodge", 3000));

        User user4 = new User("User4", "LastName4", "user4@mail.ru");
        user4.setUserCar(new Car("Fiat", 4000));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car Model = " + user.getUserCar().getModel());
            System.out.println("Car Series = " + user.getUserCar().getSeries());
            System.out.println();
        }


        String model = "Fiat";
        int series = 4000;

        List<User> ownerCar = userService.listCarOwner(model, series);
        for (User owner : ownerCar) {
            System.out.println("---------------------------------------------------");
            System.out.println("Owner car Model " + model + " Series " + series + ":");
            System.out.println("Id = " + owner.getId());
            System.out.println("First Name = " + owner.getFirstName());
            System.out.println("Last Name = " + owner.getLastName());
            System.out.println("Email = " + owner.getEmail());
            System.out.println("---------------------------------------------------");
        }


        context.close();
    }
}
