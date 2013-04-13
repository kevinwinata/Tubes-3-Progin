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
import javax.servlet.http.HttpSession;

import dboperation.UserDb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.http.*;
import model.User;

/**
 *
 * @author Mario
 */
public class EditProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public EditProfile() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = dboperation.getUser(session.getAttribute("id").toString());
        
        if (!request.getParameter("fullname").isEmpty()) {
            user.setFullName(request.getParameter("fullname"));
        }
        
        if (!request.getParameter("password").isEmpty()) {
            user.setPassword(request.getParameter("password"));
        }
        
        if (!((request.getParameter("tanggal").equals("1")) && (request.getParameter("bulan").equals("1")) && (request.getParameter("tahun").equals("1955")))) {
            try {
                String createdate = request.getParameter("bulan")+"/"+request.getParameter("tanggal")+"/"+request.getParameter("tahun");
                Date birthdate = new SimpleDateFormat("MM/dd/yyyy").parse(createdate);
                user.setBirthdate(birthdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
        //InputStream filecontent = filePart.getInputStream();
        //FileOutputStream os = new FileOutputStream(getServletContext().getRealPath("/") + "upload/" + filename);
        
        Part filePart = request.getPart("avatar"); // Retrieves <input type="file" name="file">
        String filename = getFilename(filePart);
        String dir = "upload/" + filename;
        byte buf[] = new byte[1024 * 4];
        if (!filename.isEmpty()) {
            user.setAvatar(dir);
            FileOutputStream output = new FileOutputStream(getServletContext().getRealPath("/") + "upload/" + filename);
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
        
        dboperation.editUser(user.getUsername(), user);

        RequestDispatcher view = request.getRequestDispatcher("/profile.jsp");
        view.forward(request, response);
    }
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
