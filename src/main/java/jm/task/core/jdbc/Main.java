package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        
        UserService userService = new UserServiceImpl();
        
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            userService.createUsersTable();
            
            userService.saveUser("Иван", "Иванов" , (byte) 11);
            userService.saveUser("Петр", "Петров" , (byte) 22);
            userService.saveUser("Сидор", "Сидоров" , (byte) 33);
            userService.saveUser("Еще", "Tot" , (byte) 22);

            System.out.println(User.toString(userService.getAllUsers()));

            userService.cleanUsersTable();
            
            userService.dropUsersTable();
        }
    }
}
