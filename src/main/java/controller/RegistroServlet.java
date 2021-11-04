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
import model.bean.MsgBean;
import model.entities.User;

/**
 * Servlet para registrar un usuario
 * @author Artur Viader
 */
public class RegistroServlet extends HttpServlet {
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
        String boton = request.getParameter("registrar");
        if (boton.equals("Registrar")) {
            // Recogemos datos del formulario
            //Se obtienen los campos del jsp
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname"); 
            //int type= Integer.parseInt(request.getParameter("type"));
            User user;
            //Si no hay usuarios el primero que se crea es administrador, si los hay se crean del tipo usuario
            if(miDao.getNumUsers()>0)
            {
                user = new User(username, password, name, surname, 0);
            }
            else
            {
                user = new User(username, password, name, surname, 1);
            }
            
            
            try {
                //Se inserta el usuario en la bdd
                miDao.insertAlumno(user);
                request.setAttribute("respuesta", "Usuario registrado");
            } catch (MsgException ex) {
                //Puede dar excepción si el usuario ya existía
                request.setAttribute("respuesta", ex.getMessage());
            }
            request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
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
