/**
 * This class defines constant messages related to user operations.
 */
package com.trakingcontainer.constant;

public class UserImplConstant {
    // Error message for username already existing
    // Message d'erreur pour un nom d'utilisateur déjà existant

    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    // Error message for email already existing
    // Message d'erreur pour un e-mail déjà existant
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    // Error message for no user found by email
     // Message d'erreur pour aucun utilisateur trouvé par e-mail
    public static final String NO_USER_FOUND_BY_EMAIL = "No users found by Email: ";
    // Error message for invalid token sent
    // Message d'erreur pour un jeton invalide envoyé
   
    public static final String TOKEN_SENT_NO_VALID = "TOKEN SENT NO VALID";
   
    // Success message for password change
    // Message de succès pour le changement de mot de passe
    
    public static final String PASSWORD_CHANGED_SUCCESSFULLY = "PASSWORD CHANGED SUCCESSFULLY. CONNECT WITH YOUR NEW PASSWORD";
    // Error message for no user found by username
   // Message d'erreur pour aucun utilisateur trouvé par nom d'utilisateur

    
    public static final String NO_USER_FOUND_BY_USERNAME = "No users found by username: ";
    // Info message for found user by username
    // Message d'information pour l'utilisateur trouvé par nom d'utilisateur
   
   
    public static final String FOUND_USER_BY_USERNAME = "Returning found users by username: ";
    // Info message for successful email sent
    // Message d'information pour l'e-mail envoyé avec succès
    
    public static final String EMAIL_SENT = "EMAIL SENT SUCCESSFULLY TO : ";
     // Common constant for dot character
     // Constante commune pour le caractère point
   
    public static final String DOT = ".";
    // Error message for locked account
    // Message d'erreur pour un compte verrouillé
   
    public static final String ACCOUNT_LOCKED = "YOUR ACCOUNT LOCKED";
    // Error message for unsupported content type for profile photo
    // Message d'erreur pour un type de contenu non pris en charge pour la photo de profil
    public static final String SUPPORTED_CONTENT_TYPE_PROFILE_PHOTO = "Only PNG or JPG , JPEG , images are allowed";
    
    // Error message for unsupported content type
    // Message d'erreur pour un type de contenu non pris en charge
    public static final String SUPPORTED_CONTENT_TYPE = "Only PDF,PNG or JPG , JPEG , images are allowed";


}
