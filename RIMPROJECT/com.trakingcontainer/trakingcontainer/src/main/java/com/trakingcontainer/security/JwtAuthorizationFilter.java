package com.trakingcontainer.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JWTTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Vérifie si la méthode de la requête est OPTIONS (pour CORS)
        // Check if the request method is OPTIONS (for CORS)
        if (request.getMethod().contains(SecurityConstant.OPTIONS_HTTP_METHOD)) {
            // Répond avec un statut HTTP OK
             // Respond with HTTP OK status
            response.setStatus(HttpStatus.OK.value());
        } else {
            // Obtient l'en-tête Authorization de la requête
             // Get the Authorization header from the request
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            
            // Check if the Authorization header is present and starts with the JWT token prefix
            // Vérifie si l'en-tête Authorization est présent et commence par le préfixe du token JWT
            if (authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) {
                // Passe la requête au filtre suivant dans la chaîne
                // Pass the request to the next filter in the chain
                filterChain.doFilter(request, response);
                return;
            }
            
            // Extrait le token JWT de l'en-tête Authorization
            // Extract the JWT token from the Authorization header
            String token = authorizationHeader.substring(SecurityConstant.TOKEN_PREFIX.length());
            
            // Récupère le nom d'utilisateur à partir du token JWT
            // Check if the JWT token is valid and there is no authentication already in progress
            String username = this.jwtTokenProvider.getSubject(token);
            
            // Get the authorities from the JWT token
            // Vérifie si le token JWT est valide et s'il n'y a pas déjà d'authentification en cours
            if (this.jwtTokenProvider.isTokenValid(username, token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                
                // Récupère les autorisations à partir du token JWT
                 // Create an instance of Authentication from the username and authorities
                List<GrantedAuthority> authorities = this.jwtTokenProvider.getAuthorities(token);
                
                 // Set the Authentication object in the security context
                // Crée une instance d'Authentication à partir du nom d'utilisateur et des autorisations
                Authentication authentication = this.jwtTokenProvider.getAuthentication(username, authorities, request);
                
                // Définit l'objet Authentication dans le contexte de sécurité
                // Clear the security context if the token is not valid
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Efface le contexte de sécurité si le token n'est pas valide
                // Pass the request to the next filter in the chain
                SecurityContextHolder.clearContext();
            }
        }
        
        // Passe la requête au filtre suivant dans la chaîne
        filterChain.doFilter(request, response);
    }
}
