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
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public Login() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        RequestDispatcher view2 = request.getRequestDispatcher("/dashboard.jsp");
        if (dboperation.checkLogin(user)) {
            view2.forward(request, response);
            session.setAttribute("id", request.getParameter("username"));
        }
        else {
            request.setAttribute("q", "0");
            view.forward(request, response);
        }
    }
}
