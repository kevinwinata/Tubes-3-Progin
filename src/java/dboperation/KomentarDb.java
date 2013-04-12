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
    
    public List<Komentar> getComment (String idtugas) {
        List<Komentar> komentars = new ArrayList<Komentar>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM komentar WHERE idtugas=" + idtugas + " order by waktu DESC ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Komentar komentar = new Komentar();
                komentar.setIdtugas(rs.getString("idtugas"));
                komentar.setUsername(rs.getString("username"));
                komentar.setIsikomentar(rs.getString("isikomentar"));
                komentar.setWaktu(rs.getDate("waktu"));
                komentars.add(komentar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return komentars;
    }
    
    public List<Komentar> getCommentWMax (String idtugas, String max) {
        List<Komentar> komentars = new ArrayList<Komentar>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM komentar WHERE idtugas=" + idtugas + " order by waktu DESC " + max;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Komentar komentar = new Komentar();
                komentar.setIdtugas(rs.getString("idtugas"));
                komentar.setUsername(rs.getString("username"));
                komentar.setIsikomentar(rs.getString("isikomentar"));
                komentar.setWaktu(rs.getDate("waktu"));
                komentars.add(komentar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return komentars;
    }
    
    public void addComment(Komentar komentar) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into komentar(idtugas,username,isikomentar,waktu) values (?, ?, ?, CURRENT_TIMESTAMP )");
            // Parameters start with 1
            preparedStatement.setString(1, komentar.getIdtugas());
            preparedStatement.setString(2, komentar.getUsername());
            preparedStatement.setString(3, komentar.getIsikomentar());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void deleteComment(String username, String isikomentar, String idtugas) {
        try {
            PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM komentar WHERE username=? and isikomentar=? and idtugas=?");
            // Parameters start with 1
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, isikomentar);
            preparedStatement.setString(3, idtugas);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteByIdtugas(String idtugas) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM komentar WHERE idtugas = ?");
            // Parameters start with 1
            preparedStatement.setString(1, idtugas);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
