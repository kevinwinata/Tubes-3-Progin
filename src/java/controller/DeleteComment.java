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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isikomentar = request.getParameter("q");
        String idtugas = request.getParameter("r");
        String username = request.getParameter("s");
        dboperation.deleteComment(username, isikomentar, idtugas);  
        
        RequestDispatcher view = request.getRequestDispatcher("/viewtask.jsp?q=" + idtugas);
        view.forward(request, response);
    }
}
