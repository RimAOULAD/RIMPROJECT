package com.trakingcontainer.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trakingcontainer.dao.ContainerDAO;
import com.trakingcontainer.dto.ContainerDTO;
import com.trakingcontainer.entities.Container;
import com.trakingcontainer.exception.ItemNotFoundException;
import com.trakingcontainer.mappers.ContainerMapper;
import com.trakingcontainer.services.ContainerService;
import com.trakingcontainer.utils.ConverterHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ContainerImpl implements ContainerService {
    private final ContainerDAO containerDAO;
// Method to retrieve all containers
// Méthode pour récupérer tous les conteneurs

    @Override
    public List<ContainerDTO> getContainers() {
// Fetch all containers from the database, map them to DTOs, and collect into a list
 // Récupérer tous les conteneurs de la base de données, les mapper en DTO et les collecter dans une liste
        return this.containerDAO.findAll()
                .stream().map(ContainerMapper::toDTO)
                .collect(Collectors.toList());

    }
// Method to create a new container
// Méthode pour créer un nouveau conteneur

    @Override
    public ContainerDTO createContainer(String containerDTO, MultipartFile pictureFile) throws IOException {
// Convert JSON string to ContainerDTO object
// Convertir la chaîne JSON en objet ContainerDTO

        ContainerDTO containerDTO2 = ConverterHelper.convertJson2Java(containerDTO, ContainerDTO.class);
// Map ContainerDTO to Container entity and set picture
  // Mapper ContainerDTO vers l'entité Container et définir l'image
        Container container = ContainerMapper.toEntity(containerDTO2);
        container.setPicture(pictureFile.getBytes());
  // Save the container and return its DTO
   // Enregistrer le conteneur et retourner son DTO

        return ContainerMapper.toDTO(this.containerDAO.save(container));
    }
// Method to update an existing container
// Méthode pour mettre à jour un conteneur existant

    @Override
    public ContainerDTO updateContainer(String containerDTO, MultipartFile pictureFile) throws IOException {
// Convert JSON string to ContainerDTO object
// Convertir la chaîne JSON en objet ContainerDTO
       
        ContainerDTO containerDTO2 = ConverterHelper.convertJson2Java(containerDTO, ContainerDTO.class);
// Map ContainerDTO to Container entity    
// Mapper ContainerDTO vers l'entité Container    
        Container container = ContainerMapper.toEntity(containerDTO2);
// Find the container to update    
 // Trouver le conteneur à mettre à jour    
        Optional<Container> updateContainer = this.containerDAO.findById(container.getIdConteneur());
        if (!updateContainer.isPresent())
            throw new ItemNotFoundException("Container Not Fount");
 // Copy properties from the updated container to the existing container  
// Copier les propriétés du conteneur mis à jour vers le conteneur existant         
        BeanUtils.copyProperties(container, updateContainer.get());
// Set the picture for the updated container
// Définir l'image pour le conteneur mis à jour
        updateContainer.get().setPicture(pictureFile.getBytes());
// Save the updated container and return its DTO
// Enregistrer le conteneur mis à jour et retourner son DTO
        return ContainerMapper.toDTO(this.containerDAO.save(updateContainer.get()));
    }
// Method to delete a container by its ID
// Méthode pour supprimer un conteneur par son ID
    @Override
    public void deleteContainer(Long id) {
// Find the container to delete
// Trouver le conteneur à supprimer
        Optional<Container> deleteContainer = this.containerDAO.findById(id);
        if (!deleteContainer.isPresent())
            throw new ItemNotFoundException("Container Not found");
 // Delete the container
 // Supprimer le conteneur
        containerDAO.deleteById(id);
    }
// Method to retrieve the picture of a container by its ID
// Méthode pour récupérer l'image d'un conteneur par son ID
    @Override
    public byte[] getContainerPicture(Long id) {
// Find the container
// Trouver le conteneur
        Optional<Container> container = this.containerDAO.findById(id);
        if (!container.isPresent())
            throw new ItemNotFoundException("Container Not Fount");
// Return the picture of the container
// Retourner l'image du conteneur
        return container.get().getPicture();
    }

}
