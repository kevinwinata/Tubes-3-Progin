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
import model.Hak;
import util.DbUtil;
/**
 *
 * @author TOSHIBA
 */
public class HakDb {
    private Connection connection;
    
    public HakDb() {
        connection = DbUtil.getConnection();
    }
    
    public void AddHak (Hak hak) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO hak (username, idkategori) VALUES (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, hak.getUsername());
            preparedStatement.setString(2, hak.getIdkategori());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getKategori(String username) {
        ResultSet rs = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idkategori FROM hak WHERE username=?");
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
