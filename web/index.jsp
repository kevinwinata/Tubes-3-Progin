<%-- 
    Document   : indexnew
    Created on : Apr 4, 2013, 10:20:27 AM
    Author     : Mario
--%>

<html>
    <head>
        <title>Organize Homepage</title>
        <link href="styles/home.css" rel="stylesheet" type="text/css" />

        <script>
            function isUsername(uname){
                var xmlhttp;
                if (uname.length==0) { 
                    document.getElementById("v_login").innerHTML="";
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
                        document.getElementById("v_login").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","ListUsername?q="+uname+"&t=login",true);
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

            function enableRegister(){
                if(checkUserName(document.register_form.username.value) &&
                    checkPass(document.register_form.password.value, document.register_form.username.value, document.register_form.email.value) &&
                    checkCPass(document.register_form.confirm_password.value, document.register_form.password.value) &&
                    validateFullName(document.register_form.fullname.value) &&
                    checkEmail(document.register_form.email.value) &&
                    validateAvatar(document.register_form.avatar.value))
                {
                    document.register_form.submit.disabled =false;
                }
                else
                {
                    document.register_form.submit.disabled =true;
                }
            }

            function checkEmail(email){	
                var pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
                if(pattern.test(email)){
                    var xmlhttp;
                    if (email.length==0) { 
                        document.getElementById("v_email").innerHTML="";
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
                            document.getElementById("v_email").innerHTML=xmlhttp.responseText;
                        }
                    }
                    xmlhttp.open("GET","ListEmail?q="+email,true);
                    xmlhttp.send();

                    return true;
                } else {   
                    document.getElementById('v_email').innerHTML='<font color="red">Salah</font>';
                    return false;
                }
            }			

            function checkUserName(uname){	
                var pattern=/^([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+/;
                if(pattern.test(uname)){         
                    var xmlhttp;
                    if (uname.length==0) { 
                        document.getElementById("v_uname").innerHTML="";
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
                            document.getElementById("v_uname").innerHTML=xmlhttp.responseText;
                        }
                    }
                    xmlhttp.open("GET","ListUsername?q="+uname+"&t=reg",true);
                    xmlhttp.send();
                    return true;
                }else{   
                    document.getElementById('v_uname').innerHTML='<font color="red">Salah</font>';
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

            function checkSubmit(e) {
                if (e && e.keyCode == 13) {
                    validateLogin();
                }
            }
        </script>
    </head>

    <body onLoad="makeTgl();makeThn();">
        <div id="container">
            <div id="header">
                <div class=logo id="logo">
                    <a href="index.php"><img src="images/logo.png" title="Home" alt="Home"/></a>
                </div>
                <form name="login" method="post" action='Login'>
                    <div class="login_form">
                        <input type="submit" value="Login">
                    </div>
                    <div class="login_form">
                        Password: <input type="password" name="password">
                    </div>
                    <div class="login_form">
                        Username: <input type="text" name="username" onKeyUp="isUsername(document.login.username.value)">
                        <div id="v_login">
                            <%
                            String success = (String) request.getAttribute("q");

                            if ((success != null) && (success.equals("0"))) {
                                out.print("<font color='red'>Login gagal</font>");
                            }
                            %>
                        </div>
                    </div>
                </form>
            </div>

            <div id="left_tab">
                <img src="images/registerglass.png">
            </div>

            <div id="register_tab">
                <form name="register_form" method="post" action="Register" enctype="multipart/form-data">
                    <div id="formulir">
                        <div class="form_field">
                            <div class="field_kiri">
                                Username: 
                            </div>
                            <div class="field_kanan">
                                <input name="username" type="text"  maxlength="256" onKeyUp="enableRegister()">
                            </div>
                            <div id="v_uname">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="field_kiri">
                                Password: 
                            </div>
                            <div class="field_kanan">
                                <input name="password" type="password"  maxlength="24" onKeyUp="enableRegister()">
                            </div>
                            <div id="v_pass">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="field_kiri">
                                Confirm Password: 
                            </div>
                            <div class="field_kanan">
                                <input name="confirm_password" type="password"  maxlength="24" onKeyUp="enableRegister()">
                            </div>
                            <div id="v_cpass">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="field_kiri">
                                Nama lengkap: 
                            </div>
                            <div class="field_kanan">
                                <input type="text" name="fullname" maxlength="256" onKeyUp="enableRegister()">
                            </div>
                            <div id="v_nama">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="field_kiri">
                                Tanggal lahir: 
                            </div>
                            <div class="field_kanan">
                                <select name="tanggal" id="tgl">
                                    <option>--Tanggal--</option>
                                </select>
                                <select name="bulan">
                                    <option>--Bulan--</option>
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
                                    <option>--Tahun--</option>
                                </select>
                            </div>
                            <div id="v_lahir">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="field_kiri">
                                Email: 
                            </div>
                            <div class="field_kanan">
                                <input type="text" name="email" maxlength="256" onKeyUp="enableRegister()">
                            </div>
                            <div id="v_email">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="field_kiri">
                                Avatar: 
                            </div>
                            <div class="field_kanan">
                                <input type="file" name="avatar" onChange="enableRegister()">
                            </div>
                            <div id="v_avatar">
                            </div>
                        </div>
                        <div class="form_field">
                            <div class="field_kiri">
                                <input type="submit" name="submit" value="Register" disabled = true>
                            </div>
                            <div class="field_kanan">

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

