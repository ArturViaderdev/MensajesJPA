/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.MsgException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.MsgBean;
import model.entities.User;

/**
 * Servlet para enviar mensaje
 * @author Artur Viader
 */
public class EnviarMensaje extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String boton = request.getParameter("enviar");
        //Se obtiene el usuario que ha iniciado sesión del session
        User usuario = (User)request.getSession().getAttribute("usr"); 
        //Se lee la lista de todos los usuarios excepto el que ha iniciado sesión para enviar de vuelta al jsp
        List<User> users = miDao.getUsuarios(usuario.getUsername());
        request.setAttribute("users", users);
        if (boton.equals("Enviar")) {
            try {
                // Recogemos datos del formulario
                //Se obtienen los datos que se han introducido en el jsp
                String user = request.getParameter("user");
                String mensaje = request.getParameter("mensaje");
                String asunto = request.getParameter("asunto");
                HttpSession session = request.getSession();
                User origen = (User) session.getAttribute("usr");
                
                //Se envia el mensaje
                miDao.enviarMensaje(origen,user,mensaje,asunto);
                miDao.registrarEvento(origen,"R");
                
                request.setAttribute("respuesta", "Mensaje enviado");
                
             } catch (MsgException ex) {
                 request.setAttribute("respuesta", ex.getMessage());
             }
        } 
        request.getRequestDispatcher("/enviarMensaje.jsp").forward(request, response);
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
