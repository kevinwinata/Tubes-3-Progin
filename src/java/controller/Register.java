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

import dboperation.UserDb;
import model.User;
/**
 *
 * @author Mario
 */
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public Register() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFullName(request.getParameter("fullname"));
        try {
            String createdate = request.getParameter("bulan")+"/"+request.getParameter("tanggal")+"/"+request.getParameter("tahun");
            Date birthdate = new SimpleDateFormat("MM/dd/yyyy").parse(createdate);
            user.setBirthdate(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        
        String filename = new String();
        for (String cd : request.getPart("avatar").getHeader("content-disposition").split(";")) {
        if (cd.trim().startsWith("filename")) {
            String prefilename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            filename = prefilename.substring(prefilename.lastIndexOf('/') + 1).substring(prefilename.lastIndexOf('\\') + 1); // MSIE fix.
            filename = "upload/" + filename;
            }
        }
        user.setAvatar(filename);

        dboperation.addUser(user);
        
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }
    
}
