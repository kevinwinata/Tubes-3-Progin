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
public class DeleteTugas  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AssigneeDb dbassignee;
    private AttachmentDb dbattachment;
    private KomentarDb dbkomentar;
    private TagDb dbtag;
    private TugasDb dbtugas;
    
    public DeleteTugas() {
        super();
        dbassignee = new AssigneeDb();
        dbattachment = new AttachmentDb();
        dbkomentar = new KomentarDb();
        dbtag = new TagDb();
        dbtugas = new TugasDb();
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idtugas = request.getParameter("q");

        dbtag.deleteByIdtugas(idtugas);
        dbassignee.deleteByIdtugas(idtugas);
        dbattachment.deleteByIdtugas(idtugas);
        dbkomentar.deleteByIdtugas(idtugas);

        dbtugas.deleteByIdkategori(idtugas);
        
        
        RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
        view.forward(request, response);
     }
    
}
