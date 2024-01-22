package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    
    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users ( " +
                "id BIGINT(8) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NULL, " +
                "lastName VARCHAR(45) NULL, " +
                "age BIT(8) NULL," +
                "PRIMARY KEY (id)," +
                "UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)";
        try {
            Util.getSessionFactory().inTransaction(session -> {
                session.createNativeQuery(createUserTableQuery, User.class)
                        .executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String dropUserTableQuery = "DROP TABLE IF EXISTS users";
        try {
            Util.getSessionFactory().inTransaction(session -> {
                session.createNativeQuery(dropUserTableQuery, User.class)
                        .executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            Util.getSessionFactory().inTransaction(session -> {
                session.persist(user);
                session.flush();
                
                if (session.getIdentifier(user) != null) {
                    System.out.println("User с именем – "
                            + name
                            + " добавлен в базу данных");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Util.getSessionFactory().inTransaction(session -> {
                session.remove(session.getReference(User.class, id));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try {
            Util.getSessionFactory().inTransaction(session -> {
                usersList.addAll(
                        session.createSelectionQuery("from User", User.class)
                                .getResultList());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        try{
            Util.getSessionFactory().inTransaction(session -> {
                session.createMutationQuery("delete from User")
                        .executeUpdate();;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
