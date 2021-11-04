<%-- 
    Document   : registrarUsuario
    Created on : 16-abr-2020, 17:29:57
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
        <h1>Registrar usuario</h1>
        <form method="POST" action="RegistroServlet">
            <p>Nombre de usuario: <input type="text" name="username" required></p>
            <p>Password <input type="password" name="password" required></p>
            <p>Nombre: <input type="text" name="name" required></p>
            <p>Apellido: <input type="text" name="surname" required></p>
            <input type="submit" name="registrar" value="Registrar">
        </form>
        <%
            //Se obtiene la respuesta del servlet
            String respuesta = (String) request.getAttribute("respuesta");
            if (respuesta != null) {
                %>
                <p> <%= respuesta %></p>
                <%
            }
        %>
        
        <a href="index.html">Volver a página principal.</a>
    </body>
</html>
