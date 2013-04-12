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
import model.Attachment;
import model.Tag;
import util.DbUtil;
/**
 *
 * @author Kevin
 */
public class AttachmentDb {
    private Connection connection;
    
    public AttachmentDb() {
        connection = DbUtil.getConnection();
    }
    
    public List<Attachment> getAttachment(String idtugas) {
        List<Attachment> listatt = new ArrayList<Attachment>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM attachment WHERE idtugas=?");
            preparedStatement.setString(1, idtugas);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Attachment attachment = new Attachment();
                attachment.setIdtugas(rs.getString("idtugas"));
                attachment.setIsiattachment(rs.getString("isiattachment"));
                listatt.add(attachment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listatt;
    }
    
    public void addAttachment(Attachment attachment) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into attachment(idtugas,isiattachment) values (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, attachment.getIdtugas());
            preparedStatement.setString(2, attachment.getIsiattachment());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteByIdtugas(String idtugas) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM attachment WHERE idtugas = ?");
            // Parameters start with 1
            preparedStatement.setString(1, idtugas);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
