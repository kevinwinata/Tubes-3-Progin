/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dboperation.TugasDb;
import dboperation.TagDb;
import dboperation.AssigneeDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.User;
import model.Tugas;
import model.Assignee;
import model.Tag;
/**
 *
 * @author Kevin
 */
public class ListTask extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TugasDb dbtugas;
    private TagDb dbtag;
    private AssigneeDb dbassignee;
    
    public ListTask() {
        super();
        dbtugas = new TugasDb();
        dbtag = new TagDb();
        dbassignee = new AssigneeDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("id").toString();
        String kategori = request.getParameter("q");
        PrintWriter out = response.getWriter();
        
        List<Tugas> tugaskategori = dbtugas.getTugasKategori(kategori);
        for (int i = 0; i < tugaskategori.size(); i++) {
            Tugas temp = tugaskategori.get(i);
            out.println("<div class=task_block><div class=task_judul><a href=\"viewtask.php?q="+temp.getIdtugas()+"\">"
                    +temp.getNamatugas()+"</a></div><div class=task_deadline> Deadline : "
                    +temp.getDeadline().toString()+"</div><div class=task_tag>Tags: ");
            List<String> tagpribadi = dbtag.getTagString(temp.getIdtugas());
            for (int j = 0; j < tagpribadi.size(); j++) {
                out.print(tagpribadi.get(j)+"");
                if (j < (tagpribadi.size() - 1)) 
                    out.println(", ");
            }
            String status = "";
            if (temp.getStatus().equals("done"))
                status += "checked";
            out.println("<div>Status : <input type=checkbox name=\"status\" value=\"done\" "+status+"/ onchange=\"location.href='changestatus.php?q="+temp.getIdtugas()+"'\"></div>");
            if (username.equals("moonray"))
                out.println("</div><button onclick=\"location.href='DeleteTugas?q="+temp.getIdtugas()+"'\">Hapus Task...</button></div>");
            else
                out.println("</div></div>");
        }
        out.close();
    }
}
