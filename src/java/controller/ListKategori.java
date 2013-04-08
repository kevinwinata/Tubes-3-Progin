/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dboperation.HakDb;
import dboperation.KategoriDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kategori;
/**
 *
 * @author TOSHIBA
 */
public class ListKategori extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private KategoriDb dbkategori;
    private HakDb dbhak;

    public ListKategori() {
        super();
        dbkategori = new KategoriDb();
        dbhak = new HakDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("q");
        PrintWriter out = response.getWriter();
        ResultSet idkategori = dbhak.getKategori(username);
        List<ResultSet> rs = dbkategori.ListKategori(idkategori);
        try {
            for (int i = 0; i < rs.size(); i++) {
                rs.get(i).next();
                out.println("<div class=category_block onclick=showTask("+rs.get(i).getString("idkategori")+")><div class=category_name>"+rs.get(i).getString("namakategori")+"</div></div>");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ListKategori.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }
}
