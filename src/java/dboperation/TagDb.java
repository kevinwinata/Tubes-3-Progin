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
import model.Tag;
import util.DbUtil;
/**
 *
 * @author Kevin
 */
public class TagDb {
    private Connection connection;
    
    public TagDb() {
        connection = DbUtil.getConnection();
    }
    
    public List<String> getTag(String idtugas) {
        List<String> isitag = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT isitag FROM tag WHERE idtugas =?");
            preparedStatement.setString(1, idtugas);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                isitag.add(rs.getString("isitag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isitag;
    }
}
