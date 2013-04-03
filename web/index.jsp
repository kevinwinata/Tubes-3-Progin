<%-- 
    Document   : index
    Created on : Apr 2, 2013, 2:05:42 PM
    Author     : Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action='UserController' name="frmAddUser">
        Username : <input type="text" name="username" /> <br /> 
        Password : <input type="text" name="password" /> <br /> 
        Fullname : <input type="text" name="fullname" /> <br /> 
        Birthdate : <input type="text" name="birthdate" /> <br /> 
        Phonenumber : <input type="text" name="phonenumber" /> <br /> 
        Email : <input type="text" name="email" /> <br /> 
        Avatar : <input type="text" name="avatar" /> <br /> 
        <input type="submit" value="Submit" />
        </form>
    </body>
</html>
