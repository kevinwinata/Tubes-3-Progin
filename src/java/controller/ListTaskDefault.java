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
public class ListTaskDefault extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TugasDb dbtugas;
    private TagDb dbtag;
    private AssigneeDb dbassignee;
    
    public ListTaskDefault() {
        super();
        dbtugas = new TugasDb();
        dbtag = new TagDb();
        dbassignee = new AssigneeDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("id").toString();
        PrintWriter out = response.getWriter();
        
        List<Tugas> tugaspribadi = dbtugas.getTugasUsername(username);
        for (int i = 0; i < tugaspribadi.size(); i++) {
            Tugas temp = tugaspribadi.get(i);
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
            out.println("<div id=\"status"+temp.getIdtugas()+"\" name=\"statustugas\">Status : <input type=checkbox name=\"status\" value=\"done\" "+status+"/ onchange=\"ChangeStatus("+temp.getIdtugas()+")\"></div>");
            out.println("</div><button onclick=\"location.href='DeleteTugas?q="+temp.getIdtugas()+"'\">Hapus Task...</button></div>");
        }
        
        List<String> idtugasassign = dbassignee.getIdTugas(username);
        List<Tugas> tugasassign = dbtugas.getTugasIdtugas(idtugasassign);
        for (int i = 0; i < tugasassign.size(); i++) {
            Tugas temp = tugasassign.get(i);
            out.println("<div class=task_block><div class=task_judul><a href=\"viewtask.php?q="+temp.getIdtugas()+"\">"
                    +temp.getNamatugas()+"</a></div><div class=task_deadline> Deadline : "
                    +temp.getDeadline().toString()+"</div><div class=task_tag>Tags: ");
            List<String> tagassign = dbtag.getTagString(temp.getIdtugas());
            for (int j = 0; j < tagassign.size(); j++) {
                out.print(tagassign.get(j)+"");
                if (j < (tagassign.size() - 1)) 
                    out.println(", ");
            }
            String status = "";
            if (temp.getStatus().equals("done"))
                status += "checked";
            out.println("<div id=\"status"+temp.getIdtugas()+"\" name=\"statustugas\">Status : <input type=checkbox name=\"status\" value=\"done\" "+status+"/ onchange=\"ChangeStatus("+temp.getIdtugas()+")\"></div>");
            out.println("</div><button onclick=\"location.href='DeleteTugas?q="+temp.getIdtugas()+"'\">Hapus Task...</button></div>");
        }
        out.close();
    }
}
