/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Mario
 */
public class ChangePagenum extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangePagenum() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String pg = request.getParameter("pg");
    String id = request.getParameter("q");

    session.setAttribute("pagenum", pg);

    RequestDispatcher view = request.getRequestDispatcher("/viewtask.jsp");
    request.setAttribute("q", id);
    view.forward(request, response);
    }
}
