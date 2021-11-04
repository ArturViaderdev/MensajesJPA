/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import exceptions.MsgException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import model.entities.Event;
import model.entities.Message;
import model.entities.User;

/**
 * Clase que interactúa con la base de datos
 * @author Artur Viader
 */
@Stateless
public class MsgBean {
@PersistenceUnit EntityManagerFactory emf;
    
    /**
    * Inserta un usuario
    * @param user Usuario
    * @throws MsgException 
    */
    public void insertAlumno(User user) throws MsgException {
        EntityManager em = emf.createEntityManager();
        //Se comprueba que no existe un nombre de usuario igual y se genera excepción
        User aux = em.find(User.class, user.getUsername());
        if (aux != null) {
            throw new MsgException(MsgException.USUARIOEXISTE);
        }
        //Se guarda
        em.persist(user);
        em.close();
    }
    
    /**
     * Cambia el password del usuario
     * @param username Usuario que quiere cambiar su password
     * @param password Antiguo password
     * @param newPassword Nuevo password
     * @throws MsgException Puede devolver excepción
     */
    public void cambiarPass(String username, String password, String newPassword) throws MsgException
    {
        EntityManager em = emf.createEntityManager();
        //Se obtiene el usuario
        User aux = em.find(User.class, username);
        if (aux == null) {
            //Caso en que el usuario no existe
            throw new MsgException(MsgException.USUARIONOEXISTE);
        }
        else
        {
            //Se comprueba el password antiguo
            if(!aux.getPassword().equals(password))
            {
                throw new MsgException(MsgException.PASSWORDINCORRECTO);
            }
            //Se cambia el password y se guarda
            aux.setPassword(newPassword);
            em.persist(aux);
        }
        em.close();
    }
    
    /**
     * Registra un evento
     * @param usuario Usuario
     * @param operacion Tipo de evento
     */
     public void registrarEvento(User usuario, String operacion){
        EntityManager em = emf.createEntityManager();
        Event evento = new Event();
        evento.setUser(usuario);
        evento.setDate(new Date());
        evento.setType(operacion);
        //Se guarda
        em.persist(evento);
        em.close();
    }
    
     /**
      * Envía un mensaje
      * @param origen Usuario que envía
      * @param user Destinatario
      * @param mensaje Cuerpo del mensaje
      * @param asunto Asunto
      * @throws MsgException
      */
     public void enviarMensaje(User origen, String user,String mensaje, String asunto) throws MsgException{
        EntityManager em = emf.createEntityManager();
        Message message = new Message();
        message.setDate(new Date());
        message.setBody(mensaje);
        message.setSeen(0);
        
        //El usuario no hace falta leerlo de la bdd según esta modificación ya no se genera excepción nunca
        //User receiver = em.find(User.class, user);
        //if (receiver == null) {
        //    throw new MsgException(MsgException.USUARIONOEXISTE);
        //}
        User receiver = new User(user);
        message.setReceiver(receiver);
        message.setSender(origen);
        message.setSubject(asunto);
        //Se guarda el mensaje
        em.persist(message);
        em.close();
    }

    /**
     * Hacer login
     * @param username
     * @param password
     * @return
     * @throws MsgException 
     */
    public User login(String username, String password) throws MsgException {
        EntityManager em = emf.createEntityManager();
        
        User aux = em.find(User.class, username);
        if (aux == null) {
            //Caso en que el usuario no existe
            throw new MsgException(MsgException.LOGININCORRECTO);
        }
        else
        {
            if (!aux.getPassword().equals(password))
            {
                //Caso en que el password no es correcto
                throw new MsgException(MsgException.LOGININCORRECTO);
            }
        }
        em.close();
        return aux;
        
    }
    
    /**
     * Obtiene un usuario por su nombre de usuario
     * @param username
     * @return 
     */
    public User getUsuario(String username){
        //Se lee y se devuelve el usuario
        EntityManager em = emf.createEntityManager();
        User aux = em.find(User.class, username);
        em.close();
        return aux;   
    }
    
    /**
     * Borra un usuario
     * @param usuario 
     */
    public void deleteUsuario(User usuario)
    {
        EntityManager em = emf.createEntityManager();
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
        
        em.close();
    }
    
