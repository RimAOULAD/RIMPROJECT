
/**
 * This class serves as a base class for entities to inherit auditing fields.
 * It provides automatic tracking of creation and modification details.
 ---------------
 * Cette classe sert de classe de base pour les entités afin d'hériter des champs d'audit.
 * Elle permet le suivi automatique des détails de création et de modification.
 */


 

package com.trakingcontainer.configuration;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
/**
 * The Auditable class provides auditing fields and functionalities that can be inherited by entities.
 * La classe Auditable fournit des champs et des fonctionnalités d'audit pouvant être hérités par les entités.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter // Lombok annotation to generate getters for all fields
@Setter// Lombok annotation to generate setters for all fields
public abstract class Auditable {
      /**
     * Stores the username or identifier of the user who created the entity.
     * Stocke le nom d'utilisateur ou l'identifiant de l'utilisateur ayant créé l'entité.
     */

    @CreatedBy
    @Column(updatable = false)// Ensures that this field is not updatable once set //Assure que ce champ ne peut pas être modifié une fois défini
    protected String createdBy;
     /**
     * Stores the timestamp when the entity was created.
     * Stocke l'horodatage lorsque l'entité a été créée.
     */

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)// Specifies the temporal type of the date// Spécifie le type temporel de la date
    @Column(updatable = false)// Ensures that this field is not updatable once set Assure que ce champ ne peut pas être modifié une fois défini
    protected Date createdDate;
  /**
     * Stores the username or identifier of the user who last modified the entity.
      * Stocke le nom d'utilisateur ou l'identifiant de l'utilisateur ayant modifié pour la dernière fois l'entité.
     */
    @LastModifiedBy
    protected String lastModifiedBy;
      /**
     * Stores the timestamp when the entity was last modified.
     * Stocke l'horodatage lorsque l'entité a été modifiée pour la dernière fois.
     */

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)// Specifies the temporal type of the date// Spécifie le type temporel de la date
    protected Date lastModifiedDate;

}