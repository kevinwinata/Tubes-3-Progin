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
    <head>
        <title>View Task</title>
        <link href="styles/viewtask.css" rel="stylesheet" type="text/css" />
        <link href="styles/header.css" rel="stylesheet" type="text/css" />

        <script>
            var attach = "videos/attachmentsample.ogg";
            var tanggal = 1;
            var bulan = "Januari";
            var tahun = 2013;
			
			function addTag(uidtugas) {
                var xmlhttp;
                var tag = document.getElementById("tagtext").value;
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
                        document.getElementById("tagvalue").innerHTML = xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","AddTag?q="+uidtugas+"&p="+tag,true);
                xmlhttp.send();
            }
			
			
			function hapusTag(uidtugas) {
                var xmlhttp;
                var tag = document.getElementById("fieldhapustag").value;
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
                        document.getElementById("tagvalue").innerHTML = xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","DeleteTag?q="+uidtugas+"&p="+tag,true);
                xmlhttp.send();
            }
			
            function editDeadline(uidtugas){
                var xmlhttp;
                var tgl=document.getElementById("tgl");
                var bln=document.getElementById("bln");
                var thn=document.getElementById("thn");
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
                        document.getElementById("dlvalue").innerHTML = tgl.options[tgl.selectedIndex].text+"-"+bln.options[bln.selectedIndex].text+"-"+thn.options[thn.selectedIndex].text;
                    }
                }
                xmlhttp.open("GET","EditDeadline?q="+uidtugas+"&tgl="+tgl.options[tgl.selectedIndex].text+"&bln="+bln.options[bln.selectedIndex].text+"&thn="+thn.options[thn.selectedIndex].text,true);
                xmlhttp.send();
            }
			
            function addAssignee(uidtugas,username) {
                var xmlhttp;
                var assignee = document.getElementById("as").value;
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
                        document.getElementById("asvalue").innerHTML = xmlhttp.responseText;
                        document.getElementById("daftarassignee").innerHTML = "<option value=shit>Shit</option>";
                    }
                }
                xmlhttp.open("GET","AddAssignee?q="+uidtugas+"&p="+assignee,true);
                xmlhttp.send();
            }
			
            function hapusAssignee(uidtugas) {
                var xmlhttp;
                var selection = document.getElementById("fieldhapus");
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
                        document.getElementById("asvalue").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","DeleteAssignee?q="+uidtugas+"&p="+selection.options[selection.selectedIndex].text,true);
                xmlhttp.send();
            }
			
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
                xmlhttp.open("GET","listcomment.php?q="+comment+"&r="+idtask+"&s="+username,true);
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
                    xmlhttp.open("GET","listcomment.php?q="+comment+"&r="+idtask+"&s="+username+"&pagenum="+pagenum,true);
                    xmlhttp.send();
                },500);
            }
            function suggestion(){
				
                var suggest = document.getElementById("as").value;
                var xmlhttp;
                document.getElementById("opsi").innerHTML="";
                if (suggest.length==0) { 
                    document.getElementById("opsi").innerHTML="";
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
                        document.getElementById("opsi").innerHTML=xmlhttp.responseText;
                    }
				  
                }
				
                xmlhttp.open("GET","functionsuggest.php?suggest="+suggest,true);
                xmlhttp.send();
            }
        </script>
    </head>

          <body onload="makeTgl();makeThn();">
          <div id="container">
            <div id="header">
                <div class=logo id="logo">
                    <a href="dashboard.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
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
                    <a href="index.php">Logout</a>
                </div>
                <div class="menu" id="home">
                    <a href="dashboard.php">Home</a>
                </div>
                <div class="menu" id="profile">
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
					<div class="viewtask_edit">
							<input type=button value="Edit" id="dlbutton" onClick="showHide('dl');showHide('dlvalue');showHide('dlbutton');showHide('savebutton')">
							<%
								out.println("<input type=button value=\"Save\" id=\"savebutton\" onClick=\"editDeadline('"+idtugas+"');showHide('dl');showHide('dlvalue');showHide('dlbutton');showHide('savebutton');getDateValue();setDateValue()\">");
							%>
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
                    <div class="viewtask_edit">
                        <input type=text name=as id="as" type="text" tabindex="4" list="user" onKeyUp="suggestion()" onKeyPress="checkEdit(event,'as','asvalue','asbutton')" list="suggest"/>
                        <label id="opsi"></label>
                        <%
                        out.println("<input type=button value=\"Add\" id=\"asbutton\" onclick=\"addAssignee("+idtugas+")\">");
                        %>
                        <select id="fieldhapus" name="fieldhapus">
                            <div id="daftarassignee">
                                <%
                                for (int i = 0; i < assignees.size(); i++) {
                                    out.println("<option value=\""+assignees.get(i)+"\">"+assignees.get(i)+"</option>");
                                }
                                %>
                            </div>
                        </select>
                        <%
                        out.println("<input type=button value=\"Hapus\" id=\"hapusbutton\" onclick=\"hapusAssignee("+idtugas+")\">");
                        %>
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
                                out.print(tags.get(j));
                                if (j < (tags.size() - 1)) 
                                    out.println(", ");
                            }
                        %>
                    </div>
                    <div class="viewtask_edit">
                        <input type=text name=tagtext id="tagtext" type="text" tabindex="4" />
                        <label id="opsi"></label>
                        <label id="opsi"></label>
                        <%
                        out.println("<input type=button value=\"Add\" id=\"tagbutton\" onclick=\"addTag("+idtugas+")\">");
                        %>
                        <select id="fieldhapustag" name="fieldhapustag">
                            <div id="daftartag">
                                <%
                                for (int i = 0; i < tags.size(); i++) {
                                    out.println("<option value=\""+tags.get(i)+"\">"+tags.get(i)+"</option>");
                                }
                                %>
                            </div>
                        </select>
                        <%
                        out.println("<input type=button value=\"Hapus\" id=\"hapustagbutton\" onclick=\"hapusTag("+idtugas+")\">");
                        %>
                    </div>
                </div>
                <div class="form_field">
                    <div class="viewtask_label">
                        Status
                    </div>
                    <div class="viewtask_field">
                        <%
                            String status = "";
                            if (tugas.getStatus().equalsIgnoreCase("done")) 
                                status += "checked";
                            out.println("<div id=\"status"+idtugas+"\" name=\"statustugas\">Status : <input type=checkbox name=\"status\" value=\"done\" "+status+"/ onchange=\"ChangeStatus("+idtugas+")\"></div>");
                        %>
						<%
						out.println("<button onclick=\"location.href='deletetask.php?q="+idtugas+"'\">Hapus Task...</button>");
						%>
                    </div>
                </div>
			
            </form> 
        </div>
        <div id="rightspace">
            <div>
                <div>
                    <fieldset id="fieldset">
                        <legend id="commentLabel">Comment</legend>
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
                       <input type=button value="Submit Comment" onClick="showComment(document.addcomment.isicomment.value,
                            <?php
                        echo "'".$_GET['q']."'";
                       ?>
                       ,
                       <?php
                       echo "'".$_SESSION['id']."'";
                       ?>
                       ,
                       <?php
                       echo "'".$_SESSION['pagenum']."'";
                       ?>
                       )">
                </div>
            </form>
        </div>
    </div>
</body>
</html>