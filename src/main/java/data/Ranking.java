/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import model.entities.User;

/**
 * Clase que almacena un valor del ranking de usuarios con número de mensajes enviados
 * @author arturv
 */
public class Ranking implements Comparable<Ranking>{
       private User user;
       private int numMensajes;
       
       /**
        * Constructor
        * @param user Usuario
        * @param numMensajes Número de mensajes que ha enviado
        */
       public Ranking(User user, int numMensajes)
       {
           this.user = user;
           this.numMensajes = numMensajes;
       }
       
       /**
        * Establece el usuario
        * @param user 
        */
       public void setUser(User user)
       {
           this.user = user;
       }
       
       /**
        * Establece el número de mensajes
        * @param numMensajes 
        */
       public void setNumMensajes(int numMensajes)
       {
           this.numMensajes = numMensajes;
       }
       
       /**
        * Devuelve el usuario
        * @return 
        */
       public User getUser()
       {
           return user;
       }
       
       /**
        * Devuelve el número de mensajes
        * @return 
        */
       public int getNumMensajes()
       {
           return numMensajes;
       }

       /**
        * Realiza la comparación para ordenar la lista
        * @param t
        * @return 
        */
    @Override
    public int compareTo(Ranking t) {	
	return t.getNumMensajes() - this.numMensajes;
    }
}
