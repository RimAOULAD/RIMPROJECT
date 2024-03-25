/**
 * This class defines endpoints for container-related operations.
 * Cette classe définit les points de terminaison pour les opérations liées aux conteneurs.
 */

package com.trakingcontainer.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trakingcontainer.dto.ContainerDTO;
import com.trakingcontainer.services.ContainerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerService;

    
/**
* Endpoint to retrieve all containers.
* Point de terminaison pour récupérer tous les conteneurs.
*/
    @GetMapping("/api/container/list")
    public ResponseEntity<List<ContainerDTO>> getAllContainers() {
        return new ResponseEntity<>(this.containerService.getContainers(), HttpStatus.OK);
    }

 /**
* Endpoint to create a new container.
* Point de terminaison pour créer un nouveau conteneur.
*/
    @PostMapping("/api/container/create")
    public ResponseEntity<ContainerDTO> save(@RequestParam String containerDTO,
            @RequestParam MultipartFile pictureFile) throws IOException {
        return new ResponseEntity<>(this.containerService.createContainer(containerDTO, pictureFile), HttpStatus.OK);

    }

 /**
* Endpoint to update an existing container.
* Point de terminaison pour mettre à jour un conteneur existant.
*/
    @PutMapping("/api/container/edit")
    public ResponseEntity<ContainerDTO> update(@RequestParam String containerDTO,
            @RequestParam MultipartFile pictureFile) throws IOException {

        return new ResponseEntity<>(this.containerService.updateContainer(containerDTO, pictureFile), HttpStatus.OK);
    }
/**
* Endpoint to delete a container by ID.
* Point de terminaison pour supprimer un conteneur par ID.
*/
    @DeleteMapping("/api/container/delete/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable("id") Long id) {
        this.containerService.deleteContainer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
/**
* Endpoint to retrieve the picture of a container by ID.
* Point de terminaison pour récupérer l'image d'un conteneur par ID.
*/
    @GetMapping("/api/container/getContainerPicture/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getContainerPicture(@PathVariable Long id) {
        byte[] pictureFile = this.containerService.getContainerPicture(id);

        if (pictureFile != null) {
            return ResponseEntity.ok().body(pictureFile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

public ContainerService getContainerService() {
    return containerService;
}

}
