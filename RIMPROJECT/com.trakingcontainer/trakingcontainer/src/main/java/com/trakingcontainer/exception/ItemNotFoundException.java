/**
 * This class represents an exception for an item not found.

 * Cette classe représente une exception pour un élément non trouvé.
 */

package com.trakingcontainer.exception;

public class ItemNotFoundException extends RuntimeException {
     /**
     * Constructor taking the error message as a parameter.
     * @param message The error message associated with the exception.
     * 
     * Constructeur prenant le message d'erreur en paramètre.
     * @param message Le message d'erreur associé à l'exception.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}
