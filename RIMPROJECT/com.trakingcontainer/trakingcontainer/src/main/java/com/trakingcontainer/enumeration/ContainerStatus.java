/**
 * This enum represents the status of containers.
 * Cette énumération représente le statut des conteneurs.
 */
package com.trakingcontainer.enumeration;

public enum ContainerStatus {
    ARRIVING,    // Container is arriving // Le conteneur arrive
    PENDING,     // Container is pending// Le conteneur est en attente
    ONGOING,     // Container processing is ongoing// Le traitement du conteneur est en cours
    VERIFICATION // Container is under verification// Le conteneur est en cours de vérification
}
