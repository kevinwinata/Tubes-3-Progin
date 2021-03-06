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
import java.io.PrintWriter;
import java.util.List;
/**
 *
 * @author Kevin Alfianto
 */
public class DeleteTag extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TagDb dboperation;

    public DeleteTag() {
        super();
        dboperation = new TagDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tag tag = new Tag();
        tag.setIdtugas(request.getParameter("q"));
		tag.setIsitag(request.getParameter("p"));
        dboperation.deleteTag(tag);  
		PrintWriter out = response.getWriter();
		List<String> result = dboperation.getTagString(tag.getIdtugas());
        for (int i = 0; i < result.size(); i++) {
            if (i < result.size() - 1)
                out.println(result.get(i)+", ");
            else
                out.println(result.get(i));
        }
        out.close();
    }
}
