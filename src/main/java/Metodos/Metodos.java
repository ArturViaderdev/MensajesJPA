/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

/**
 * Clase que almacena métodos
 * @author Artur Viader
 */
public class Metodos {
   
    /**
     * Constructor
     */
    public Metodos()
    {
        
    }
    
    /**
     * Obtiene el último texto después de la última barra de la url
     * @param url
     * @return 
     */
    public String obtenUltimoParametro(String url)
    {
        int cont = url.length()-1;
        char caracter;
        String idstring = "";
        boolean sal = false;
        while(!sal)
        {
            if(cont>0)
            {
                 caracter = url.charAt(cont);
                 if(caracter!='/')
                 {
                    idstring = caracter + idstring;
                    cont--;
                 }
                 else
                 {
                     sal =true;
                 }
            }
            else
            {
                sal = true;
            }
           
        }
        return idstring;
    }
}
