package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            String queryCreateUsersTable =
                    "CREATE TABLE IF NOT EXISTS users ( " +
                        "id BIGINT(8) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                        "name VARCHAR(45) NULL, " +
                        "lastName VARCHAR(45) NULL, " +
                        "age BIT(8) NULL," +
                        "PRIMARY KEY (id)," +
                        "UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)" +
                    "DEFAULT CHARACTER SET = utf8;" ;
            stmt.executeUpdate(queryCreateUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void dropUsersTable() {
        try (Connection conn = Util.getConnection()) {
            conn.createStatement().executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void saveUser(String name, String lastName, byte age) {
        String querySaveUser = "INSERT INTO users VALUES (default, ?, ?, ?)";
        try (Connection conn = Util.getConnection();
             PreparedStatement stmtPrep = conn.prepareStatement(querySaveUser)) {
            stmtPrep.setString(1, name);
            stmtPrep.setString(2, lastName);
            stmtPrep.setByte(3, age);
            int isRowAffected = stmtPrep.executeUpdate();
            if (isRowAffected > 0 ) {
                System.out.println("User с именем – "+ name + " добавлен в базу данных");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection()) {
            PreparedStatement stmtPrep =
                    conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmtPrep.setLong(1, id);
            stmtPrep.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = Util.getConnection()) {
            ResultSet rs = conn.createStatement()
                    .executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection()) {
            conn.createStatement().executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}