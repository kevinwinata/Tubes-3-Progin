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

import dboperation.AssigneeDb;
import model.Assignee;
/**
 *
 * @author Mario
 */
public class AddAssignee extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AssigneeDb dboperation;

    public AddAssignee() {
        super();
        dboperation = new AssigneeDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Assignee assignee = new Assignee();
        assignee.setIdtugas(request.getParameter("idtugas"));
        assignee.setUsername(request.getParameter("username"));
        dboperation.addAssignee(assignee);
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }
    
}
