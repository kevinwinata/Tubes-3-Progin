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
import model.Tugas;
import util.DbUtil;
/**
 *
 * @author Kevin
 */
public class TugasDb {
    private Connection connection;
    
    public TugasDb() {
        connection = DbUtil.getConnection();
    }
    
    public List<Tugas> getTugasUsername(String username) {
        List<Tugas> listtugas = new ArrayList<Tugas>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas WHERE username =?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tugas tugas = new Tugas();
                tugas.setIdtugas(rs.getString("idtugas"));
                tugas.setNamatugas(rs.getString("namatugas"));
                tugas.setDeadline(rs.getDate("deadline"));
                tugas.setIdkategori(rs.getString("idkategori"));
                tugas.setUsername(rs.getString("username"));
                tugas.setStatus(rs.getString("status"));
                listtugas.add(tugas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listtugas;
    }
    
    public List<Tugas> getTugasKategori(String idkategori) {
        List<Tugas> listtugas = new ArrayList<Tugas>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas WHERE idkategori =?");
            preparedStatement.setString(1, idkategori);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tugas tugas = new Tugas();
                tugas.setIdtugas(rs.getString("idtugas"));
                tugas.setNamatugas(rs.getString("namatugas"));
                tugas.setDeadline(rs.getDate("deadline"));
                tugas.setIdkategori(rs.getString("idkategori"));
                tugas.setUsername(rs.getString("username"));
                tugas.setStatus(rs.getString("status"));
                listtugas.add(tugas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listtugas;
    }
    
    public List<Tugas> getTugasIdtugas(List<String> idtugas) {
        List<Tugas> listtugas = new ArrayList<Tugas>();
        try {
            for (int i = 0; i < idtugas.size(); i++) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas WHERE idtugas =?");
                preparedStatement.setString(1, idtugas.get(i));
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                Tugas tugas = new Tugas();
                tugas.setIdtugas(rs.getString("idtugas"));
                tugas.setNamatugas(rs.getString("namatugas"));
                tugas.setDeadline(rs.getDate("deadline"));
                tugas.setIdkategori(rs.getString("idkategori"));
                tugas.setUsername(rs.getString("username"));
                tugas.setStatus(rs.getString("status"));
                listtugas.add(tugas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listtugas;
    }
}
