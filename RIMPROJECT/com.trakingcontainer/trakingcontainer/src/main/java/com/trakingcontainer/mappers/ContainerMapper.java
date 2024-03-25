package com.trakingcontainer.mappers;

import org.springframework.beans.BeanUtils;

import com.trakingcontainer.dto.ContainerDTO;
import com.trakingcontainer.entities.Container;

public class ContainerMapper {
// Méthode pour mapper un objet Container vers un objet ContainerDTO

    public static ContainerDTO toDTO(Container container) {
        if (container == null)
            return null;
// Créer un nouvel objet ContainerDTO
        ContainerDTO containerDTO = new ContainerDTO();
// Copier les propriétés de l'objet Container vers l'objet ContainerDTO
        BeanUtils.copyProperties(container, containerDTO);
        return containerDTO;
    }
 // Méthode pour mapper un objet ContainerDTO vers un objet Container
    public static Container toEntity(ContainerDTO containerDTO) {
        if (containerDTO == null)
            return null;
// Créer un nouvel objet Container
        Container container = new Container();
// Copier les propriétés de l'objet ContainerDTO vers l'objet Container
        BeanUtils.copyProperties(containerDTO, container);
        return container;
    }
}
