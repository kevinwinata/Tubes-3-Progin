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

        function validateFullName(nama) {
            var str = nama;
            var idx = str.indexOf(' ');
            if (idx > 0 && idx < str.length - 1) {
                var chr1 = str.charAt(idx - 1);
                var chr2 = str.charAt(idx + 1);
                if (chr1 != ' ' && chr2 != ' ') {
                    document.getElementById('v_nama').innerHTML='<font color="green">Benar</font>';
                    return true;	
                } else {
                    document.getElementById('v_nama').innerHTML='<font color="red">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; 
                    return false;
                }
            } else {
                document.getElementById('v_nama').innerHTML='<font color="red">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; 
                return false;
            }
        }

        function checkPass(pass, uname, email){
            var a = pass;
            if((pass != uname) && (pass != email) && (a.length > 7)){
                document.getElementById('v_pass').innerHTML='<font color="green">Benar</font>';
                return true;					
            }else if(pass == uname){
                document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username</font>'; 
                return false;
            }
            else if(pass == email){
                document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan email</font>'; 
                return false;
            }
            else{
                document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username/email dan minimal 8 karakter</font>'; 
                return false;
            }
        }

        function checkCPass(cpass, pass){
            if(cpass == pass){
                document.getElementById('v_cpass').innerHTML='<font color="green">Benar</font>';
                return true;					
            }else{
                document.getElementById('v_cpass').innerHTML='<font color="red">Salah</font>'; 
                return false;
            }
        }

        function validateAvatar(avatar) {
            var str = avatar;
            var ext = str.substring(str.lastIndexOf('.') + 1, str.length);
            if (ext.toLowerCase() == "jpeg" || ext.toLowerCase() == "jpg") {
                document.getElementById('v_avatar').innerHTML='<font color="green">Benar</font>';
                return true;	
            } else {
                document.getElementById('v_avatar').innerHTML='<font color="red">Avatar harus ekstensi .jpg atau .jpeg</font>'; 
                return false;
            }
        }

        function check(fullname, password, cpassword, tanggal, bulan, tahun, avatar) {
            if ((fullname.length==0) &&
                (password.length==0) && 
                (cpassword.length==0) &&
                (tanggal == "1") && (bulan == "1") && (tahun == "1955") &&
                (avatar.length == 0)
            ) {
                    alert("Tidak ada atribut profile yang diubah!");
                    return false;
                }
            if (password == cpassword) {
                return true;
            } else {
                return false;
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
                    <a href="#editprofile_form"><button type="button" id="editbutton"></button></a>
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

            <!--Popup edit profile -->
            <a href="#x" class="overlay" id="editprofile_form"></a>
            <div class="popup">
                <form name="editprofile" method="post" onSubmit="return check(
                document.editprofile.fullname.value,
                document.editprofile.password.value,
                document.editprofile.confirm_password.value,
                document.editprofile.tanggal.value,
                document.editprofile.bulan.value,
                document.editprofile.tahun.value,
                document.editprofile.avatar.value)" action="EditProfile" enctype="multipart/form-data">
                    Fullname : <input name="fullname" type="text" maxlength="256" onKeyUp="validateFullName(document.editprofile.fullname.value)"><br>
                    <div id="v_nama">
                    </div>
                    Password : <input name="password" type="password" maxlength="24" onKeyUp="checkPass(document.editprofile.password.value,<% out.print("'" + user.getUsername() + "','" + user.getEmail() + "'"); %>)"> <br>
                    <div id="v_pass">
                    </div>
                    Confirm Password : <input name="confirm_password" type="password" maxlength="24" onKeyUp="checkCPass(document.editprofile.confirm_password.value, document.editprofile.password.value)"><br>
                    <div id="v_cpass">
                    </div>
                    Birthdate : 
                    <select name="tanggal" id="tgl">

                    </select>
                    <select name="bulan">
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
                    <select name="tahun" id="thn">

                    </select>
                    <br>
                    Avatar : <input type="file" name="avatar"><br>
                    <div id="v_avatar">
                    </div>
                    <input type="submit" name="submit" value="Edit">
                    <a class="close" href="#"></a>
                </form>
            </div>
    </body>
</html>