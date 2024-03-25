/**
 * Cette énumération représente les rôles des utilisateurs dans le système.
 * Chaque rôle est associé à un ensemble d'autorisations.

 * This enumeration represents the roles of users in the system.
 * Each role is associated with a set of authorities.
 */

package com.trakingcontainer.enumeration;

import com.trakingcontainer.constant.Authority;

public enum Role {
    ROLE_SUPER_ADMIN(Authority.SUPER_ADMIN_AUTHORITIES), // Super administrateur avec autorisations étendues// Super admin with extended authorities
    ROLE_ADMIN(Authority.ADMIN_AUTHORITIES),             // Administrateur avec autorisations administratives// Admin with administrative authorities
    ROLE_USER(Authority.USER_AUTHORITIES);               // Utilisateur avec autorisations de base// User with basic authorities

    private final String[] authorities;// Les autorisations associées à chaque rôle// The authorities associated with each role
    Role(String... authorities) {
        this.authorities = authorities;
    }
    /**
     * Obtient la liste des autorisations associées au rôle.
     * @return La liste des autorisations.
    
     
     * Gets the list of authorities associated with the role.
     * @return The list of authorities.
     */

    public String[] getAuthorities() {
        return authorities;
    }

}
