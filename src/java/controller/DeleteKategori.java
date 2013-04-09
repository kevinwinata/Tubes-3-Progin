/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dboperation.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
/**
 *
 * @author Kevin
 */
public class DeleteKategori extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AssigneeDb dbassignee;
    private AttachmentDb dbattachment;
    private KategoriDb dbkategori;
    private HakDb dbhak;
    private KomentarDb dbkomentar;
    private TagDb dbtag;
    private TugasDb dbtugas;
    
    public DeleteKategori() {
        super();
        dbassignee = new AssigneeDb();
        dbattachment = new AttachmentDb();
        dbkategori = new KategoriDb();
        dbhak = new HakDb();
        dbkomentar = new KomentarDb();
        dbtag = new TagDb();
        dbtugas = new TugasDb();
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String idkategori = request.getParameter("q");
         
         List<Tugas> tugaskategori = dbtugas.getTugasKategori(idkategori);
         for (int i = 0; i < tugaskategori.size(); i++) {
             dbtag.deleteByIdtugas(tugaskategori.get(i).getIdtugas());
             dbassignee.deleteByIdtugas(tugaskategori.get(i).getIdtugas());
             dbattachment.deleteByIdtugas(tugaskategori.get(i).getIdtugas());
             dbkomentar.deleteByIdtugas(tugaskategori.get(i).getIdtugas());
         }
         dbtugas.deleteByIdkategori(idkategori);
         dbhak.deleteByIdkategori(idkategori);
         dbkategori.deleteByIdkategori(idkategori);
         
         
        RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
        view.forward(request, response);
     }
}
