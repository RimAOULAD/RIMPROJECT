/**
 * This class represents an exception for a non-existing email address.

 * Cette classe représente une exception pour une adresse e-mail non trouvée.
 */
package com.trakingcontainer.exception;

public class EmailNotFoundException extends Exception {
 /**
     * Constructor taking the error message as a parameter.
     * @param message The error message associated with the exception.
      * Constructeur prenant en paramètre le message d'erreur.
     * @param message Le message d'erreur associé à l'exception.
*/
    public EmailNotFoundException(String message) {
        super(message);
    }

}
