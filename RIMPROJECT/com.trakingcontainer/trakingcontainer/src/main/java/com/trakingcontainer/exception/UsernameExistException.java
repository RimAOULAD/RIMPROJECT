/**
 * This class represents an exception for an existing username.

 * Cette classe représente une exception pour un nom d'utilisateur déjà existant.
 */
package com.trakingcontainer.exception;

public class UsernameExistException extends Exception {
    /**
     * Constructor taking the error message as a parameter.
     * @param message The error message associated with the exception.
     * 
     * Constructeur prenant le message d'erreur en paramètre.
     * @param message Le message d'erreur associé à l'exception.
     */
    public UsernameExistException(String message) {
        super(message);
    }
}
