/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dboperation.HakDb;
import dboperation.KategoriDb;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Hak;
import model.Kategori;
/**
 *
 * @author Kevin
 */
public class AddKategori extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private KategoriDb dbkategori;
    private HakDb dbhak;
    
    public AddKategori() {
        super();
        dbkategori = new KategoriDb();
        dbhak = new HakDb();
    }
    
    public String randomId() {
        String S = "";
        for (int i = 0; i < 10; i++) {
            S += (char)(Math.floor(Math.random()*10)+48);
        }
        return S;
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Kategori kategori = new Kategori();
        String[] userberhak = request.getParameterValues("user_berhak");
        String currentuser = "moonray";
        
        String idkategori;
        do {
            idkategori = randomId();
        }
        while (dbkategori.isIdExist(idkategori));
        kategori.setIdkategori(idkategori);
        kategori.setNamakategori(request.getParameter("categoryname"));
        dbkategori.AddKategori(kategori);
        
        Hak pembuat = new Hak();
        pembuat.setIdkategori(idkategori);
        pembuat.setUsername(currentuser);
        dbhak.AddHak(pembuat);
        if (userberhak != null) {
            for (int i = 0; i < userberhak.length; i++) {
                if (!userberhak[i].equals(currentuser)) {
                    Hak temp = new Hak();
                    temp.setIdkategori(idkategori);
                    temp.setUsername(userberhak[i]);
                    dbhak.AddHak(temp);
                }
            }
        }
        
        RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
        view.forward(request, response);
    }
}
