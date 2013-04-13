<%-- 
    Document   : profile
    Created on : Apr 6, 2013, 2:40:14 PM
    Author     : Mario
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dboperation.TagDb"%>
<%@page import="model.Tag"%>
<%@page import="dboperation.TugasDb"%>
<%@page import="model.Tugas"%>
<%@page import="dboperation.UserDb"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("id") == null) {
            RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
            view.forward(request, response);
        }
    %>
    <head>
        <title>Profile</title>
        <link href="styles/profile.css" rel="stylesheet" type="text/css" />
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
    </head>

    <script>
        function makeTgl(){
            for(var i=1; i<=31; i++){
                var isi=document.createTextNode(i);
                var opsi = document.createElement("option");
                opsi.setAttribute("value",i);
                opsi.appendChild(isi);
                document.getElementById("tgl").appendChild(opsi);
            }
        }

        function makeThn(){
            for(var i=1955; i<=2013; i++){
                var isi=document.createTextNode(i);
                var opsi = document.createElement("option");
                opsi.setAttribute("value",i);
                opsi.appendChild(isi);
                document.getElementById("thn").appendChild(opsi);
            }
        }
    </script>

    <body onLoad="makeTgl();makeThn();">

        <div id="container">
            <div id="header">
                <div class=logo id="logo">
                    <a href="dashboard.jsp"><img src="images/logo.png" title="Home" alt="Home"/></a>
                </div>
                <div id="space">
                </div>
                <div class = "menu" id = "search">
                    <form name="search" method="post" action="search.jsp">
                        Search for: <input type="text" name="find" /> in 
                        <Select NAME="field">
                            <Option VALUE="semua">Semua</option>
                            <Option VALUE="username">Username</option>
                            <Option VALUE="namakategori">Judul Kategori</option>
                            <Option VALUE="tasktag">Task atau Tag</option>
                        </Select>
                        <input type="hidden" name="searching" value="yes" />
                        <button type="submit" id="searchbutton"></button>
                    </form>
                </div>
                <div class="menu" id="logout" action="logout.php">
                    <a href="Logout">Logout</a>
                </div>
                <div class="menu" id="home">
                    <a href="dashboard.jsp">Home</a>
                </div>
                <div class="menu" id="profile">
                    <a href="profile.jsp">
                        <img alt="" height=25 width=25 src="
                        <%
                            session = request.getSession();
                            UserDb userdb = new UserDb();
                            User user = new User();
                            User userc = new User();

                            user = userdb.getUser(request.getParameter("idsearch").toString());
                            userc = userdb.getUser(session.getAttribute("id").toString());
                            out.print(userc.getAvatar());
                        %>
                        ">
                        <%
                            out.print(userc.getUsername());
                        %>
                    </a>
                </div>

            </div>
            <div id="profilearea">
                <div class="profilephoto">
                    <img alt ="" src="
                    <%
                        out.print(user.getAvatar());
                    %>
                    ">
                </div>
                <div class="biodata">
                    <%
                        out.print("</br>");
                        out.print("Username : " + user.getUsername() + "</br></br>");
                        out.print("Fullname : " + user.getFullname() + "</br></br>");
                        out.print("Birthdate : " + user.getBirthdate() + "</br></br>");
                        out.print("Handphone : " + user.getPhonenumber() + "</br></br>");
                        out.print("Email : " + user.getEmail());
                    %>
                    
                </div>
            </div>
            <div id="listarea">
                <div id="undonetasktitle">
                    Undone Task
                </div>
                <div id="donetasktitle">
                    Done Task
                </div>
                <div id="undonetask">
                    <%
                        TugasDb tugasdb = new TugasDb();
                        TagDb tagdb = new TagDb();
                        List<Tugas> listtugas = new ArrayList<Tugas>();

                        listtugas = tugasdb.getTugas((session.getAttribute("id").toString()),"undone");
                        
                        for (int i=0; i<listtugas.size(); i++) {
                            out.print("<div class=\"task_block\">");
                            
                            out.print("<div class=\"task_judul\">");
                            out.print(listtugas.get(i).getNamatugas());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_deadline\">");
                            out.print("Deadline : " + listtugas.get(i).getDeadline().toString());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_tag\">");
                            out.print("Tag : ");
                            List<Tag> listtag = new ArrayList<Tag>();
                            listtag = tagdb.getTag(listtugas.get(i).getIdtugas());
                            for (int j=0; j<listtag.size(); j++) {
                                if (j == listtag.size()-1)
                                    out.print(listtag.get(j).getIsitag());
                                else
                                    out.print(listtag.get(j).getIsitag() + ", ");
                            }
                            out.print("</div>");
                            
                            out.print("</div>");
                        }
                        
                        listtugas.clear();
                        listtugas = tugasdb.getTugasByAssignee((session.getAttribute("id").toString()),"undone");
                        
                        for (int i=0; i<listtugas.size(); i++) {
                            out.print("<div class=\"task_block\">");
                            
                            out.print("<div class=\"task_judul\">");
                            out.print(listtugas.get(i).getNamatugas());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_deadline\">");
                            out.print("Deadline : " + listtugas.get(i).getDeadline().toString());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_tag\">");
                            out.print("Tag : ");
                            List<Tag> listtag = new ArrayList<Tag>();
                            listtag = tagdb.getTag(listtugas.get(i).getIdtugas());
                            for (int j=0; j<listtag.size(); j++) {
                                if (j == listtag.size()-1)
                                    out.print(listtag.get(j).getIsitag());
                                else
                                    out.print(listtag.get(j).getIsitag() + ", ");
                            }
                            out.print("</div>");
                            
                            out.print("</div>");
                        }
                    %>
                </div>
                <div id="donetask">
                    <%
                        listtugas.clear();
                        listtugas = tugasdb.getTugas((session.getAttribute("id").toString()),"done");
                        
                        for (int i=0; i<listtugas.size(); i++) {
                            out.print("<div class=\"task_block\">");
                            
                            out.print("<div class=\"task_judul\">");
                            out.print(listtugas.get(i).getNamatugas());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_deadline\">");
                            out.print("Deadline : " + listtugas.get(i).getDeadline().toString());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_tag\">");
                            out.print("Tag : ");
                            List<Tag> listtag = new ArrayList<Tag>();
                            listtag = tagdb.getTag(listtugas.get(i).getIdtugas());
                            for (int j=0; j<listtag.size(); j++) {
                                if (j == listtag.size()-1)
                                    out.print(listtag.get(j).getIsitag());
                                else
                                    out.print(listtag.get(j).getIsitag() + ", ");
                            }
                            out.print("</div>");
                            
                            out.print("</div>");
                        }
                        
                        listtugas.clear();
                        listtugas = tugasdb.getTugasByAssignee((session.getAttribute("id").toString()),"done");
                        
                        for (int i=0; i<listtugas.size(); i++) {
                            out.print("<div class=\"task_block\">");
                            
                            out.print("<div class=\"task_judul\">");
                            out.print(listtugas.get(i).getNamatugas());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_deadline\">");
                            out.print("Deadline : " + listtugas.get(i).getDeadline().toString());
                            out.print("</div>");
                            
                            out.print("<div class=\"task_tag\">");
                            out.print("Tag : ");
                            List<Tag> listtag = new ArrayList<Tag>();
                            listtag = tagdb.getTag(listtugas.get(i).getIdtugas());
                            for (int j=0; j<listtag.size(); j++) {
                                if (j == listtag.size()-1)
                                    out.print(listtag.get(j).getIsitag());
                                else
                                    out.print(listtag.get(j).getIsitag() + ", ");
                            }
                            out.print("</div>");
                            
                            out.print("</div>");
                        }
                    %>
                </div>
            </div>
    </body>
</html>