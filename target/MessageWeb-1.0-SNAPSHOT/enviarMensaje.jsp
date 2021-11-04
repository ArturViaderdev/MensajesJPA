<%-- 
    Document   : enviarMensaje
    Created on : 18-abr-2020, 12:01:31
    Author     : Administrador
--%>

<%@page import="java.util.List"%>
<%@page import="model.entities.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enviar mensaje</h1>
        <form method="POST" action="EnviarMensaje">
            
            
        
        <b>Usuario destino:</b>   <select name="user">
           
        <% 
            //Se obtiene la lista de usuarios
        List<User> users = (List<User>) request.getAttribute("users");
       //Para cada usuario se muestra
        for (User user : users) {
            %>
            <option value="<%= user.getUsername() %>"><%= user.getName() %></option>
        <% }
        %>
           </select>
           <br>
           <b>Mensaje</b>
           <textarea name="mensaje"></textarea>
           <br>
           <b>Asunto:</b>
           <p>Asunto: <input type="text" name="asunto" required></p>
           <br>
            <input type="submit" name="enviar" value="Enviar">
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
           

        <br>
        <a href="menuPrincipal.jsp">Volver al menú.</a>
    </body>
</html>
