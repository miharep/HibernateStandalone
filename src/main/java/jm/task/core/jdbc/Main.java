package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        
        UserService userService = new UserServiceImpl();
        
        //Создание таблицы User(ов)
        userService.createUsersTable();
        
        //Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userService.saveUser("Иван", "Иванов" , (byte) 11);
        userService.saveUser("Петр", "Петров" , (byte) 22);
        userService.saveUser("Сидор", "Сидоров" , (byte) 33);
        userService.saveUser("Четвертый", "Чел" , (byte) 44);
        
        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        System.out.println(User.toString(userService.getAllUsers()));
        
        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        
        //Удаление таблицы
        userService.dropUsersTable();
    }
}
