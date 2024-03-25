/**
 * This class represents the Data Transfer Object (DTO) for containers.
 * It extends the Auditable class to inherit auditing fields like createdBy, createdDate, etc.
 * Cette classe représente l'objet de transfert de données (DTO) pour les conteneurs.
 * Elle étend la classe Auditable pour hériter des champs d'audit tels que createdBy, createdDate, etc
 */
package com.trakingcontainer.dto;

import com.trakingcontainer.configuration.Auditable;
import com.trakingcontainer.enumeration.ContainerStatus;

import lombok.Data;

@Data
public class ContainerDTO extends Auditable {
    // Container properties
    // Propriétés du conteneur
    private Long idConteneur;
    private String garges;
    private String position;
    private int etage;
    private String matricule;
    private ContainerStatus containerStatus;

}
