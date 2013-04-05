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
public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDb dboperation;

    public Search() {
        super();
        dboperation = new UserDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String find = request.getParameter("find");
		String field = request.getParameter("field");
		PrintWriter out = response.getWriter();
		if ((field.equals("semua")) || (field.equals("username")))
		{
			User result = new User();
			List<User> users = new ArrayList<User>();
			users = dboperation.searchUsers(find);
			if(!users.isEmpty())
			{
				while(!users.isEmpty())
				{	
					result = users.remove(0);
					out.println("<font color=\"green\">Username : "+ result.getUsername() +"</font><br>");
					out.println("<font color=\"green\">Fullname : "+ result.getFullname() +"</font><br>");
					out.println("<img src=\""+ result.getAvatar() + "\" alt=\"\" / height=\"100\" width=\"100\"><br>");
					
				}
			}
			else
			{
				out.println("<font color=\"red\">Username tidak ditemukan</font>");
			}
			out.close();
		}
    }
}
