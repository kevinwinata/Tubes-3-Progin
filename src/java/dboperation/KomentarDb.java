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
import model.Komentar;
import util.DbUtil;
/**
 *
 * @author Kevin Alfianto
 */
public class KomentarDb {
    private Connection connection;
    
    public KomentarDb() {
        connection = DbUtil.getConnection();
    }
	
	public void deleteComment(Komentar komentar) {
        try {
            PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM komentar WHERE waktu=? and idtugas=?;");
            // Parameters start with 1
            preparedStatement.setDate(1, new java.sql.Date(komentar.getWaktu().getTime()));
            preparedStatement.setString(2, komentar.getIdtugas());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
