/**
 * This class represents the Data Transfer Object (DTO) for account responses.
  * Cette classe représente l'objet de transfert de données (DTO) pour les réponses de compte.
 */
package com.trakingcontainer.dto;

import com.trakingcontainer.enumeration.Role;

import lombok.Data;

@Data
public class AccountResponseDTO {
 // Account properties
// Propriétés du compte
    private Long accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String language;
// Role-related properties
// Propriétés liées au rôle
    private Role role;
    private String[] authorities;
    private boolean isActive;

}
