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
public class ListUsername extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public ListUsername() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("q");
        String type = request.getParameter("t");
        
        boolean isuserexist = dboperation.isUserExist(username);
        
        // Returns a writer to write to the browser
        PrintWriter out = response.getWriter();
        //Writes the string to the browser.
        if (type.equals("reg")) {
            if (!isuserexist)
                out.println("<font color=\"green\">Benar</font>");
            else
                out.println("<font color=\"red\">Username telah terpakai, silahkan coba username lain</font>");
        } else {
            if (!isuserexist)
                out.println("<font color=\"red\">Username tidak ditemukan</font>");
            else
                out.println("<font color=\"green\">Username ditemukan</font>");
        }
        out.close();
    }
}
