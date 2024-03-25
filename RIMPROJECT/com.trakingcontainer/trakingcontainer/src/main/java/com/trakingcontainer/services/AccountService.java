package com.trakingcontainer.services;

import java.util.List;

import com.trakingcontainer.dto.AccountDTO;
import com.trakingcontainer.dto.AccountResponseDTO;
// Interface defining methods of the service related to user accounts
// Interface définissant les méthodes du service liées aux comptes d'utilisateurs
public interface AccountService {
 // Method to retrieve the list of user accounts
 // Méthode pour récupérer la liste des comptes utilisateurs
        List<AccountResponseDTO> getAccounts();
// Method to find a user account by email (case-insensitive)
// Méthode pour trouver un compte utilisateur par e-mail (en ignorant la casse)
        AccountDTO findAccountByEmailIgnoreCase(String email);
// The following methods are currently commented out, they appear to be either disabled or pending implementation.
// Les méthodes suivantes sont actuellement commentées, elles semblent avoir été désactivées ou en attente d'implémentation.
        // void resetPassword(String email)
        // throws EmailNotFoundException, MessagingException,
        // UnsupportedEncodingException;

        // void changePassword(String tokenRestPassword, String newPassword)
        // throws EmailNotFoundException, MessagingException;

        // AccountResponseDTO createAccount(AccountResponseDTO accountResponseDTO)
        // throws UserNotFoundException, EmailExistException, MessagingException;

        // AccountResponseDTO updateAccount(AccountResponseDTO accountResponseDTO)
        // throws UserNotFoundException, EmailExistException;
// Method to delete a user account using its ID
// Méthode pour supprimer un compte utilisateur en utilisant son ID
        void deleteAccount(Long accountId);

}
