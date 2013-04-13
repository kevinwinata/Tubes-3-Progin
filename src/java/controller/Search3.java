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

import dboperation.TugasDb;
import dboperation.TagDb;
import dboperation.AssigneeDb;
import javax.servlet.http.HttpSession;
import model.Tugas;
import model.Tag;
/**
 *
 * @author Kevin Alfianto
 */
public class Search3 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TugasDb dbtugas;
    private TagDb dbtag;
    private AssigneeDb dbassignee;

    public Search3() {
        super();
        dbtugas = new TugasDb();
	dbtag = new TagDb();
        dbassignee = new AssigneeDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String find = request.getParameter("find");
		String field = request.getParameter("field");
                HttpSession session = request.getSession();
                String username = session.getAttribute("id").toString();
		int totalCari = 0;
		PrintWriter out = response.getWriter();
		if ((field.equals("semua")) || (field.equals("tasktag")))
		{
			Tugas result1 = new Tugas();
			Tag result2 = new Tag();
            int pagenum = 0;
			List<Tugas> tugass = new ArrayList<Tugas>();
			List<Tag> tags = new ArrayList<Tag>();
			tugass = dbtugas.searchTugas(find);
			tags = dbtag.searchTag(find);
			totalCari = tugass.size();
			if((!tugass.isEmpty()) || (!tags.isEmpty()))
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
					tugass = dbtugas.searchTugasLimit(find,max);
					tags = dbtag.searchTagsLimit(find,max);
					while(!tugass.isEmpty())
					{	
						result1 = tugass.remove(0);
						result2 = tags.remove(0);
						out.println("<div id=\"isi1\">");
						out.println("<p style='margin-left: 1em;'>Nama Task : <a href=\"viewtasksearch.jsp?q="+result1.getIdtugas()+"\">"+ result1.getNamatugas() +"</a></p>");
						out.println("<p style='margin-left: 1em;'>Tanggal Deadline : "+ result1.getDeadline() +"</p>");
						out.println("<p style='margin-left: 1em;'>Tag : "+ result2.getIsitag() +"</p>");
                                                out.println("<p style='margin-left: 1em;'>Status : "+ result1.getStatus() +"</p>");
                                                String assigned = dbassignee.getUser(result1.getIdtugas(), username);
                                                if (username.equals(result1.getUsername()) || username.equals(assigned)) {
                                                    String status = "";
                                                    if (result1.getStatus().equalsIgnoreCase("done"))
                                                        status += "checked";
                                                    out.println("<div id=\"status\" name=\"statustugas\"><input type=checkbox name=\"status\" value=\"done\" "+status+"/ onchange=\"ChangeStatus("+result1.getIdtugas()+")\"></div>");
                                                }
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
						
						out.println("<a href=\"#\" onclick = \"searchWord3(1); return false;\" > <<-First</a>");
						out.println(" ");
						previous = pagenum - 1;
						out.println("<a href=\"#\" onclick = \"searchWord3("+ previous +"); return false;\" > <-Previous</a>");
					}

					out.println(" ---- ");

					
					//Jika pagenum bukan last maka ditampilkan next dan last

					if (pagenum == lastpage) {
						
					} else {
						next = pagenum + 1;
						out.println("<a href=\"#\" onclick = \"searchWord3("+ next +"); return false;\" > Next-></a>");
						out.println(" ");
						out.println("<a href=\"#\" onclick = \"searchWord3("+ lastpage +"); return false;\" > Last->></a>");
					}
					out.println("</p>");
					
			}	
			out.println("<font color=\"red\">Total Pencarian : "+ totalCari +"</font>");
		}
			out.close();
		}
    }
