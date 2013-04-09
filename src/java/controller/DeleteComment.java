/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dboperation.KomentarDb;
import model.Komentar;
/**
 *
 * @author Kevin Alfianto
 */
public class DeleteComment extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private KomentarDb dboperation;

    public DeleteComment() {
        super();
        dboperation = new KomentarDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Komentar komentar = new Komentar();
        komentar.setIdtugas(request.getParameter("idtugas"));
		try {
            String createdate = request.getParameter("bulan")+"/"+request.getParameter("tanggal")+"/"+request.getParameter("tahun");
            Date waktu = new SimpleDateFormat("MM/dd/yyyy").parse(createdate);
            komentar.setWaktu(waktu);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dboperation.deleteComment(komentar);  
        RequestDispatcher view = request.getRequestDispatcher("/viewtask.jsp");
        view.forward(request, response);
    }
}
