/**
 * Cette interface fournit un accès à la base de données pour les opérations liées aux conteneurs.
 * This interface provides access to the database for container-related operations.
 */

package com.trakingcontainer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trakingcontainer.entities.Container;

public interface ContainerDAO extends JpaRepository<Container, Long> {
}
