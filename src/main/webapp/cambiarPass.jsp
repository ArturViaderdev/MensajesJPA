<%-- 
    Document   : cambiarPass
    Created on : 18-abr-2020, 10:51:23
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
        <h1>Cambiar contraseña</h1>
        <form method="POST" action="CambiaPassServlet">
            <p>Password <input type="password" name="password" required></p>
            <p>Nuevo password: <input type="text" name="newPassword" required></p>
            <input type="submit" name="cambiarPass" value="cambiarPass">
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
        
        <a href="menuPrincipal.jsp">Volver al menú.</a>
    </body>
</html>
