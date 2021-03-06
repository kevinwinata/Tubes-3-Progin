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

    public void addTugas(Tugas tugas) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tugas(idtugas,namatugas,deadline,idkategori,username,status) values (?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, tugas.getIdtugas());
            preparedStatement.setString(2, tugas.getNamatugas());
            preparedStatement.setDate(3, new java.sql.Date(tugas.getDeadline().getTime()));
            preparedStatement.setString(4, tugas.getIdkategori());
            preparedStatement.setString(5, tugas.getUsername());
            preparedStatement.setString(6, tugas.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Tugas getTugasById(String idtugas) {
        Tugas tugas = new Tugas();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas WHERE idtugas =?");
            preparedStatement.setString(1, idtugas);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            tugas.setIdtugas(rs.getString("idtugas"));
            tugas.setNamatugas(rs.getString("namatugas"));
            tugas.setDeadline(rs.getDate("deadline"));
            tugas.setIdkategori(rs.getString("idkategori"));
            tugas.setUsername(rs.getString("username"));
            tugas.setStatus(rs.getString("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tugas;
    }

    public List<Tugas> getTugas(String username, String status) {
        List<Tugas> listtugas = new ArrayList<Tugas>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas WHERE username=? AND status=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, status);
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

    public List<Tugas> getTugasByAssignee(String username, String status) {
        List<Tugas> listtugas = new ArrayList<Tugas>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas JOIN assignee WHERE assignee.username=? AND status=? AND tugas.idtugas=assignee.idtugas");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, status);
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

    public boolean isIdExist(String idtugas) {
        boolean ret = true;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas WHERE idtugas=?");
            preparedStatement.setString(1, idtugas);
            ResultSet rs = preparedStatement.executeQuery();
            ret = rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Tugas> searchTugas(String tugas1) {
        List<Tugas> tugass = new ArrayList<Tugas>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tag,tugas WHERE ((upper(isitag) LIKE'%" + tugas1 + "%') OR (upper(namatugas) LIKE'%" + tugas1 + "%')) AND tag.idtugas = tugas.idtugas";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tugas tugas = new Tugas();
                tugas.setIdtugas(rs.getString("idtugas"));
                tugas.setNamatugas(rs.getString("namatugas"));
                tugas.setDeadline(rs.getDate("deadline"));
                tugas.setIdkategori(rs.getString("idkategori"));
                tugas.setUsername(rs.getString("username"));
                tugas.setStatus(rs.getString("status"));
                tugass.add(tugas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tugass;
    }

    public List<Tugas> searchTugasLimit(String tugas1, String max) {
        List<Tugas> tugass = new ArrayList<Tugas>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tag,tugas WHERE ((upper(isitag) LIKE'%" + tugas1 + "%') OR (upper(namatugas) LIKE'%" + tugas1 + "%')) AND tag.idtugas = tugas.idtugas " + max;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tugas tugas = new Tugas();
                tugas.setIdtugas(rs.getString("idtugas"));
                tugas.setNamatugas(rs.getString("namatugas"));
                tugas.setDeadline(rs.getDate("deadline"));
                tugas.setIdkategori(rs.getString("idkategori"));
                tugas.setUsername(rs.getString("username"));
                tugas.setStatus(rs.getString("status"));
                tugass.add(tugas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tugass;
    }

    public void editDeadline(Tugas tugas) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update tugas set deadline=? where idtugas=?;");
            // Parameters start with 1
            preparedStatement.setDate(1, new java.sql.Date(tugas.getDeadline().getTime()));
            preparedStatement.setString(2, tugas.getIdtugas());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeStatusView(Tugas tugas) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update tugas set status=? where idtugas=?;");
            // Parameters start with 1
            preparedStatement.setString(1, tugas.getStatus());
            preparedStatement.setString(2, tugas.getIdtugas());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByIdkategori(String idkategori) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tugas WHERE idkategori = ?");
            // Parameters start with 1
            preparedStatement.setString(1, idkategori);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTugas(String idtugas) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tugas WHERE idtugas = ?");
            // Parameters start with 1
            preparedStatement.setString(1, idtugas);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(String idtugas, String status) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tugas SET status=? WHERE idtugas = ?");
            // Parameters start with 1
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, idtugas);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
