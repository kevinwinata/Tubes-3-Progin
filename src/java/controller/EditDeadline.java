/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
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
public class EditDeadline extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TugasDb dboperation;

    public EditDeadline() {
        super();
        dboperation = new TugasDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
        Tugas tugas = new Tugas();
		try {
            String createdate = request.getParameter("bln")+"/"+request.getParameter("tgl")+"/"+request.getParameter("thn");
			Date deadline = new SimpleDateFormat("MM/dd/yyyy").parse(createdate);
			tugas.setIdtugas(request.getParameter("idtugas"));
			tugas.setDeadline(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dboperation.editDeadline(tugas);  
		out.println(tugas.getDeadline().toString());
        RequestDispatcher view = request.getRequestDispatcher("/viewtask.jsp");
        view.forward(request, response);
    }
}
