/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dboperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.User;
import util.DbUtil;

/**
 *
 * @author Mario
 */
public class UserDb {
    private Connection connection;
    
    public UserDb() {
        connection = DbUtil.getConnection();
    }
    
    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(username,password,fullname,birthdate,phonenumber,email,avatar) values (?, ?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullname());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthdate().getTime()));
            preparedStatement.setString(5, user.getPhonenumber());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getAvatar());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editUser(User user, String fullname, String password, Date birthdate, String avatar) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update user set fullname=?, password=?, birthdate=?, avatar=? where username=?;");
            // Parameters start with 1
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, password);
            preparedStatement.setDate(3, new java.sql.Date(birthdate.getTime()));
            preparedStatement.setString(4, avatar);
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullname"));
                user.setBirthdate(rs.getDate("birthdate"));
                user.setPhonenumber(rs.getString("phonenumber"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
