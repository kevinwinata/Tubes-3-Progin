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

import dboperation.KomentarDb;
import dboperation.UserDb;
import java.util.*;
import model.Komentar;
import model.User;
/**
 *
 * @author Mario
 */
public class ListComment extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private KomentarDb dboperation;
    private UserDb userdb;

    public ListComment() {
        super();
        dboperation = new KomentarDb();
        userdb = new UserDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Komentar komentar = new Komentar();
        
        String isikomentar = request.getParameter("q");
        String idtugas = request.getParameter("r");
        String username = request.getParameter("s");

        Date d1 = new Date(); //the midnight, that's the first second of the day.

        if (!isikomentar.equals("")) {
            komentar.setIsikomentar(isikomentar);
            komentar.setIdtugas(idtugas);
            komentar.setUsername(username);
            komentar.setWaktu(d1);
            dboperation.addComment(komentar);
        }
        
        int pagenum = 1;
        if (request.getParameter("pagenum") != null) {
            pagenum = Integer.parseInt(request.getParameter("pagenum"));
        }
        
        int page_rows = 10;
        String max = "LIMIT " + (pagenum-1)*page_rows +","+page_rows;
        
        List<Komentar> komentars = new ArrayList<Komentar>();
        komentars = dboperation.getComment(idtugas);
        List<Komentar> komentarswmax = new ArrayList<Komentar>();
        komentarswmax = dboperation.getCommentWMax(idtugas, max);
        
        int count = komentars.size();
        int last = count / page_rows;
        int lastpage = count % page_rows;
        int previous = 0;
        int next = 0;
        
        if(lastpage!= 0)
        {
            last = last + 1; 
        }
        
        if (pagenum < 1) {
                pagenum = 1;
        } else if (pagenum > last) {
                pagenum = last;
        }
        
        String cresponse = "Jumlah komentar : " + count + "<br>-----------------------------------------";
        
        for (int i=0; i<komentarswmax.size(); i++) {
            //
            User user = new User();
            user = userdb.getUser(komentarswmax.get(i).getUsername());
            cresponse = cresponse + "<img alt=\"\" src=\"" + user.getAvatar() + "\"/> <b>" + user.getUsername() + "</b><br>";
            cresponse = cresponse + komentarswmax.get(i).getIsikomentar() + "<br>";
            if (username.equals(komentarswmax.get(i).getUsername())) {
                cresponse = cresponse + "<a href=\"DeleteComment?q=" + komentarswmax.get(i).getIsikomentar()
                        + "&r=" + komentarswmax.get(i).getIdtugas()
                        + "&s=" + komentarswmax.get(i).getUsername() + "\">(Delete)</a><br><br>";
            }
            else
                cresponse = cresponse + "<br>";
            
            cresponse = cresponse + "[" + komentarswmax.get(i).getWaktu().toString() + "]<br>";
            if (count != 1) {
                    cresponse = cresponse + "-----------------------------------------</p>";
            }
            count--;
        }
        
        // Menunjukkan halaman pencarian
        out.println("<p style='margin-left: 0em;'>");
        out.println(" ========= Page "+pagenum+ " of "+last+" =========<br><br>");
        // Jika pagenum bukan 1 maka ditampilkan link untuk ke First yaitu pagenum 1 dan previous
        if (pagenum == 1 || pagenum == 0) {

        } else {
                out.println("<a href=\"ChangePagenum?pg=1&q=" + idtugas + "\"> <<-First</a>");
                out.println(" ");
                previous = pagenum - 1;
                out.println("<a href=\"ChangePagenum?pg=" + previous + "&q=" + idtugas + "\"> <-Previous</a>");
        }
        out.println(" -- ");
        
        //Jika pagenum bukan last maka ditampilkan next dan last
        if (pagenum == last) {

        } else {
                next = pagenum + 1;
                out.println("<a href=\"ChangePagenum?pg=" + next + "&q=" + idtugas + "\"> Next-></a>");
                out.println(" ");
                out.println("<a href=\"ChangePagenum?pg=" + last + "&q=" + idtugas + "\"> Last->></a>");
        }

        out.println(" ===========================");
        out.println("</p>");
        out.println(cresponse);
        
        out.close();
    }
}