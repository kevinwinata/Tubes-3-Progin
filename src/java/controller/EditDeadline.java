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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		
		DateFormat formatter;
        formatter = new SimpleDateFormat("dd-MMM-yy");
        String date = request.getParameter("tgl")+"-"+request.getParameter("bln")+"-"+request.getParameter("thn");
        Date deadline = null;
        try {
            deadline = (Date)formatter.parse(date);
			tugas.setDeadline(deadline);
			tugas.setIdtugas(request.getParameter("q"));
        } catch (ParseException ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
        }
		
        dboperation.editDeadline(tugas);  
		out.println(tugas.getDeadline().toString());
    }
}
