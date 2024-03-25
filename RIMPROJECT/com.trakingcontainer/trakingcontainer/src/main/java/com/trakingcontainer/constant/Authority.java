/**
 * This class defines authorities (or permissions) for different roles in the application.
 * Cette classe définit les autorisations (ou permissions) pour différents rôles dans l'application.
 */
package com.trakingcontainer.constant;

public class Authority {
        // Autorisations pour les utilisateurs standard
        // Authorities for regular users
        public static final String[] USER_AUTHORITIES = { "users:read" };

        // Autorisations pour les administrateurs
        // Authorities for administrators
        public static final String[] ADMIN_AUTHORITIES = { "users:read", "users:create", "users:update",
                        "users:delete" };


        // Autorisations pour les super administrateurs
        // Authorities for super administrators
        public static final String[] SUPER_ADMIN_AUTHORITIES = { "users:read", "users:create", "users:update",
                        "users:delete" };


}
