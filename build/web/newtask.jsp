<%-- 
    Document   : newtask
    Created on : Apr 11, 2013, 7:59:05 PM
    Author     : Kevin
--%>

<%@page import="model.User"%>
<%@page import="dboperation.UserDb"%>
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
        <title>Insert New Task</title>
        <link href="styles/newtask.css" rel="stylesheet" type="text/css" />
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <script>
            var fullPath = "empty";

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

            function checkTaskName(taskName){
                var pattern = /[^a-zA-Z0-9 ]/
                var l = taskName.length;
                if(!pattern.test(taskName)){         
                    document.getElementById('v_tname').innerHTML='<img src="images/Check.png" title="Benar"/img>';
                    return true;
                }else{   
                    document.getElementById('v_tname').innerHTML='<img src="images/Cross.png" title="Nama task tidak boleh menggunakan karakter khusus"/img>';
                    return false;
                }
            }

            function checkAssignee(assignee){
                var pattern = /[a-zA-Z]/
                if(pattern.test(assignee)){         
                    document.getElementById('v_assignee').innerHTML='<img src="images/Check.png" title="Benar"/img>'; 
                    return true;
                }else{   
                    document.getElementById('v_assignee').innerHTML='<img src="images/Cross.png" title="Nama harus tersusun dari minimal 2 kata"/img>';
                    return false;
                }
            }

            function checkAttachment(attachment) {
                attachment = attachment.substring(attachment.lastIndexOf('.')+1);
                if (attachment) {
                    var startIndex = (attachment.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
                    var filename = attachment.substring(startIndex);
                    if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0)
                        filename = filename.substring(1);
                    if(filename == "docx"|| filename == "jpeg" || filename == "bmp" || filename == "jpg" || filename == "pdf" || filename == "doc" 
                        || filename == "docx" || filename == "xls" || filename == "xlsx" || filename == "ppt" || filename == "pptx" || filename == "mp4"
                        || filename == "ogg"|| filename == "webm"|| filename == "3gp"){         
                        document.getElementById('v_attachment').innerHTML='<img src="images/Check.png" title="Benar"/img>'; 
                        return true;
                    }else{   
                        document.getElementById('v_attachment').innerHTML='<img src="images/Cross.png" title="Ekstensi file tidak valid"/img>';
                        return false;
                    }
                }
            }
			
            function suggestion(){
				
                var suggest = document.getElementById("assignee").value;
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

    <body onload="makeTgl();makeThn();">
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
            <div id="newtask">
                <form name=newtask_form method=post action="AddTask" enctype="multipart/form-data">
                            <div id="newtask_space">
                    </div>
                    <div id="formulir">
                        <div class="form_field">
                            <div class="newtask_label">
                                Nama Tugas
                            </div>
                            <div class="newtask_field">
                                <input name="taskname" type="text" size="35" maxlength="25" onkeyup="checkTaskName(document.newtask_form.taskname.value)" class="inputtext">
                            </div>
                            <div class="newtask_warning" id="v_tname">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="newtask_label">
                                Attachment
                            </div>
                            <div class="newtask_field">
                                <input type="file" name="attachment" id="attachment" onchange="checkAttachment(document.newtask_form.attachment.value)">
                            </div>
                            <div class="newtask_warning" id="v_attachment">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="newtask_label">
                                Deadline
                            </div>
                            <div class="newtask_field">
                                <select name="tanggal" id="tgl">
                                </select>
                                <select name="bulan">
                                    <option value="January">January</option>
                                    <option value="February">February</option>
                                    <option value="March">March</option>
                                    <option value="April">April</option>
                                    <option value="May">May</option>
                                    <option value="June">June</option>
                                    <option value="July">July</option>
                                    <option value="August">August</option>
                                    <option value="September">September</option>
                                    <option value="October">October</option>
                                    <option value="November">November</option>
                                    <option value="December">December</option>
                                </select>
                                <select name="tahun" id="thn">
                                </select>
                            </div>
                            <div class="newtask_warning" id="v_deadline">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="newtask_label">
                                Assignee
                            </div>
                            <div class="newtask_field">
                                <input id="assignee" type="text" name="assignee" tabindex="4" size="35" maxlength="256" list="user" onkeyup="checkAssignee(document.newtask_form.assignee.value);suggestion()">
                                <label id="opsis"></label>
                            </div>
                            <div class="newtask_warning" id="v_assignee">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="newtask_label">
                                Tag
                            </div>
                            <div class="newtask_field">
                                <input type="text" name="tag" size="35" maxlength="256"  class="inputtext">
                            </div>
                            <div class="newtask_warning" id="v_tag">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="newtask_label">
                            </div>
                            <div class="newtask_field">
                                <input type="submit" value="Add Task" id="addtask_button">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="rightspace">

            </div>
        </div>
    </body>
</html>