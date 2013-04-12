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
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dboperation.UserDb;
import model.User;
/**
 *
 * @author Kevin
 */
public class Suggestion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public Suggestion() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String suggest = request.getParameter("suggest");
		PrintWriter out = response.getWriter();
		User result = new User();
		List<User> users = new ArrayList<User>();
		users = dboperation.searchUsers(suggest);
		
		out.println("<datalist id=\"user\">");
		while (!users.isEmpty()) {
			result = users.remove(0);
			out.println("<option value=\""+ result.getUsername() +"\">");
			out.println(result.getUsername());
		}
		out.println("</datalist>");
		
		out.close();
	}
}
