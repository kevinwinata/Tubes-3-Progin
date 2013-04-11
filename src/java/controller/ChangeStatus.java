/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import dboperation.TugasDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Tugas;

/**
 *
 * @author Kevin
 */
public class ChangeStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TugasDb dboperation;

    public ChangeStatus() {
        super();
        dboperation = new TugasDb();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idtugas = request.getParameter("q");
        Tugas tugas = dboperation.getTugasById(idtugas);
        PrintWriter out = response.getWriter();
        
        if (tugas.getStatus().equalsIgnoreCase("done")) {
            dboperation.updateStatus(idtugas, "undone");
        }
        else {
            dboperation.updateStatus(idtugas, "done");
        }
        
        out.close();
    }
}
