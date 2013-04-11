/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dboperation.AttachmentDb;
import dboperation.TagDb;
import dboperation.TugasDb;
import dboperation.AssigneeDb;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Tugas;
import model.Tag;
import model.Attachment;
import model.Assignee;
import controller.ListTask;

/**
 *
 * @author Kevin
 */
public class AddTask extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AttachmentDb dbattachment;
    private TagDb dbtag;
    private TugasDb dbtugas;
    private AssigneeDb dbassignee;
    
    public AddTask() {
        super();
        dbattachment = new AttachmentDb();
        dbtag = new TagDb();
        dbtugas = new TugasDb();
        dbassignee = new AssigneeDb();
    }
    
    public String randomId() {
        String S = "";
        for (int i = 0; i < 10; i++) {
            S += (char)(Math.floor(Math.random()*10)+48);
        }
        return S;
    }
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("id").toString();
        String namatugas = request.getParameter("taskname");
        
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd-MMM-yy");
        String date = request.getParameter("tanggal")+"-"+request.getParameter("bulan")+"-"+request.getParameter("tahun");
        Date deadline = null;
        try {
            deadline = (Date)formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Part filePart = request.getPart("attachment"); // Retrieves <input type="file" name="file">
        String filename = getFilename(filePart);
        String dir = "upload/" + filename;
        byte buf[] = new byte[1024 * 4];
        if (!filename.isEmpty()) {
            FileOutputStream output = new FileOutputStream(getServletContext().getRealPath("/") + "upload/" + filename);
            try {
                InputStream input = filePart.getInputStream();
                try {
                    while (true) {
                        int count = input.read(buf);
                        if (count == -1)
                            break;
                        output.write(buf, 0, count);
                    }
                } finally {
                    input.close();
                }
            } finally {
                output.close();
            }
        }
        String[] tags = request.getParameter("tag").trim().split(",");
        String[] assignees = request.getParameter("assignee").trim().split(",");
        String idtugas;
        do {
            idtugas = randomId();
        }
        while (dbtugas.isIdExist(idtugas));
        
        Tugas tugas = new Tugas();
        tugas.setIdtugas(idtugas);
        tugas.setNamatugas(namatugas);
        tugas.setDeadline(deadline);
        tugas.setIdkategori(ListTask.kategori);
        tugas.setUsername(username);
        tugas.setStatus("undone");
        dbtugas.addTugas(tugas);
        
        Attachment attachment = new Attachment();
        attachment.setIdtugas(idtugas);
        attachment.setIsiattachment(dir);
        dbattachment.addAttachment(attachment);
        
        for (int i = 0; i < tags.length; i++) {
            Tag tag = new Tag();
            tag.setIdtugas(idtugas);
            tag.setIsitag(tags[i]);
            dbtag.addTag(tag);
        }
        
        for (int j = 0; j < assignees.length; j++) {
            Assignee assignee = new Assignee();
            assignee.setIdtugas(idtugas);
            assignee.setUsername(assignees[j].trim());
            dbassignee.addAssignee(assignee);
        }
        
        RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
        view.forward(request, response);
    }
}
