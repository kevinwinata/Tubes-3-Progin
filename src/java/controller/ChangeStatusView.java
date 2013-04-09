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

import dboperation.TugasDb;
import model.Tugas;
/**
 *
 * @author Kevin Alfianto
 */
public class ChangeStatusView extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TugasDb dboperation;

    public ChangeStatusView() {
        super();
        dboperation = new TugasDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tugas tugas = new Tugas();
        tugas.setIdtugas(request.getParameter("idtugas"));
		tugas.setStatus(request.getParameter("status"));
        dboperation.changeStatusView(tugas);  
        RequestDispatcher view = request.getRequestDispatcher("/viewtask.jsp");
        view.forward(request, response);
    }
}
