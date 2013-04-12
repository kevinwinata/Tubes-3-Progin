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
import java.io.PrintWriter;
import java.util.List;
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Assignee assignee = new Assignee();
        assignee.setIdtugas(request.getParameter("q"));
        assignee.setUsername(request.getParameter("p"));
        dboperation.addAssignee(assignee);
        PrintWriter out = response.getWriter();
        List<String> as = dboperation.getUsername(assignee.getIdtugas());
        for (int i = 0; i < as.size(); i++) {
            if (i < as.size() - 1)
                out.println("<a href=\"profilesearch.jsp?idsearch="+as.get(i)+"\">"+as.get(i)+"</a>\", ");
            else
                out.println("<a href=\"profilesearch.jsp?idsearch="+as.get(i)+"\">"+as.get(i)+"</a>");
        }
        out.close();
    }
    
}
