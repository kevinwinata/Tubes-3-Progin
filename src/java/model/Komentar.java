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
public class Komentar {
    private String idtugas;
    private String username;
    private String isikomentar;
    private Date waktu;

    public String getIdtugas() {
        return idtugas;
    }

    public void setIdtugas(String idtugas) {
        this.idtugas = idtugas;
    }

    public String getIsikomentar() {
        return isikomentar;
    }

    public void setIsikomentar(String isikomentar) {
        this.isikomentar = isikomentar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }
    
}
