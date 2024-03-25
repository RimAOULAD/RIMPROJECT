package com.trakingcontainer.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trakingcontainer.constant.UserImplConstant;
import com.trakingcontainer.dao.AccountDAO;
import com.trakingcontainer.dto.AccountDTO;
import com.trakingcontainer.dto.AccountResponseDTO;
import com.trakingcontainer.entities.Account;
import com.trakingcontainer.exception.ItemNotFoundException;
import com.trakingcontainer.mappers.AccountMapper;
import com.trakingcontainer.security.UserPrincipal;
import com.trakingcontainer.services.AccountService;
import com.trakingcontainer.utils.HelperService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService, UserDetailsService {
 // Durée d'expiration du token JWT
 // Expiration time for JWT token
    private static final long TOKEN_EXPIRATION_TIME = 4 * 60 * 60 * 1000; // 4 hours
// DAO pour les opérations sur les comptes
// DAO for account operations

    private final AccountDAO accountDAO;
// Service utilitaire
 // Utility service
    private final HelperService helperService;

// Méthode pour obtenir la liste des comptes
// Method to get the list of accounts

    @Override
    public List<AccountResponseDTO> getAccounts() {
        return this.accountDAO.findAll().stream()
                .map(AccountMapper::toDTO).map(AccountMapper::toAccountResponseDTO).collect(Collectors.toList());
    }
// Méthode pour trouver un compte par email
// Method to find an account by email

    @Override
    public AccountDTO findAccountByEmailIgnoreCase(String email) {
        Optional<Account> account = this.accountDAO.findAccountByEmailIgnoreCase(email);
        if (!account.isPresent())
            throw new ItemNotFoundException("Account not found by email : " + email);
        return AccountMapper.toDTO(account.get());
    }
 // Méthode pour charger un utilisateur par nom d'utilisateur (utilisé pour l'authentification)
// Method to load a user by username (used for authentication)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = this.accountDAO.findAccountByEmailIgnoreCase(username);
        if (!account.isPresent())
            throw new UsernameNotFoundException(UserImplConstant.NO_USER_FOUND_BY_USERNAME + username);
        return new UserPrincipal(AccountMapper.toDTO(account.get()));

    }

    // @Override
    // public void resetPassword(String email)
    // throws EmailNotFoundException, MessagingException,
    // UnsupportedEncodingException {
    // Optional<Account> account = this.accountDAO
    // .findAccountByEmailIgnoreCase(email);
    // if (!account.isPresent())
    // throw new EmailNotFoundException(UserImplConstant.NO_USER_FOUND_BY_EMAIL +
    // email);
    // String resetPasswordToken = UUID.randomUUID().toString();
    // account.get().setResetPasswordToken(resetPasswordToken);
    // account.get().setResetPasswordTokenDuration(new Date());
    // this.accountDAO.save(account.get());
    // this.sendGridService.sendEmail(account.get().getEmail(),
    // this.helperService.getResetPasswordHtmlMsg(resetPasswordToken),
    // "Reset Password");

    // }

    // @Override
    // public void changePassword(String restPasswordToken, String newPassword)
    // throws EmailNotFoundException, MessagingException {
    // Optional<Account> account = this.accountDAO
    // .findAccountByResetPasswordToken(ConverterHelper.decoder(restPasswordToken));
    // if (!account.isPresent())
    // throw new EmailNotFoundException(UserImplConstant.TOKEN_SENT_NO_VALID);
    // if (isCodeExpired(account.get().getResetPasswordTokenDuration()))
    // throw new ItemNotFoundException("Token expired");
    // account.get().setPassword(this.helperService.encodePassword(newPassword));
    // account.get().setActive(true);
    // account.get().setNotLocked(true);
    // account.get().setResetPasswordToken(null);
    // account.get().setResetPasswordTokenDuration(null);
    // this.accountDAO.save(account.get());
    // this.sendGridService.sendEmail(account.get().getEmail(),
    // this.helperService.getChangePasswordHtmlMsg(),
    // "Password Changed");
    // }
 // Méthode privée pour vérifier si un code de réinitialisation de mot de passe a expiré
// Private method to check if a password reset code has expired
    private boolean isCodeExpired(Date durationParam) {
        if (durationParam == null)
            return false;
        long currentTime = System.currentTimeMillis();
        long duration = durationParam.getTime();
        return currentTime > duration + TOKEN_EXPIRATION_TIME;
    }

    // @Override
    // public AccountResponseDTO createAccount(AccountResponseDTO
    // accountResponseDTO)
    // throws UserNotFoundException, EmailExistException, MessagingException {
    // this.helperService.validateNewUsername(StringUtils.EMPTY,
    // accountResponseDTO.getEmail());
    // Account account = AccountMapper.toEntity(accountResponseDTO);
    // String resetPasswordToken = UUID.randomUUID().toString();
    // account.setResetPasswordToken(resetPasswordToken);
    // account.setResetPasswordTokenDuration(new Date());
    // account.setActive(false);
    // account.setNotLocked(false);
    // Role role = accountResponseDTO.getRole();
    // account.setRole(role);
    // account.setAuthorities(role.getAuthorities());
    // AccountDTO accountDTO = AccountMapper.toDTO(this.accountDAO.save(account));
    // this.sendGridService.sendEmail(accountDTO.getEmail(),
    // this.helperService.getResetPasswordHtmlMsg(resetPasswordToken),
    // "Reset Password");
    // return AccountMapper.toAccountResponseDTO(accountDTO);

    // }

    // @Override
    // public AccountResponseDTO updateAccount(AccountResponseDTO
    // accountResponseDTO)
    // throws UserNotFoundException, EmailExistException {
    // Optional<Account> account =
    // this.accountDAO.findById(accountResponseDTO.getAccountId());
    // if (!account.isPresent())
    // throw new ItemNotFoundException("Account not found ");
    // this.helperService.validateNewUsername(account.get().getEmail(),
    // accountResponseDTO.getEmail());
    // BeanUtils.copyProperties(accountResponseDTO, account.get());
    // Role role = accountResponseDTO.getRole();
    // account.get().setRole(role);
    // account.get().setAuthorities(role.getAuthorities());
    // AccountDTO accountDTO =
    // AccountMapper.toDTO(this.accountDAO.save(account.get()));
    // return AccountMapper.toAccountResponseDTO(accountDTO);
    // }
 // Méthode pour supprimer un compte par son identifiant
  // Method to delete an account by its ID
    @Override
    public void deleteAccount(Long accountId) {
        Optional<Account> account = this.accountDAO.findById(accountId);
        if (!account.isPresent())
            throw new ItemNotFoundException("Account not found  ");
        this.accountDAO.deleteById(accountId);
    }
}
