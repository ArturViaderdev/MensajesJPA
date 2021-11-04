/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.MsgException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.MsgBean;
import model.entities.User;

/**
 * Servlet para el login
 * @author Administrador
 */
public class LoginServlet extends HttpServlet {
@EJB
     MsgBean miDao;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean logeado = false;
      String boton = request.getParameter("login");
        if (boton.equals("Login")) {
            // Recogemos datos del formulario
            //Se obtienen el nombre de usuario y el password del jsp
            String username = request.getParameter("username");
            String password = request.getParameter("password");
           
            try {
                //Se hace login y se obtiene el usuario, puede fallar si el login es incorrecto
                User user = miDao.login(username,password);
                //Se registra el evento de inicio de sesión
                miDao.registrarEvento(user,"I");
                HttpSession session = request.getSession();
                //Se guarda en session el usuario que ha iniciado sesión
                session.setAttribute("usr", user);
                logeado = true;
                //Se envia al jsp la respuesta que es el nombre de usuario
                request.setAttribute("respuesta", username);
            } catch (MsgException ex) {
                //Si falla el inicio de sesión se manda al jsp el mensaje
                request.setAttribute("respuesta", ex.getMessage());
            }
            if(logeado)
            {
                //Si se ha hecho login se va al menú principal
                request.getRequestDispatcher("/menuPrincipal.jsp").forward(request, response);
            }
            else
            {
                //Si no se ha hecho login se vuelve al jsp de login
                request.getRequestDispatcher("/loginUsuario.jsp").forward(request, response);
            }
            
        } 
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
