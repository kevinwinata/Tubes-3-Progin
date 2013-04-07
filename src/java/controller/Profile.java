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
import model.User;
/**
 *
 * @author Mario
 */
public class Profile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public Profile() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = dboperation.getUser(session.getAttribute("id").toString());

        RequestDispatcher view = request.getRequestDispatcher("/profile.jsp");
        request.setAttribute("user", user);
        
        view.forward(request, response);
    }
}
