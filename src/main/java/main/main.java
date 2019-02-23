package main;

import config.Database;
import dao.UserDao;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class main {
    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        UserDao userDao = new UserDao(db);

      /*  User user = new User("DROP TABLE", "M","tomek@email.com");

        userDao.save(user);             //odpala metode save z UserDao
*/
        User user = new User(3,"Tom", "Mad","tomek123@email.com");

        userDao.update(user);

        userDao.delete(2);

        List<User> users = userDao.findAll();
        System.out.println(user);

        System.out.println(userDao.findById(1));

    }

}
