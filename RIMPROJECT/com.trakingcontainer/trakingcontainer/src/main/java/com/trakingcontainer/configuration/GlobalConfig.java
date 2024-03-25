/**
 * This class provides global configurations for the application,
 * including CORS configuration, JPA auditing, password encoding,
 * and initialization of some default data.
 
 
 * Cette classe fournit des configurations globales pour l'application,
 * incluant la configuration CORS, l'audit JPA, le cryptage des mots de passe,
 * et l'initialisation de certaines données par défaut.
 */

package com.trakingcontainer.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.trakingcontainer.dao.AccountDAO;
import com.trakingcontainer.entities.Account;
import com.trakingcontainer.enumeration.Role;

@Configuration
@EnableJpaAuditing
public class GlobalConfig {
 /**
 * Configures CORS filters to allow cross-origin requests.
 * Configure les filtres CORS pour autoriser les requêtes cross-origin.
 
 */

        @Bean
        public CorsFilter corsFilter() {
                UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowCredentials(true);
                // corsConfiguration.setAllowedOrigins(Collections.singletonList(FRONT_END_URL));
                corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
                corsConfiguration.setAllowedHeaders(Arrays.asList(
                                "Origin", "Access-Control-Allow-Origin",
                                "Content-Type", "Accept",
                                "Jwt-Token", "Authorization",
                                "Origin, Accept", "X-Requested-With",
                                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
                corsConfiguration.setExposedHeaders(Arrays.asList(
                                "Origin", "Content-Type",
                                "Accept", "Jwt-Token",
                                "Authorization", "Access-Control-Allow-Origin",
                                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
                corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
                urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
                return new CorsFilter(urlBasedCorsConfigurationSource);

        }
/**
* Provides a BCrypt password encoder bean.
Fournit un bean d'encodeur de mot de passe BCrypt.
*/
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

/**
* Initializes default data upon application startup using CommandLineRunner.
*Initialise des données par défaut lors du démarrage de l'application en utilisant CommandLineRunner.

*/ 

        @Bean
        public CommandLineRunner run(
                        AccountDAO accountDAO,
                        BCryptPasswordEncoder passwordEncoder) {
                return args -> {
 // Check if admin account exists, if not, create one     
 // Vérifie si le compte admin existe, sinon le crée  
                        Optional<Account> neAccount = accountDAO
                                        .findAccountByEmailIgnoreCase("admin@gmail.com");
                        if (!neAccount.isPresent()) {
                                Account account = new Account();
                                account.setEmail("admin@gmail.com");
                                account.setFirstName("admin");
                                account.setLastName("admin");
                                account.setPassword(passwordEncoder.encode("admin"));
                                account.setLanguage("en");
                                account.setNotLocked(true);
                                account.setActive(true);
                                account.setRole(Role.ROLE_SUPER_ADMIN);
                                account.setAuthorities(Role.ROLE_SUPER_ADMIN.getAuthorities());
                                account.setEmailSent(true);
                                accountDAO.save(account);
                        }
// Check if rim user account exists, if not, create one      
// Vérifie si le compte utilisateur rim existe, sinon le crée                  
                        Optional<Account> neAccount2 = accountDAO
                                        .findAccountByEmailIgnoreCase("rim@gmail.com");
                        if (!neAccount2.isPresent()) {
                                Account account = new Account();
                                account.setEmail("rim@gmail.com");
                                account.setFirstName("rim");
                                account.setLastName("user");
                                account.setPassword(passwordEncoder.encode("rim123"));
                                account.setLanguage("en");
                                account.setNotLocked(true);
                                account.setActive(true);
                                account.setRole(Role.ROLE_USER);
                                account.setAuthorities(Role.ROLE_USER.getAuthorities());
                                account.setEmailSent(true);
                                accountDAO.save(account);
                        }

                };

        }
/**
* Provides a RestTemplate bean for making HTTP requests.
* Fournit un bean RestTemplate pour effectuer des requêtes HTTP.
*/
        @Bean
        public RestTemplate restTemplate() {
                return new RestTemplate();
        }
}
