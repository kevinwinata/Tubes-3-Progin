package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class indexnew_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Organize Homepage</title>\n");
      out.write("        <link href=\"styles/home.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            function isUsername(uname){\n");
      out.write("                var xmlhttp;\n");
      out.write("                if (uname.length==0) { \n");
      out.write("                    document.getElementById(\"v_login\").innerHTML=\"\";\n");
      out.write("                    return;\n");
      out.write("                }\n");
      out.write("                if (window.XMLHttpRequest) {\n");
      out.write("                    // code for IE7+, Firefox, Chrome, Opera, Safari\n");
      out.write("                    xmlhttp=new XMLHttpRequest();\n");
      out.write("                }\n");
      out.write("                else {\n");
      out.write("                    // code for IE6, IE5\n");
      out.write("                    xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("                }\n");
      out.write("                xmlhttp.onreadystatechange=function() {\n");
      out.write("                    if (xmlhttp.readyState==4 && xmlhttp.status==200) {\n");
      out.write("                        document.getElementById(\"v_login\").innerHTML=xmlhttp.responseText;\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                xmlhttp.open(\"GET\",\"listusername.php?q=\"+uname+\"&t=login\",true);\n");
      out.write("                xmlhttp.send();\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function makeTgl(){\n");
      out.write("                for(var i=1; i<=31; i++){\n");
      out.write("                    var isi=document.createTextNode(i);\n");
      out.write("                    var opsi = document.createElement(\"option\");\n");
      out.write("                    opsi.setAttribute(\"value\",i);\n");
      out.write("                    opsi.appendChild(isi);\n");
      out.write("                    document.getElementById(\"tgl\").appendChild(opsi);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function makeThn(){\n");
      out.write("                for(var i=1955; i<=2013; i++){\n");
      out.write("                    var isi=document.createTextNode(i);\n");
      out.write("                    var opsi = document.createElement(\"option\");\n");
      out.write("                    opsi.setAttribute(\"value\",i);\n");
      out.write("                    opsi.appendChild(isi);\n");
      out.write("                    document.getElementById(\"thn\").appendChild(opsi);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function enableRegister(){\n");
      out.write("                if(checkUserName(document.register_form.username.value) &&\n");
      out.write("                    checkPass(document.register_form.password.value, document.register_form.username.value, document.register_form.email.value) &&\n");
      out.write("                    checkCPass(document.register_form.confirm_password.value, document.register_form.password.value) &&\n");
      out.write("                    validateFullName(document.register_form.nama_lengkap.value) &&\n");
      out.write("                    checkEmail(document.register_form.email.value) &&\n");
      out.write("                    validateAvatar(document.register_form.avatar.value))\n");
      out.write("                {\n");
      out.write("                    document.register_form.submit.disabled =false;\n");
      out.write("                }\n");
      out.write("                else\n");
      out.write("                {\n");
      out.write("                    document.register_form.submit.disabled =true;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function checkEmail(email){\t\n");
      out.write("                var pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+/;\n");
      out.write("                if(pattern.test(email)){\n");
      out.write("                    var xmlhttp;\n");
      out.write("                    if (email.length==0) { \n");
      out.write("                        document.getElementById(\"v_email\").innerHTML=\"\";\n");
      out.write("                        return;\n");
      out.write("                    }\n");
      out.write("                    if (window.XMLHttpRequest) {\n");
      out.write("                        // code for IE7+, Firefox, Chrome, Opera, Safari\n");
      out.write("                        xmlhttp=new XMLHttpRequest();\n");
      out.write("                    }\n");
      out.write("                    else {\n");
      out.write("                        // code for IE6, IE5\n");
      out.write("                        xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("                    }\n");
      out.write("                    xmlhttp.onreadystatechange=function() {\n");
      out.write("                        if (xmlhttp.readyState==4 && xmlhttp.status==200) {\n");
      out.write("                            document.getElementById(\"v_email\").innerHTML=xmlhttp.responseText;\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                    xmlhttp.open(\"GET\",\"listemail.php?q=\"+email,true);\n");
      out.write("                    xmlhttp.send();\n");
      out.write("\n");
      out.write("                    return true;\n");
      out.write("                } else {   \n");
      out.write("                    document.getElementById('v_email').innerHTML='<font color=\"red\">Salah</font>';\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("            }\t\t\t\n");
      out.write("\n");
      out.write("            function checkUserName(uname){\t\n");
      out.write("                var pattern=/^([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+/;\n");
      out.write("                if(pattern.test(uname)){         \n");
      out.write("                    var xmlhttp;\n");
      out.write("                    if (uname.length==0) { \n");
      out.write("                        document.getElementById(\"v_uname\").innerHTML=\"\";\n");
      out.write("                        return;\n");
      out.write("                    }\n");
      out.write("                    if (window.XMLHttpRequest) {\n");
      out.write("                        // code for IE7+, Firefox, Chrome, Opera, Safari\n");
      out.write("                        xmlhttp=new XMLHttpRequest();\n");
      out.write("                    }\n");
      out.write("                    else {\n");
      out.write("                        // code for IE6, IE5\n");
      out.write("                        xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("                    }\n");
      out.write("                    xmlhttp.onreadystatechange=function() {\n");
      out.write("                        if (xmlhttp.readyState==4 && xmlhttp.status==200) {\n");
      out.write("                            document.getElementById(\"v_uname\").innerHTML=xmlhttp.responseText;\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                    xmlhttp.open(\"GET\",\"listusername.php?q=\"+uname+\"&t=reg\",true);\n");
      out.write("                    xmlhttp.send();\n");
      out.write("                    return true;\n");
      out.write("                }else{   \n");
      out.write("                    document.getElementById('v_uname').innerHTML='<font color=\"red\">Salah</font>';\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("            }\t\t\t\n");
      out.write("\n");
      out.write("            function checkCPass(cpass, pass){\n");
      out.write("                if(cpass == pass){\n");
      out.write("                    document.getElementById('v_cpass').innerHTML='<font color=\"green\">Benar</font>';\n");
      out.write("                    return true;\t\t\t\t\t\n");
      out.write("                }else{\n");
      out.write("                    document.getElementById('v_cpass').innerHTML='<font color=\"red\">Salah</font>'; \n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function checkPass(pass, uname, email){\n");
      out.write("                if((pass != uname) && (pass != email)){\n");
      out.write("                    document.getElementById('v_pass').innerHTML='<font color=\"green\">Benar</font>';\n");
      out.write("                    return true;\t\t\t\t\t\n");
      out.write("                }else if(pass == uname){\n");
      out.write("                    document.getElementById('v_pass').innerHTML='<font color=\"red\">Password tidak boleh sama dengan username</font>'; \n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("                else if(pass == email){\n");
      out.write("                    document.getElementById('v_pass').innerHTML='<font color=\"red\">Password tidak boleh sama dengan email</font>'; \n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("                else{\n");
      out.write("                    document.getElementById('v_pass').innerHTML='<font color=\"red\">Password tidak boleh sama dengan username/email</font>'; \n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function validateFullName(nama) {\n");
      out.write("                var str = nama;\n");
      out.write("                var idx = str.indexOf(' ');\n");
      out.write("                if (idx > 0 && idx < str.length - 1) {\n");
      out.write("                    var chr1 = str.charAt(idx - 1);\n");
      out.write("                    var chr2 = str.charAt(idx + 1);\n");
      out.write("                    if (chr1 != ' ' && chr2 != ' ') {\n");
      out.write("                        document.getElementById('v_nama').innerHTML='<font color=\"green\">Benar</font>';\n");
      out.write("                        return true;\t\n");
      out.write("                    } else {\n");
      out.write("                        document.getElementById('v_nama').innerHTML='<font color=\"red\">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; \n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                } else {\n");
      out.write("                    document.getElementById('v_nama').innerHTML='<font color=\"red\">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; \n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function validateAvatar(avatar) {\n");
      out.write("                var str = avatar;\n");
      out.write("                var ext = str.substring(str.lastIndexOf('.') + 1, str.length);\n");
      out.write("                if (ext.toLowerCase() == \"jpeg\" || ext.toLowerCase() == \"jpg\") {\n");
      out.write("                    document.getElementById('v_avatar').innerHTML='<font color=\"green\">Benar</font>';\n");
      out.write("                    return true;\t\n");
      out.write("                } else {\n");
      out.write("                    document.getElementById('v_avatar').innerHTML='<font color=\"red\">Avatar harus ekstensi .jpg atau .jpeg</font>'; \n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function checkSubmit(e) {\n");
      out.write("                if (e && e.keyCode == 13) {\n");
      out.write("                    validateLogin();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body onLoad=\"makeTgl();makeThn();\">\n");
      out.write("        <div id=\"container\">\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <div class=logo id=\"logo\">\n");
      out.write("                    <a href=\"index.php\"><img src=\"images/logo.png\" title=\"Home\" alt=\"Home\"/></a>\n");
      out.write("                </div>\n");
      out.write("                <form name=\"login\" method=\"post\" action='Login'>\n");
      out.write("                    <div class=\"login_form\">\n");
      out.write("                        <input type=\"submit\" name=\"submit\" value=\"Login\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"login_form\">\n");
      out.write("                        Password: <input type=\"password\" name=\"mypassword\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"login_form\">\n");
      out.write("                        Username: <input type=\"text\" name=\"myusername\" onKeyUp=\"isUsername(document.login.myusername.value)\">\n");
      out.write("                        <div id=\"v_login\">\n");
      out.write("                            ");

                            String success = request.getParameter("q");
                            if (success == "0") {
                                out.print("Login gagal");
                            }
                            
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"left_tab\">\n");
      out.write("                <img src=\"images/registerglass.png\" alt=\"Register dong gan\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"register_tab\">\n");
      out.write("                <form name=\"register_form\" method=\"post\" action=\"register.php\" enctype=\"multipart/form-data\">\n");
      out.write("                    <div id=\"formulir\">\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                Username: \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("                                <input name=\"username\" type=\"text\"  maxlength=\"256\" onKeyUp=\"enableRegister()\">\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"v_uname\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                Password: \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("                                <input name=\"password\" type=\"password\"  maxlength=\"24\" onKeyUp=\"enableRegister()\">\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"v_pass\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                Confirm Password: \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("                                <input name=\"confirm_password\" type=\"password\"  maxlength=\"24\" onKeyUp=\"enableRegister()\">\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"v_cpass\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                Nama lengkap: \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("                                <input type=\"text\" name=\"nama_lengkap\" maxlength=\"256\" onKeyUp=\"enableRegister()\">\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"v_nama\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                Tanggal lahir: \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("                                <select name=\"tanggal\" id=\"tgl\">\n");
      out.write("                                    <option>--Tanggal--</option>\n");
      out.write("                                </select>\n");
      out.write("                                <select name=\"bulan\">\n");
      out.write("                                    <option>--Bulan--</option>\n");
      out.write("                                    <option value=\"January\">January</option>\n");
      out.write("                                    <option value=\"February\">February</option>\n");
      out.write("                                    <option value=\"March\">March</option>\n");
      out.write("                                    <option value=\"April\">April</option>\n");
      out.write("                                    <option value=\"May\">May</option>\n");
      out.write("                                    <option value=\"June\">June</option>\n");
      out.write("                                    <option value=\"July\">July</option>\n");
      out.write("                                    <option value=\"August\">August</option>\n");
      out.write("                                    <option value=\"September\">September</option>\n");
      out.write("                                    <option value=\"October\">October</option>\n");
      out.write("                                    <option value=\"November\">November</option>\n");
      out.write("                                    <option value=\"December\">December</option>\n");
      out.write("                                </select>\n");
      out.write("                                <select name=\"tahun\" id=\"thn\">\n");
      out.write("                                    <option>--Tahun--</option>\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"v_lahir\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                Email: \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("                                <input type=\"text\" name=\"email\" maxlength=\"256\" onKeyUp=\"enableRegister()\">\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"v_email\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                Avatar: \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("                                <input type=\"file\" name=\"avatar\" onChange=\"enableRegister()\">\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"v_avatar\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form_field\">\n");
      out.write("                            <div class=\"field_kiri\">\n");
      out.write("                                <input type=\"submit\" name=\"submit\" value=\"Register\" disabled = true>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"field_kanan\">\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}