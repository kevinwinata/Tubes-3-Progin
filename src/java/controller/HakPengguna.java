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
import java.util.List;
import model.User;
/**
 *
 * @author Kevin
 */
public class HakPengguna extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public HakPengguna() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Returns a writer to write to the browser
        PrintWriter out = response.getWriter();
        //Writes the string to the browser.
        List<User> pengguna = dboperation.getAllUsers();
        for (int i = 0; i < pengguna.size(); i++) {
            User temp = pengguna.get(i);
            out.println("<div class=gambar_kecil><img src="+temp.getAvatar() +" width=50px height=50px><input type=checkbox name=\"user_berhak\" value=\""+temp.getUsername()+"\"/></div>");
        }
        out.close();
    }
}
