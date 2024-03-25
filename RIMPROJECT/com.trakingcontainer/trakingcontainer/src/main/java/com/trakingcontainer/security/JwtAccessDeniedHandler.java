package com.trakingcontainer.security;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trakingcontainer.exception.HttpResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
// Création d'une réponse HTTP personnalisée pour l'accès refusé
// Creating a custom HTTP response for access denied
    HttpResponse httpResponse = new HttpResponse(
            new Date(), // Date et heure actuelles// Current date and time
            UNAUTHORIZED.value(), // Code de statut HTTP 401 (Unauthorized)// HTTP status code 401 (Unauthorized)
            UNAUTHORIZED, // Statut HTTP 401 (Unauthorized)// HTTP status 401 (Unauthorized)
            UNAUTHORIZED.getReasonPhrase().toUpperCase(), // Raison de l'erreur// Reason for the error
            SecurityConstant.ACCESS_DENIED_MESSAGE // Message d'accès refusé défini dans SecurityConstant / Access denied message defined in SecurityConstant
        );
// Définition du type de contenu de la réponse comme JSON  
 // Setting the content type of the response as JSON              
        response.setContentType(APPLICATION_JSON_VALUE);
// Définition du statut de la réponse comme HTTP 401 (Unauthorized)
 // Setting the response status as HTTP 401 (Unauthorized)
        response.setStatus(UNAUTHORIZED.value());
// Récupération du flux de sortie de la réponse
// Getting the output stream of the response
        OutputStream outputStream = response.getOutputStream();
// Création d'un objet ObjectMapper pour convertir l'objet HttpResponse en JSON
// Creating an ObjectMapper object to convert the HttpResponse object to JSON
        ObjectMapper mapper = new ObjectMapper();
 // Écriture de la réponse JSON dans le flux de sortie
  // Writing the JSON response to the output stream
        mapper.writeValue(outputStream, httpResponse);
// Vidage du flux de sortie
// Flushing the output stream
        outputStream.flush();

    }

}
