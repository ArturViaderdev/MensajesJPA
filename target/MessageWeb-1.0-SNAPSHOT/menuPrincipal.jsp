<%-- 
    Document   : menuPrincipal
    Created on : 18-abr-2020, 10:39:59
    Author     : Administrador
--%>

<%@page import="model.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //Se obtiene el nombre de usuario del servlet
            String respuesta = (String) request.getAttribute("respuesta");
            if (respuesta != null) {
                %>
                <p>Bienvenido <%= respuesta %></p>
                <%
            }
        %>
        
        <%
            //Se obtiene el usuario que ha iniciado sesión del session
             User usuario = (User)request.getSession().getAttribute("usr");
             if(usuario.getType()==1)
             {
                %>
                     <form action="ListarUsuarios">
                        <input type="submit" value="Listar usuarios">
                     </form>
                     
                     <form action="TodosMensajes">
                        <input type="submit" value="Ver todos los mensajes">
                     </form>
                     
                     <form action="RankingUsuarios">
                        <input type="submit" value="Ranking usuarios">
                     </form>
                <%
              }
        
        %>
        
         <form action="cambiarPass.jsp">
            <input type="submit" value="Cambiar contraseña">
        </form>
        
        <form action="EnviarMensajeListUsers">
            <input type="submit" value="Enviar mensaje">
        </form>
        
        <form action="LeerMensajes">
             <input type="submit" value="Consultar mensajes">
        </form>
        
        <form action="MensajesEnviados">
             <input type="submit" value="Consultar mensajes enviados">
        </form>
        
   
                
       
    </body>
</html>
