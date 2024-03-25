/**
 * This class represents an exception for a user not found.

 * Cette classe représente une exception pour un utilisateur non trouvé.
 */
package com.trakingcontainer.exception;

public class UserNotFoundException extends Exception {
      /**
     * Constructor taking the error message as a parameter.
     * @param message The error message associated with the exception.
     * Constructeur prenant le message d'erreur en paramètre.
     * @param message Le message d'erreur associé à l'exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
