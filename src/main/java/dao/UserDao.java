package dao;

import config.Database;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    private Database db;

public UserDao(Database database){

    this.db = database;
}
    // insert
    public void save (User user) throws SQLException {

        Connection connection = db.getConnection();         //polaczenie zbaza


        PreparedStatement ps =
                connection.prepareStatement(
                        "INSERT INTO user (first_name, last_name, email)"
                                + "VALUES (?, ?, ?)"

                );
        ps.setString(1, user.getFirstName());       //dzieki temu odparnosc na sqlinjection
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());





        ps.executeUpdate() ;                                         //bo zmieniamy baze danych





    }

    //update

    public  void update(User  user) throws SQLException{

        String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?"
        + " WHERE id = ?";              // musi  bycspacja przed where

        Connection connection = db.getConnection();         //polaczenie zbaza


        PreparedStatement ps =
                connection.prepareStatement(sql);

        ps.setString(1, user.getFirstName());       //dzieki temu odparnosc na sqlinjection
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        ps.setInt(4, user.getId());

        ps.executeUpdate();




    }
    //delete

    public  void delete(int id) throws SQLException{                    //bo nie potrzebujemy calego uzytkownika

   Connection connection = db.getConnection();

    String sql = "DELETE FROM user WHERE id =? ";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setInt(1,id);
    ps.executeUpdate();

    }
    //findbyall
    public List<User> findAll() throws SQLException{
    Connection connection = db.getConnection();
    String sql = "SELECT id, first_name, last_name, email FROM user";
    PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();                      //zwraca wyniki
        List<User> users = new ArrayList<User>();

    while (resultSet.next()){
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
    }


        return users;
    }                                         // lista,bo wiecej userow


    //findbyid
    public User findById(int id) throws SQLException{
        Connection connection = db.getConnection();
        String sql = "SELECT id, first_name, last_name, email FROM user where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
       ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();                      //zwraca wyniki


        if (resultSet.next()){

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            return new User(id, firstName,lastName, email);
        }


        return null;
    }


}
