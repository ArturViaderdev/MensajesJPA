<%-- 
    Document   : muestraMensaje
    Created on : 19 d’abr. 2020, 9:21:08
    Author     : arturv
--%>

<%@page import="model.entities.Message"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ver mensaje</h1>
        <% 
            //Se obtiene el mensaje
            Message mensaje = (Message)request.getAttribute("mensaje");
        %>
        <p><b>Origen:</b><%= mensaje.getSender().getName() %></p>
        <p><b>Destino:</b><%= mensaje.getReceiver().getName() %></p>
        <p><b>Asunto:</b><%= mensaje.getSubject() %></p>
        <p><b>Fecha:</b><%= mensaje.getDate().toString() %></p>
        <p><b>Mensaje:</b><%= mensaje.getBody() %></p>
            
    </body>
</html>
