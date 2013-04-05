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
import dboperation.KategoriDb;
import model.User;
import model.Kategori;
/**
 *
 * @author Kevin
 */
public class Search2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private KategoriDb dboperation;

    public Search2() {
        super();
        dboperation = new KategoriDb(); 
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String find = request.getParameter("find");
		String field = request.getParameter("field");
		PrintWriter out = response.getWriter();
		if ((field.equals("semua")) || (field.equals("namakategori")))
		{
			Kategori result = new Kategori();
			List<Kategori> kategoris = new ArrayList<Kategori>();
			kategoris = dboperation.searchKategori(find);
			if(!kategoris.isEmpty())
			{
				while(!kategoris.isEmpty())
				{	
					result = kategoris.remove(0);
					out.println("<font color=\"green\">Kategori : "+ result.getNamakategori() +"</font><br>");
					
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
