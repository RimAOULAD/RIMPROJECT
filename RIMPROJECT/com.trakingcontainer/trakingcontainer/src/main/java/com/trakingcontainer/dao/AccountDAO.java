

/**
 * This interface provides access to the database for account-related operations.
 * Cette interface fournit un accès à la base de données pour les opérations liées aux comptes.
 */
package com.trakingcontainer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trakingcontainer.entities.Account;

public interface AccountDAO extends JpaRepository<Account, Long> {
 /**
* Finds an account by email (case insensitive).
 * Recherche un compte par email (insensible à la casse).
 */
    Optional<Account> findAccountByEmailIgnoreCase(String email);

/**
* Finds an account by reset password token.
* Recherche un compte par jeton de réinitialisation de mot de passe.
*/
    Optional<Account> findAccountByResetPasswordToken(String resetPasswordToken);

/**
* Finds accounts with emailSent flag set to false.
* Recherche les comptes avec le drapeau emailSent défini sur false.
*/
    List<Account> findAccountByEmailSentFalse();
}
