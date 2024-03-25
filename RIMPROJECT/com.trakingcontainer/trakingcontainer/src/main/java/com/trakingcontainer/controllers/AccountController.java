/**
 * This class defines endpoints for account-related operations.
* Cette classe définit les points de terminaison pour les opérations liées aux comptes utilisateurs.
 */
package com.trakingcontainer.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trakingcontainer.dto.AccountDTO;
import com.trakingcontainer.dto.AccountLoginDTO;
import com.trakingcontainer.dto.AccountResponseDTO;
import com.trakingcontainer.exception.ExceptionHandling;
import com.trakingcontainer.mappers.AccountMapper;
import com.trakingcontainer.security.JWTTokenProvider;
import com.trakingcontainer.security.SecurityConstant;
import com.trakingcontainer.security.UserPrincipal;
import com.trakingcontainer.services.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JWTTokenProvider jwtTokenProvider;

 /**
 * Endpoint for user login.
 * Point de terminaison pour la connexion de l'utilisateur.
 */    

    @PostMapping("/api/account/login")
    public ResponseEntity<AccountResponseDTO> login(@RequestBody AccountLoginDTO accountLoginDTO) {
        authenticate(accountLoginDTO.getEmail(), accountLoginDTO.getPassword());
        AccountDTO loginUser = this.accountService.findAccountByEmailIgnoreCase(accountLoginDTO.getEmail());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(AccountMapper.toAccountResponseDTO(loginUser), jwtHeader, HttpStatus.OK);
    }
 /**
 * Endpoint to retrieve list of accounts.
 * Point de terminaison pour récupérer la liste des comptes.
 */
    @GetMapping("/api/account/list")
    public ResponseEntity<List<AccountResponseDTO>> getAccounts() {
        return new ResponseEntity<>(this.accountService.getAccounts(), HttpStatus.OK);
    }

    // @GetMapping("/api/account/resetPassword/{email}")
    // public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email")
    // String email)
    // throws MessagingException, EmailNotFoundException,
    // UnsupportedEncodingException {
    // this.accountService.resetPassword(email);
    // return response(HttpStatus.OK, UserImplConstant.EMAIL_SENT + email);
    // }

    // @GetMapping("/api/account/changePassword/{restPasswordToken}/{newPassword}")
    // public ResponseEntity<HttpResponse> changePassword(
    // @PathVariable("restPasswordToken") String restPasswordToken,
    // @PathVariable("newPassword") String newPassword) throws
    // EmailNotFoundException, MessagingException {
    // this.accountService.changePassword(restPasswordToken, newPassword);
    // return response(HttpStatus.OK,
    // UserImplConstant.PASSWORD_CHANGED_SUCCESSFULLY);
    // }

    // @PostMapping("/api/account/add")
    // public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody
    // AccountResponseDTO accountResponseDTO)
    // throws UserNotFoundException, EmailExistException, MessagingException {
    // return new
    // ResponseEntity<>(this.accountService.createAccount(accountResponseDTO),
    // HttpStatus.OK);
    // }

    // @PutMapping("/api/account/update")
    // public ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody
    // AccountResponseDTO accountResponseDTO)
    // throws UserNotFoundException, EmailExistException {
    // return new
    // ResponseEntity<>(this.accountService.updateAccount(accountResponseDTO),
    // HttpStatus.OK);
    // }
/**
* Endpoint to delete an account.
* Point de terminaison pour supprimer un compte.
*/
    @DeleteMapping("/api/account/delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        this.accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 /**
* Generates JWT token header.
* Génère l'en-tête du token JWT.
*/
    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }
/**
* Authenticates the user.
* Authentifie l'utilisateur.
*/    

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    // private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String
    // message) {
    // return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
    // httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
    // message), httpStatus);
    // }
}
