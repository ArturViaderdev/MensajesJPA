<%-- 
    Document   : listarUsuarios
    Created on : 19 d’abr. 2020, 21:32:02
    Author     : arturv
--%>

<%@page import="model.entities.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listar usuarios</h1>
          <table>   
            <tr>
                <th>Nombre de usuario</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Tipo</th>
                <th></th>
                <th></th>
            </tr>
        <% 
            //Se obtiene la lista de usuarios
        List<User> usuarios = (List<User>) request.getAttribute("users");
        String tipo;
        //Cada usuario se muestra
          for (User usuario : usuarios) {
              //Se prepara la palabra usuario o administrador según el tipo de usuario
            if(usuario.getType()==0)
            {
                tipo="Usuario";
            }
            else
            {
                tipo="Administrador";
            }

       %>
            <tr>
                <td><%= usuario.getUsername() %></td> 
                <td><%= usuario.getName()  %></td> 
                <td><%= usuario.getSurname() %></td>
                <td><%= tipo %></td>
                <td><a href="EliminaUsuario/username/<%= usuario.getUsername().toString() %>">Eliminar</a></td>
                <td><a href="UltimoInicio/username/<%= usuario.getUsername().toString() %>">Último inicio de sesión</a></td>
            </tr>
        </table>
        <% }  
         
        %>
    </body>
</html>
