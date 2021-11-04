<%-- 
    Document   : loginUsuario
    Created on : 18-abr-2020, 10:34:02
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="LoginServlet">
            <p>Nombre de usuario: <input type="text" name="username" required></p>
            <p>Password: <input type="password" name="password" required></p>
            <input type="submit" name="login" value="Login">
        </form>
        <%
            //Se muestra la respuesta del servlet
            String respuesta = (String) request.getAttribute("respuesta");
            if (respuesta != null) {
                %>
                <p> <%= respuesta %></p>
                <%
            }
        %>
    </body>
</html>
