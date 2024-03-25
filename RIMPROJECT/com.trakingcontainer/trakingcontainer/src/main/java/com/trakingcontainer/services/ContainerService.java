package com.trakingcontainer.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.trakingcontainer.dto.ContainerDTO;
// Interface définissant les méthodes pour la gestion des conteneurs
// Interface defining methods for managing containers
public interface ContainerService {
 // Method to retrieve the list of containers 
 // Méthode pour récupérer la liste des conteneurs   
    List<ContainerDTO> getContainers();
// Method to create a new container
// Méthode pour créer un nouveau conteneur

    ContainerDTO createContainer(String containerDTO, MultipartFile pictureFile) throws IOException;
// Method to update an existing container
// Méthode pour mettre à jour un conteneur existant
    ContainerDTO updateContainer(String containerDTO, MultipartFile pictureFile) throws IOException;
// Method to delete a container by its ID
// Méthode pour supprimer un conteneur par son ID
    void deleteContainer(Long id);
 
 // Method to retrieve the picture of a container by its ID
// Méthode pour récupérer l'image d'un conteneur par son ID
    byte[] getContainerPicture(Long id);
}
