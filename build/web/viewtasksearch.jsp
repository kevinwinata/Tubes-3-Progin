<%-- 
    Document   : viewtask
    Created on : Apr 11, 2013, 10:21:35 AM
    Author     : TOSHIBA
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dboperation.*"%>
<%@page import="model.*"%>
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
        <title>View Task</title>
        <link href="styles/viewtask.css" rel="stylesheet" type="text/css" />
        <link href="styles/header.css" rel="stylesheet" type="text/css" />

        <script>
            var attach = "videos/attachmentsample.ogg";
            var tanggal = 1;
            var bulan = "Januari";
            var tahun = 2013;
			
            function ChangeStatus(uidtugas) {
                var xmlhttp;
                var selection = document.getElementById("status");
                if (window.XMLHttpRequest) {
                    // code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }
                else {
                    // code for IE6, IE5
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange=function() {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                        
                    }
                }
                xmlhttp.open("GET","ChangeStatus?q="+uidtugas,true);
                xmlhttp.send();
            }
			
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
			
            function getStyle(el, name)
            {
                if ( document.defaultView && document.defaultView.getComputedStyle )
                {
                    var style = document.defaultView.getComputedStyle(el, null);
                    if ( style )
                        return style[name];
                }
                else if ( el.currentStyle )
                    return el.currentStyle[name];

                return null;
            }

            function showHide(a){
                var e=document.getElementById(a);
                if(!e)return true;
                if(getStyle(e, "display") == "none"){
                    e.style.display="inline"
                } else {
                    e.style.display="none"
                }
                return true;
            }
			
            function renameButton(id){
                var elem = document.getElementById(id);
                if (elem.value=="Edit") elem.value = "Cancel";
                else elem.value = "Edit";
            }
			
            function renameButton2(id){
                var elem = document.getElementById(id);
                if (elem.value=="Add") elem.value = "Cancel";
                else elem.value = "Add";
            }
			
            function clearContents(id) {
                document.getElementById(id).value = '';
            }
			
            function editValue(id1,id2){
                var value = document.getElementById(id1).value;
                document.getElementById(id2).innerHTML = value;
            }
			
            function setDateValue() {
                document.getElementById("dlvalue").innerHTML = tanggal+"-"+bulan+"-"+tahun;
            }
			
            function getDateValue() {
                tanggal = document.edit.tgl.value;
                bulan = document.edit.bln.value;
                tahun = document.edit.thn.value;
            }
			
            function checkEdit(e,id1,id2,id3) {
                if (e && e.keyCode == 13) {
                    editValue(id1,id2);
                    showHide(id1);
                    showHide(id2);
                    renameButton(id3);
                }
            }
			
            function showComment(comment, idtask, username){
                var xmlhttp;
                if (window.XMLHttpRequest) {
                    // code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }
                else {
                    // code for IE6, IE5
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange=function() {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                        document.getElementById("comment").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","ListComment?q="+comment+"&r="+idtask+"&s="+username,true);
                xmlhttp.send();
            }
			
            function myFunction(comment, idtask, username, pagenum)
            {
                setInterval(function() {
                    var xmlhttp;
                    if (window.XMLHttpRequest) {
                        // code for IE7+, Firefox, Chrome, Opera, Safari
                        xmlhttp=new XMLHttpRequest();
                    }
                    else {
                        // code for IE6, IE5
                        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    xmlhttp.onreadystatechange=function() {
                        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                            document.getElementById("comment").innerHTML=xmlhttp.responseText;
                        }
                    }
                    xmlhttp.open("GET","ListComment?q="+comment+"&r="+idtask+"&s="+username+"&pagenum="+pagenum,true);
                    xmlhttp.send();
                },500);
            }
            function suggestion(){
				
                var suggest = document.getElementById("as").value;
                var xmlhttp;
                document.getElementById("opsis").innerHTML="";
                if (suggest.length==0) { 
                    document.getElementById("opsis").innerHTML="";
                    return;
                }
                if (window.XMLHttpRequest) {
                    // code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }
                else {
                    // code for IE6, IE5
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange=function() {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                        document.getElementById("opsis").innerHTML=xmlhttp.responseText;
                    }
				  
                }
				
                xmlhttp.open("GET","Suggestion?suggest="+suggest,true);
                xmlhttp.send();
            }
        </script>
    </head>

          <% out.print("<body onload=\"makeTgl();makeThn();myFunction(document.addcomment.isicomment.value,'" + request.getParameter("q") + "','" + session.getAttribute("id").toString() + "','" + session.getAttribute("pagenum").toString() + "');\">" ); %>
          <div id="container">
            <div id="header">
                <div class=logo id="logo">
                    <a href="dashboard.jsp"><img src="images/logo.png" title="Home" alt="Home"/></a>
                </div>
                <div id="space">
                </div>
                <div class = "menu" id = "search">
                    <form name="search" method="get" action="SearchPage">
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
                <div class="menu" id="logout" action="Logout">
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

                            user = userdb.getUser(session.getAttribute("id").toString());
                            out.print(user.getAvatar());
                        %>
                        ">
                        <%
                            out.print(user.getUsername());
                        %>
                    </a>
                </div>
            </div>
            <div id="leftspace">

            </div>
            <div id="viewtask">
                <form name=edit>
                    <div class="form_field">
                        <div class="viewtask_label">
                            Task Name
                        </div>
                        <div class="viewtask_field">
                            <%
                            TugasDb dbtugas = new TugasDb();
                            Tugas tugas = new Tugas();
                            String idtugas = request.getParameter("q");
                            tugas = dbtugas.getTugasById(idtugas);
                            out.println(tugas.getNamatugas());
                            %>
                        </div>
                    </div>
                    <div class="form_attachment">
                        <%
                            List<Attachment> attachment = new ArrayList<Attachment>();
                            AttachmentDb dbattachment = new AttachmentDb();
                            attachment = dbattachment.getAttachment(tugas.getIdtugas());
                            for (int i = 0; i < attachment.size(); i++) {
                                String filename = attachment.get(i).getIsiattachment();
                                String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
                                if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("bmp")) {
                                    out.println("<img id=image src=\""+filename+"\" width=320 alt=\"\"/>");
                                }
                                else if (extension.equals("ogg") || extension.equals("webm") || extension.equals("3gp")) {
                                    out.println("<video id=video width=320 src=\""+filename+"\"  controls onError=\"this.style.display = 'none';\"></video>");
                                }
                            }
                        %>
                        </video>
                </div>
                
                <div class="form_field">
                    <div class="viewtask_label">
                        Attachment
                    </div>
                    <form name=viewtask_form method=post action=AddAttachment enctype="multipart/form-data">
                    <div class="viewtask_field">
                            <%
                                for (int i = 0; i < attachment.size(); i++) {
                                    out.println("<a id=link href=\""+attachment.get(i).getIsiattachment()+"\" target=\"_blank\">"+attachment.get(i).getIsiattachment()+"</a>");
                                }
                            %>
                        </div>
                    </form>
                </div>
                <div class="form_field">
                    <div class="viewtask_label">
                        Deadline
                    </div>
                    <div class="viewtask_field">
                        <div>
                            <p id="dlvalue">
                                <%
                                    out.println(tugas.getDeadline());
                                %>
                            </p>
                        </div>
                        <div id="dl">
                            <select name="tgl" id="tgl">
                            </select>
                            <select name="bln" id="bln">
                                <option value="1">January</option>
                                <option value="2">February</option>
                                <option value="3">March</option>
                                <option value="4">April</option>
                                <option value="5">May</option>
                                <option value="6">June</option>
                                <option value="7">July</option>
                                <option value="8">August</option>
                                <option value="9">September</option>
                                <option value="10">October</option>
                                <option value="11">November</option>
                                <option value="12">December</option>
                            </select>
                            <select name="thn" id="thn">
                            </select>
                        </div>
					</div>
					
                </div>
                <div class="form_field">
                    <div class="viewtask_label">
                        Assignee
                    </div>
                    <div class="viewtask_field">
                        <p id="asvalue">
                            <%
                            AssigneeDb dbassignee = new AssigneeDb();
                            List<String> assignees = dbassignee.getUsername(idtugas);
                            for (int i = 0; i < assignees.size(); i++) {
                                if (i < assignees.size() - 1)
                                    out.println("<a href=\"profilesearch.jsp?idsearch="+assignees.get(i)+"\">"+assignees.get(i)+"</a>\", ");
                                else
                                    out.println("<a href=\"profilesearch.jsp?idsearch="+assignees.get(i)+"\">"+assignees.get(i)+"</a>");
                            }
                            %>
                        </p>			
                    </div>
                </div>
                <div class="form_field">
                    <div class="viewtask_label">
                        Tag
                    </div>
                    <div class="viewtask_field">
                        <p id="tagvalue">
                        <%
                        TagDb dbtag = new TagDb();
                            List<String> tags = dbtag.getTagString(idtugas);
                            for (int j = 0; j < tags.size(); j++) {
                                if (j < (tags.size() - 1))
                                    out.println(tags.get(j)+", ");
                                else
                                    out.print(tags.get(j));
                            }
                        %>
                    </div>
                </div>
                <div class="form_field">
                    <div class="viewtask_label">
                        Status : 
                        <%
                        out.println(tugas.getStatus());
                        %>
                    </div>
                </div>
			
            </form> 
        </div>
        <div id="rightspace">
            <div>
                <div>
                    <fieldset id="fieldset">
                        <legend id="commentLabel">Comments</legend>
                        <div class="comment" id="comment">
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
        <div id="rightspace2">
            <form name="addcomment" method="post">
                <div>
                    <textarea id="commentField" name="isicomment" rows="3" cols="30" onfocus="this.value='';"></textarea>
                </div>
                <div>
                    <% out.print("<input type=button value=\"Submit Comment\" onClick=\"showComment(document.addcomment.isicomment.value,'" + request.getParameter("q") + "','" + session.getAttribute("id").toString() + "')\">"); %>
                </div>
            </form>
        </div>
    </div>
</body>
</html>