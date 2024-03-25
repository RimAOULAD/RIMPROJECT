package com.trakingcontainer.mappers;

import org.springframework.beans.BeanUtils;

import com.trakingcontainer.dto.AccountDTO;
import com.trakingcontainer.dto.AccountResponseDTO;
import com.trakingcontainer.entities.Account;
import com.trakingcontainer.enumeration.Role;

public class AccountMapper {
// Méthode pour mapper un objet Account vers un objet AccountDTO
    public static AccountDTO toDTO(Account account) {
        if (account == null)
            return null;
        AccountDTO accountDTO = new AccountDTO();
        
          // Copier les propriétés de Account vers AccountDTO
          BeanUtils.copyProperties(account, accountDTO);
          // Définir le rôle de l'AccountDTO
          account.setRole(account.getRole());
        return accountDTO;
    }
 // Méthode pour mapper un objet AccountDTO vers un objet AccountResponseDTO
    public static AccountResponseDTO toAccountResponseDTO(AccountDTO accountDTO) {
        if (accountDTO == null)
            return null;
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
       // Copier les propriétés de AccountDTO vers AccountResponseDTO
       BeanUtils.copyProperties(accountDTO, accountResponseDTO);
       // Définir le rôle de l'AccountResponseDTO
       accountResponseDTO.setRole(accountDTO.getRole());
        return accountResponseDTO;
    }
 // Méthode pour mapper un objet AccountDTO vers un objet Account
    public static Account toEntity(AccountDTO accountDTO) {
        if (accountDTO == null)
            return null;
        Account account = new Account();
// Copier les propriétés de AccountDTO vers Account
        BeanUtils.copyProperties(accountDTO, account);
        return account;
    }
// Méthode pour mapper un objet AccountResponseDTO vers un objet Account
    public static Account toEntity(AccountResponseDTO accountResponseDTO) {
        if (accountResponseDTO == null)
            return null;
        Account account = new Account();
// Copier les propriétés de AccountResponseDTO vers Account
        BeanUtils.copyProperties(accountResponseDTO, account);
        return account;
    }
// Méthode privée pour obtenir l'enum Role à partir de son nom
    private static Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }
}
