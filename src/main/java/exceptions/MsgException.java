/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.util.Arrays;
import java.util.List;

/**
 * Clase que almacena excepciones
 * @author Administrador
 */
public class MsgException extends Exception {
    public static final int USUARIOEXISTE =0;
    public static final int LOGININCORRECTO = 1;
    public static final int USUARIONOEXISTE = 2;
    public static final int PASSWORDINCORRECTO = 3;
     private int code;
    
     private final List<String> messages = Arrays.asList(
       "El usuario ya existe",
       "Login incorrecto",
       "El usuario no existe, se ha borrado de la base de datos",
       "Password incorrecto"
     );

     /**
      * Constructor
      * @param code Código de excepción 
      */
    public MsgException(int code) {
        this.code = code;
    }

    /**
     * Devuelve el mensaje de la excepción correspondiente al código
     * @return Mensaje
     */
    @Override
    public String getMessage() {
        return messages.get(code);
    }
}
