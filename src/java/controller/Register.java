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
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.http.*;
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
        HttpSession session = request.getSession();
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
        
        Part filePart = request.getPart("avatar"); // Retrieves <input type="file" name="file">
        String dir = "upload/" + filename;
        byte buf[] = new byte[1024 * 4];
        if (!filename.isEmpty()) {
            FileOutputStream output = new FileOutputStream(getServletContext().getRealPath("/") + filename);
            try {
                InputStream input = filePart.getInputStream();
                try {
                    while (true) {
                        int count = input.read(buf);
                        if (count == -1)
                            break;
                        output.write(buf, 0, count);
                    }
                } finally {
                    input.close();
                }
            } finally {
                output.close();
            }
        }

        dboperation.addUser(user);
        
        session.setAttribute("id", user.getUsername());
        session.setAttribute("pagenum", 1);
        RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
        view.forward(request, response);
    }
    
}
