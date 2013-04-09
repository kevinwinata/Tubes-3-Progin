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
            preparedStatement.setString(1, komentar.getIdtugas());
            preparedStatement.setString(2, komentar.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
