
/**
 * This class represents the entity for containers within the application.
 

 * Cette classe représente l'entité des conteneurs dans l'application.
 */
package com.trakingcontainer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.trakingcontainer.enumeration.ContainerStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Container {
     // Container properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConteneur;
    private String garges;
    private String position;
    private int etage;
    private String matricule;
    private ContainerStatus containerStatus;
    @Lob
    private byte[] picture;
}
