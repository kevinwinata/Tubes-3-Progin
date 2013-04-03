/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Date;
/**
 *
 * @author TOSHIBA
 */
public class Tugas {
    private String idtugas;
    private String namatugas;
    private Date deadline;
    private String idkategori;
    private String username;
    private String status;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(String idkategori) {
        this.idkategori = idkategori;
    }

    public String getIdtugas() {
        return idtugas;
    }

    public void setIdtugas(String idtugas) {
        this.idtugas = idtugas;
    }

    public String getNamatugas() {
        return namatugas;
    }

    public void setNamatugas(String namatugas) {
        this.namatugas = namatugas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
