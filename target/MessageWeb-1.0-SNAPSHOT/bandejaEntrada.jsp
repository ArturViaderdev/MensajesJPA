<%-- 
    Document   : bandejaEntrada
    Created on : 19 d’abr. 2020, 0:28:44
    Author     : arturv
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.entities.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bandeja</h1>
        <table>   
            <tr>
                <th>Emisor</th>
                <th>Fecha-Hora</th>
                <th>Asunto</th>
                <th>Leído</th>
            </tr>
        <% 
            //Obtiene la lista de mensajes
        List<Message> mensajes = (List<Message>) request.getAttribute("mensajes");
        //Se prepara para convertir fecha a string
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String leido;
        //Se muestra cada mensaje
        for (Message mensaje : mensajes) {
            //Se prepara la palabra si o no dependiendo de si el mensaje ha sido visto
            if(mensaje.getSeen()==0)
            {
                leido="No";
            }
            else
            {
                leido="Si";
            }

            %>
            <tr>
                <td><%= mensaje.getSender().getName() %></td> 
                <td><%= sdf.format(mensaje.getDate())  %></td> 
                <td><a href="MuestraMensaje/id/<%= mensaje.getIdmessage().toString() %>"><%= mensaje.getSubject() %></a></td>
                <td><%= leido %></td>
            </tr>
        </table>
        <% }
        %>
        

    </body>
</html>
