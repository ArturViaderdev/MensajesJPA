<%--    
    Document   : ultimoInicio
    Created on : 20 d’abr. 2020, 6:12:46
    Author     : arturv
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Último inicio de sesión</h1>
        <% 
            //Se obtiene la fecha
            Date fecha = (Date)request.getAttribute("fecha");
            if(fecha==null)
            {
                %>
                <p><b>Fecha:</b>Nunca ha iniciado sesión</p>
                <%
            }
            else
            {

             %>
                <p><b>Fecha:</b><%=fecha.toString() %></p>
                <%
                    }
%>
    </body>
</html>
