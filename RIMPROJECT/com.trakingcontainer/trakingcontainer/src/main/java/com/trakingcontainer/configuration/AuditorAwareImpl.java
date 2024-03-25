/**
 * Cette classe fournit une implémentation de l'interface AuditorAware,
 * permettant de récupérer le nom de l'utilisateur actuellement authentifié
 * pour l'audit dans l'application.
 
  * This class provides an implementation of the AuditorAware interface,
 * allowing to retrieve the name of the currently authenticated user
 * for auditing purposes in the application.
 */
package com.trakingcontainer.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Méthode pour obtenir le nom de l'utilisateur actuellement authentifié.
     * @return Optionnel contenant le nom de l'utilisateur ou une chaîne vide si aucun utilisateur n'est authentifié.
     
      * Method to retrieve the name of the currently authenticated user.
     * @return Optional containing the name of the user or an empty string if no user is authenticated.
     
     
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        // Récupération de l'objet Authentication à partir du contexte de sécurité
        // Retrieve the Authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
              // If no user is authenticated, return an empty string
            // Si aucun utilisateur n'est authentifié, retourne une chaîne vide
            return Optional.of("");
        } else {
             // Otherwise, return the name of the authenticated user
            // Sinon, retourne le nom de l'utilisateur authentifié
            return Optional.of(authentication.getName());
        }
    }
}
