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
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public UserController() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFullName(request.getParameter("fullname"));
        try {
            Date birthdate = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("birthdate"));
            user.setBirthdate(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhonenumber(request.getParameter("phonenumber"));
        user.setEmail(request.getParameter("email"));
        user.setAvatar(request.getParameter("avatar"));

        dboperation.addUser(user);
    }
}
