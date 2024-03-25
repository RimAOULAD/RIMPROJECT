package com.trakingcontainer.security;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trakingcontainer.exception.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException exception) throws IOException {
 // Creating a custom HTTP response for forbidden access   
 // Création d'une réponse HTTP personnalisée pour l'accès interdit            
                                HttpResponse httpResponse = new HttpResponse(
            new Date(), // Current date and time// Date et heure actuelles
            FORBIDDEN.value(), // HTTP status code 403 (Forbidden)// Code de statut HTTP 403 (Interdit)
            FORBIDDEN, // HTTP status 403 (Forbidden)// Statut HTTP 403 (Interdit)
            FORBIDDEN.getReasonPhrase().toUpperCase(), // Reason for the error// Raison de l'erreur
            SecurityConstant.FORBIDDEN_MESSAGE // Forbidden access message defined in SecurityConstant// Message d'accès interdit défini dans SecurityConstant
        );
// Setting the content type of the response as JSON
 // Définition du type de contenu de la réponse en JSON
        response.setContentType(APPLICATION_JSON_VALUE);
        
// Setting the response status as HTTP 403 (Forbidden)
// Définition du statut de la réponse en HTTP 403 (Interdit)
        response.setStatus(FORBIDDEN.value());
 // Obtention du flux de sortie de la réponse       
// Getting the output stream of the response
        OutputStream outputStream = response.getOutputStream();
// Création d'un objet ObjectMapper pour convertir l'objet HttpResponse en JSON        
// Creating an ObjectMapper object to convert the HttpResponse object to JSON
        ObjectMapper mapper = new ObjectMapper();
        
// Writing the JSON response to the output stream
 // Écriture de la réponse JSON dans le flux de sortie
        mapper.writeValue(outputStream, httpResponse);
        
// Flushing the output stream
// Vidage du flux de sortie
        outputStream.flush();
    }
}
