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
    
    public List<Tag> searchTag(String tag1) {
        List<Tag> tags = new ArrayList<Tag>();
        try {
            Statement statement = connection.createStatement();
			String query = "SELECT isitag FROM tag,tugas WHERE ((upper(isitag) LIKE'%" + tag1 +"%') OR (upper(namatugas) LIKE'%" + tag1 +"%')) AND tag.idtugas = tugas.idtugas";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setIsitag(rs.getString("isitag"));
                tags.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;
    }
	
	public List<Tag> searchTagsLimit(String tag1, String max) {
        List<Tag> tags = new ArrayList<Tag>();
        try {
            Statement statement = connection.createStatement();
			String query = "SELECT isitag FROM tag,tugas WHERE ((upper(isitag) LIKE'%" + tag1 +"%') OR (upper(namatugas) LIKE'%" + tag1 +"%')) AND tag.idtugas = tugas.idtugas "+ max;	
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setIsitag(rs.getString("isitag"));;
                tags.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;
    }
}


