<%-- 
    Document   : muestraRanking
    Created on : 20 d’abr. 2020, 6:46:31
    Author     : arturv
--%>

<%@page import="data.Ranking"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ranking</h1>
        
          <table>   
            <tr>
                <th>Nombre de usuario</th>
                <th>Número de mensajes enviados</th>
            </tr>
        <% 
            //Se obtiene el ranking
        List<Ranking> ranking = (List<Ranking>) request.getAttribute("ranking");
        
        //Se muestra el ranking
          for (Ranking elemento : ranking) {
          
       %>
            <tr>
                <td><%= elemento.getUser().getUsername() %></td> 
                <td><%= elemento.getNumMensajes()  %></td> 
            </tr>
        </table>
        <% }  
         
        %>
    </body>
</html>
