<%-- 
    Document   : search
    Created on : Apr 4, 2013, 8:21:12 PM
    Author     : kevin
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dboperation.TagDb"%>
<%@page import="model.Tag"%>
<%@page import="dboperation.TugasDb"%>
<%@page import="model.Tugas"%>
<%@page import="dboperation.UserDb"%>
<%@page import="model.User"%>
<%@page import="java.io.*"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <link href="styles/search.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="search.js"></script>
        <script>
            function searchWord1(pagenum){
                var find = document.search.find.value;
                var searching = document.search.searching.value;
                var field = document.search.field.value;
                var xmlhttp;
                document.getElementById("task1").innerHTML="";
                if (find.length==0) { 
                    document.getElementById("task1").innerHTML="";
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
                        document.getElementById("task1").innerHTML=xmlhttp.responseText;
                    }
				  
                }

                xmlhttp.open("GET","Search?find="+find+"&field="+field+"&pagenum="+pagenum,true);;
                xmlhttp.send();

            }
            function searchWord2(pagenum){
                var find = document.search.find.value;
                var searching = document.search.searching.value;
                var field = document.search.field.value;
                var xmlhttp;
                document.getElementById("task2").innerHTML="";
                if (find.length==0) { 
                    document.getElementById("task2").innerHTML="";
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
                        document.getElementById("task2").innerHTML=xmlhttp.responseText;
                    }
				  
                }
				
                xmlhttp.open("GET","Search2?find="+find+"&field="+field+"&pagenum="+pagenum,true);
                xmlhttp.send();
            }
            function searchWord3(pagenum){
                var find = document.search.find.value;
                var searching = document.search.searching.value;
                var field = document.search.field.value;
                var xmlhttp;
                document.getElementById("task3").innerHTML="";
                if (find.length==0) { 
                    document.getElementById("task3").innerHTML="";
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
                        document.getElementById("task3").innerHTML=xmlhttp.responseText;
                    }
				  
                }
				
                xmlhttp.open("GET","Search3?find="+find+"&field="+field+"&pagenum="+pagenum,true);
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
        
            function searchWords(){
                searchWord1(0);
                searchWord2(0);
                searchWord3(0);
            }
            function showK1(){
                document.getElementById('k1').style.borderColor = '#836fff';
                document.getElementById('k2').style.borderColor = '#d9d9d9';
                document.getElementById('k3').style.borderColor = '#d9d9d9';
                document.getElementById('k11').style.display = 'block';
                document.getElementById('k12').style.display = 'block';
                document.getElementById('k13').style.display = 'block';
                document.getElementById('k14').style.display = 'block';
                document.getElementById('k21').style.display = 'none';
                document.getElementById('k22').style.display = 'none';
                document.getElementById('k31').style.display = 'none';
            }
            function showK2(){
                document.getElementById('k1').style.borderColor = '#d9d9d9';
                document.getElementById('k2').style.borderColor = '#836fff';
                document.getElementById('k3').style.borderColor = '#d9d9d9';
                document.getElementById('k11').style.display = 'none';
                document.getElementById('k12').style.display = 'none';
                document.getElementById('k13').style.display = 'none';
                document.getElementById('k14').style.display = 'none';
                document.getElementById('k21').style.display = 'block';
                document.getElementById('k22').style.display = 'block';
                document.getElementById('k31').style.display = 'none';
            }
            function showK3(){
                document.getElementById('k1').style.borderColor = '#d9d9d9';
                document.getElementById('k2').style.borderColor = '#d9d9d9';
                document.getElementById('k3').style.borderColor = '#836fff';
                document.getElementById('k11').style.display = 'none';
                document.getElementById('k12').style.display = 'none';
                document.getElementById('k13').style.display = 'none';
                document.getElementById('k14').style.display = 'none';
                document.getElementById('k21').style.display = 'none';
                document.getElementById('k22').style.display = 'none';
                document.getElementById('k31').style.display = 'block';
            }
        </script>
    </head>

    <body onLoad = "searchWords();">

        <div id="container">
            <div id="header">
                <div class=logo id="logo">
                    <a href="dashboard.jsp"><img src="images/logo.png" title="Home" alt="Home"/></a>
                </div>
                <div id="space">
                </div>
                <div class = "menu" id = "search">
                    <form name="search" method="get" action="SearchPage">
                        Search for: <input type="text" onkeyup= "searchWords();" name="find" value="<%
                            String find = request.getParameter("find");
                            if (find != null) {
                                out.println(find);
                            } else {
                                out.println("");
                            }
                                           %>"> in 
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
                <div class="menu" id="logout">
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

            <div id="category">
                <div id="category_head">
                    Hasil Search
                </div>
            </div>
            <div id="task1">

            </div>
            <div id="task2">

            </div>
            <div id="task3">

            </div>
        </div>
    </body>
</html>
