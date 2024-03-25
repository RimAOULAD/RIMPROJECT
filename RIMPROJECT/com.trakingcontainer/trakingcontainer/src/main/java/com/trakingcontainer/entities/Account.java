/**
 * Cette classe représente l'entité de compte dans la base de données.
 * Elle étend la classe Auditable pour hériter des champs d'audit tels que createdBy, createdDate, etc.
 * This class represents the account entity in the database.
 * It extends the Auditable class to inherit audit fields such as createdBy, createdDate, etc.
 */
package com.trakingcontainer.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.trakingcontainer.configuration.Auditable;

import com.trakingcontainer.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Account extends Auditable implements Serializable {

    @Id
    @GeneratedValue
    private Long accountId;
    @NotEmpty(message = "First Name  required")
    private String firstName;
    @NotEmpty(message = "Last Name  required")
    private String lastName;
    private String password;
    @Column(unique = true)
    @Email(message = "Email not valid")
    private String email;
    private boolean isActive;
    private boolean isNotLocked;
    private String resetPasswordToken;
    private Date resetPasswordTokenDuration;
    private String language;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String[] authorities;
    @Column(columnDefinition = "boolean default false")
    private boolean emailSent;
}
