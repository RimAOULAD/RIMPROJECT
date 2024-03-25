/**
 * Cette classe représente une exception pour une adresse e-mail déjà existante.

 * This class represents an exception for an existing email address.
 */
package com.trakingcontainer.exception;

public class EmailExistException extends Exception {
 /**
     * Constructeur prenant en paramètre le message d'erreur.
     * @param message Le message d'erreur associé à l'exception.

     * Constructor taking the error message as a parameter.
     * @param message The error message associated with the exception.
*/   

    public EmailExistException(String message) {
        super(message);
    }
}
