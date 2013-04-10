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

import dboperation.TagDb;
import model.Tag;
/**
 *
 * @author Kevin Alfianto
 */
public class AddTag extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TagDb dboperation;

    public AddTag() {
        super();
        dboperation = new TagDb();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tag tag = new Tag();
        tag.setIdtugas(request.getParameter("idtugas"));
		tag.setIsitag(request.getParameter("isitag"));
        dboperation.addTag(tag);  
        RequestDispatcher view = request.getRequestDispatcher("/viewtask.jsp");
        view.forward(request, response);
    }
}