    /**
     * Obtiene todos los usuarios menos uno especificado
     * @param username
     * @return 
     */
    public List<User> getUsuarios(String username)
    {
       EntityManager em = emf.createEntityManager();
       Query query = em.createNamedQuery("User.findNotUsername", User.class);
       query.setParameter("username", username);

       List<User> result = query.getResultList();
        em.close();
       return result;
        
    }
    
    /**
     * Obtiene todos los mensajes que ha recibido un usuario
     * @param usuario
     * @return 
     */
    public List<Message> getMensajes(User usuario)
    {
       String username = usuario.getUsername();
       EntityManager em = emf.createEntityManager();
       Query query = em.createNamedQuery("Message.findByReceiver", Message.class);
       query.setParameter("receiver", new User(username));
       List<Message> result = query.getResultList();
        em.close();
       return result;
    }
    
    /**
     * Obtiene todos los mensajes que ha enviado un usuario
     * @param usuario
     * @return 
     */
    public List<Message> getMensajesEnviados(User usuario)
    {
       String username = usuario.getUsername();
       EntityManager em = emf.createEntityManager();
       Query query = em.createNamedQuery("Message.findBySender", Message.class);
       query.setParameter("sender", new User(username));
       List<Message> result = query.getResultList();
        em.close();
       return result;
    }
    
    /**
     * Obtiene el número de mensajes enviados
     * @param usuario
     * @return 
     */
    public int getNumMensajesEnviados(User usuario)
    {
        //Se podría hacer un count pero al no ser sql estandard he preferido hacerlo así
        List<Message> mensajes = getMensajesEnviados(usuario);
        return mensajes.size();             
    }
    
    /**
     * Obtiene todos los mensajes
     * @return 
     */
    public List<Message> getAllMensajes()
    {
       EntityManager em = emf.createEntityManager();
       Query query = em.createNamedQuery("Message.findAll", Message.class);
       List<Message> result = query.getResultList();
        em.close();
       return result;
    }
    
    /**
     * Obtiene un mensaje por el id y lo marca como visto
     * @param id
     * @return 
     */
     public Message getMensaje(int id)
    {
       EntityManager em = emf.createEntityManager();
       //Se lee el mensaje
       Query query = em.createNamedQuery("Message.findById", Message.class);
       query.setParameter("idmessage", id);
       List<Message> result = query.getResultList();
       //No compruebo si hay mensajes porque como no se pueden borrar siempre debe de existir al haberse mostrado en la tabla anterior, podría poner una excepción
       //Si el mensaje no ha sido visto anteriormente se marca como visto
       if(result.get(0).getSeen() == 0)
       {
           result.get(0).setSeen(1);
           //Se guarda que se ha leido
           em.persist(result.get(0));
       }
        em.close();
       return result.get(0);
    }
     
     /**
      * Se obtiene el número de usuarios
      * @return 
      */
    public int getNumUsers()
    {
       EntityManager em = emf.createEntityManager();
       Query query = em.createNamedQuery("User.findAll", User.class);
       
       List<User> result = query.getResultList();
        em.close();
        
       return result.size();
        
    }
    
    /**
     * Se obtienen todos los usuarios
     * @return 
     */
    public List<User> getAllUsuarios()
    {
       EntityManager em = emf.createEntityManager();
       Query query = em.createNamedQuery("User.findAll", User.class);
       
       List<User> result = query.getResultList();
       em.close();
       return result;
        
    }
    
    /**
     * Se obtiene la fecha del último inicio de sesión de un usuario
     * @param user
     * @return 
     */
    public Date getLastLogin(User user)
    {
         EntityManager em = emf.createEntityManager();
         //Se obtiene la lista de inicios de sesión ordenada
         Query query = em.createNamedQuery("Event.findByUser", User.class);
         query.setParameter("username", user);
         List<Event> result = query.getResultList();
         //Si el usuario ha iniciado sesión se devuelve la fecha
         if(result.size()>0)
         {
             return result.get(0).getDate();
         }
         else
         {
             //Si el usuario no ha iniciado sesión se devuelve null
             return null;
         }
    }
    
}
