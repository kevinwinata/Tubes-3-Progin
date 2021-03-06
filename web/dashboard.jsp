<%-- 
    Document   : dashboard
    Created on : Apr 2, 2013, 2:05:42 PM
    Author     : Kevin Winata
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

        <title>Dashboard</title>
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <link href="styles/dashboard.css" rel="stylesheet" type="text/css" />

        <script>
            var kategori = 0;
            function ListKategori(username){
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
                        document.getElementById("listkategori").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","ListKategori",true);
                xmlhttp.send();
            }
        
            function HakPengguna(){
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
                        document.getElementById("pengguna").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","HakPengguna",true);
                xmlhttp.send();
            }
        
            function showTask(uidkategori){
                var xmlhttp;
                kategori = uidkategori;
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
                        document.getElementById("listtask").innerHTML=xmlhttp.responseText;
                        document.getElementById("listtaskdefault").style.display = 'none';
                        document.getElementById("listtask").style.display = 'block';
                        document.getElementById("tambahtaskblock").innerHTML="<div class=task_block id=tambah_task onclick=\"location.href='newtask.jsp'\"><p>Tambah Task...</p></div>";
                        document.getElementById("deletecategory").innerHTML="<div class=hapus_category_block onclick=\"location.href='DeleteKategori?q="+uidkategori+"'\"><div class=category_name>Hapus Kategori...</div></div>";
                        document.getElementById("deletecategory").style.display = 'block';
                    }
                }
                xmlhttp.open("GET","ListTask?q="+uidkategori,true);
                xmlhttp.send();
            }

            function showTaskDefault(){
                var xmlhttp;
                kategori = 0;
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
                        document.getElementById("listtaskdefault").innerHTML=xmlhttp.responseText;
                        document.getElementById("listtaskdefault").style.display = 'block';
                        document.getElementById("listtask").style.display = 'none';
                        document.getElementById("deletecategory").style.display = 'none';
                        document.getElementById("tambahtaskblock").innerHTML="";
                    }
                }
                xmlhttp.open("GET","ListTaskDefault?q=moonray",true);
                xmlhttp.send();
            }
            
            function ChangeStatus(idtugas){
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
			
                    }
                }
                xmlhttp.open("GET","ChangeStatus?q="+idtugas,true);
                xmlhttp.send();
            }
            
            function update() {
                setInterval(function() {
                if (kategori == 0) {
                    showTaskDefault();
                }
                else {
                    showTask(kategori);
                }
                },500);
            }
            
        </script>

    </head>
    <body onLoad="ListKategori();HakPengguna();update()">
    <div id="container">
        <div id="header">
            <div class=logo id="logo">
                <a href="dashboard.jsp"><img src="images/logo.png" title="Home" alt="Home"/></a>
            </div>
            <div id="space">
            </div>
            <div class = "menu" id = "search">
                <form name="search" method="get" action="SearchPage">
                    Search for: <input type="text" onkeyup= "searchWords();" name="find"/> in 
                    <Select NAME="field" onchange= "searchWords();">
                        <Option VALUE="semua">Semua</option>
                        <Option VALUE="username">Username</option>
                        <Option VALUE="namakategori">Judul Kategori</option>
                        <Option VALUE="tasktag">Task atau Tag</option>
                    </Select>
                    <input type="hidden" name="searching" value="yes" />
                    <button type="submit" id="searchbutton" onclick= "searchWords();"></button>
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
    </div>

    <div id="category">
        <div id="category_head">
            Kategori
        </div>
        <div class="category_block" onclick="showTaskDefault()">
            <div class="category_name">
                <a href="dashboard.php">...</a>
            </div>
        </div>
        <div id="listkategori" name="listkategori"></div>

        <div class="category_block" id="tambah_kategori" onclick="location.href='#category_form'">
            <div class="category_pic">
                <img src="images/tambah.png" alt=""/>
            </div>
            <div class="category_name">
                Tambah kategori...
            </div>
        </div>
    </div>
    <div id="task">
        <div id="task_header">
            Tasks
        </div>

        <div id="tambahtaskblock">
        </div>
        <div id = "listtask">
        </div>
        <div id = "listtaskdefault">
        </div>
        <div id = "deletecategory">
        </div>
    </div>
</div>

<!--Popup bikin kategori baru -->
<a href="#x" class="overlay" id="category_form"></a>
<div class="popup">
    <form name="addcategory" method="post" action="AddKategori">
        <div class="form_baris">

            <div class="form_kiri">
                Nama Kategori: 
            </div>
            <div class="form_kanan">
                <input name="categoryname" type="text" size="35" maxlength="25" class="inputtext">
            </div>
        </div>
        <div id="fs">
            <fieldset>
                <legend>Pengguna yang Bisa Mengubah</legend>
                <div id="pengguna" name="pengguna"></div>
            </fieldset>
        </div>
        <div class="form_baris">
            <input type="submit" name="submit" value="Buat Kategori" id="button_buat_kategori"/>
        </div>
        <a class="close" href="#close"></a>
    </form>
</div>
</body>
</html>

