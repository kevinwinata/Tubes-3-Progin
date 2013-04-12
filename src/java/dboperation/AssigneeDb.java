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
import model.Assignee;
import util.DbUtil;
/**
 *
 * @author Kevin
 */
public class AssigneeDb {
    private Connection connection;
    
    public AssigneeDb() {
        connection = DbUtil.getConnection();
    }
    
    public List<String> getUsername(String idtugas) {
        List<String> username = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM assignee WHERE idtugas =?");
            preparedStatement.setString(1, idtugas);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                username.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
    
    public List<String> getIdTugas(String username) {
        List<String> idtugas = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idtugas FROM assignee WHERE username =?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                idtugas.add(rs.getString("idtugas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idtugas;
    }
    
    public String getUser(String idtugas, String username) {
        String user = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM assignee WHERE idtugas =? AND username =?");
            preparedStatement.setString(1, idtugas);
            preparedStatement.setString(2, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user += rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public void addAssignee(Assignee assignee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into assignee(username,idtugas) values (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, assignee.getUsername());
            preparedStatement.setString(2, assignee.getIdtugas());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void deleteAssignee(Assignee assignee) {
        try {
            PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM assignee WHERE idtugas=? and username=?;");
            // Parameters start with 1
            preparedStatement.setString(1, assignee.getIdtugas());
            preparedStatement.setString(2, assignee.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteByIdtugas(String idtugas) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM assignee WHERE idtugas = ?");
            // Parameters start with 1
            preparedStatement.setString(1, idtugas);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
