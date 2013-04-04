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

import dboperation.UserDb;
import model.User;
/**
 *
 * @author Mario
 */
public class ListEmail extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public ListEmail() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("q");
        
        boolean isemailexist = dboperation.isEmailExist(email);
        
        // Returns a writer to write to the browser
        PrintWriter out = response.getWriter();
        //Writes the string to the browser.
        if (!isemailexist)
            out.println("<font color=\"green\">Benar</font>");
        else
            out.println("<font color=\"red\">Email telah terpakai, silahkan coba email lain</font>");
        
        out.close();
    }
}
