/**
 * This class represents the Data Transfer Object (DTO) for an account.
* Cette classe représente l'objet de transfert de données (DTO) pour un compte.
 */
package com.trakingcontainer.dto;

import java.util.Date;

import com.trakingcontainer.enumeration.Role;

import lombok.Data;

@Data
public class AccountDTO {
// Account properties
// Propriétés du compte
    private Long accountId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean isActive;
    private boolean isNotLocked;
    private String resetPasswordToken;
    private Date resetPasswordTokenDuration;
    private String language;
    // Propriétés liées au rôle
    private Role role;
    private String[] authorities;
}
