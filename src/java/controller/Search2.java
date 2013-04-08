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
 * @author Kevin Alfianto
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
		int totalCari = 0;
		PrintWriter out = response.getWriter();
		if ((field.equals("semua")) || (field.equals("namakategori")))
		{
			Kategori result = new Kategori();
            int pagenum = 0;
			List<Kategori> kategoris = new ArrayList<Kategori>();
			kategoris = dboperation.searchKategori(find);
			totalCari = kategoris.size();
			if(!kategoris.isEmpty())
			{
				
				int pagerow = 10;
				int lastpage = 0;
				int last = 0;
				int previous = 0;
				int next = 0;
				String max = "";
				if (request.getParameter("pagenum").equals("0")) {
					pagenum = 1;
				} else {
				    pagenum = Integer.parseInt(request.getParameter("pagenum").toString());               
				}
				lastpage = totalCari / pagerow;
                                last = totalCari % pagerow;
                                if(last!= 0)
                                {
                                   lastpage = lastpage + 1; 
                                }
                                
				if (pagenum < 1) {
					pagenum = 1;
				} else if (pagenum > lastpage) {
					pagenum = lastpage;
				}
				
				max = "LIMIT " + (pagenum-1)*pagerow +","+pagerow;
				if (totalCari == 0)
				{
				}
				else
				{
					kategoris = dboperation.searchKategorisLimit(find,max);
					while(!kategoris.isEmpty())
					{	
						result = kategoris.remove(0);
						out.println("<div id=\"isi1\">");
						out.println("<p style='margin-left: 1em;'>Kategori : "+ result.getNamakategori() +"</p>");
						out.println("</div>");
					}
				}
				
					// Menunjukkan halaman pencarian
					out.println("<div id=\"hasilcari2\"><p style='margin-left: 5em;'>");
					out.println(" --Page "+ pagenum +" of "+ lastpage +"-- </p>");
					out.println("<p style='margin-left: 5em;'>");
					// Jika pagenum bukan 1 maka ditampilkan link untuk ke First yaitu pagenum 1 dan previous
					if (pagenum == 1 || pagenum == 0) {
						
					} else {
						
						out.println("<a href=\"#\" onclick = \"searchWord2(1); return false;\" > <<-First</a>");
						out.println(" ");
						previous = pagenum - 1;
						out.println("<a href=\"#\" onclick = \"searchWord2("+ previous +"); return false;\" > <-Previous</a>");
					}

					out.println(" ---- ");

					
					//Jika pagenum bukan last maka ditampilkan next dan last

					if (pagenum == lastpage) {
						
					} else {
						next = pagenum + 1;
						out.println("<a href=\"#\" onclick = \"searchWord2("+ next +"); return false;\" > Next-></a>");
						out.println(" ");
						out.println("<a href=\"#\" onclick = \"searchWord2("+ lastpage +"); return false;\" > Last->></a>");
					}
					out.println("</p>");
					
			}	
			out.println("<font color=\"red\">Total Pencarian : "+ totalCari +"</font>");
		}
			else
			{
				out.println("<font color=\"red\">Total Pencarian : "+ totalCari +"</font>");
			}
			out.close();
		}
    }
