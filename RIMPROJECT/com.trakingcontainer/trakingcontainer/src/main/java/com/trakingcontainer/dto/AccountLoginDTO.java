/**
 * Cette classe représente l'objet de transfert de données (DTO) pour la connexion d'un compte.
 * This class represents the Data Transfer Object (DTO) for account login.
 */
package com.trakingcontainer.dto;

import lombok.Data;

@Data
public class AccountLoginDTO {
    // Adresse email du compte
    // Email address of the account
    private String email;
    // Mot de passe du compte
    // Password of the account
    private String password;

}
