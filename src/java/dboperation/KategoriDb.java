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
public class KategoriDb {
    private Connection connection;
    
    public KategoriDb() {
        connection = DbUtil.getConnection();
    }
    
    public List<ResultSet> ListKategori(ResultSet id) {
        List<ResultSet> rs = new ArrayList<ResultSet>();
        try {
            while (id.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kategori WHERE idkategori=?");
                preparedStatement.setString(1, id.getString("idkategori"));
                ResultSet temp = preparedStatement.executeQuery();
                rs.add(temp);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
