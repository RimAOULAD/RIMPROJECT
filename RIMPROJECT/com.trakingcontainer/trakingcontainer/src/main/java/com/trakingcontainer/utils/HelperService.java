package com.trakingcontainer.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trakingcontainer.constant.UserImplConstant;
import com.trakingcontainer.dao.AccountDAO;
import com.trakingcontainer.dto.AccountDTO;
import com.trakingcontainer.enumeration.Role;
import com.trakingcontainer.exception.EmailExistException;
import com.trakingcontainer.exception.HttpResponse;
import com.trakingcontainer.exception.UserNotFoundException;
import com.trakingcontainer.mappers.AccountMapper;

import lombok.RequiredArgsConstructor;
// Utility service for common operations
// Service class providing various utility methods
@Service
@RequiredArgsConstructor
// Validate new username for uniqueness
// Validate if a new username is available
public class HelperService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final AccountDAO accountDAO;

    public void validateNewUsername(String currentUsername, String newUsername)
            throws UserNotFoundException, EmailExistException {
        AccountDTO accountByNewUsername = AccountMapper
                .toDTO(this.accountDAO.findAccountByEmailIgnoreCase(newUsername).orElse(null));
        if (StringUtils.isNotBlank(currentUsername)) {
            AccountDTO accountByCurrentUsername = AccountMapper
                    .toDTO(this.accountDAO.findAccountByEmailIgnoreCase(currentUsername).orElse(null));
            if (accountByCurrentUsername == null) {
                throw new UserNotFoundException(UserImplConstant.NO_USER_FOUND_BY_USERNAME +
                        " " + currentUsername);
            }
            if (accountByNewUsername != null
                    &&
                    !accountByCurrentUsername.getAccountId().equals(accountByNewUsername.getAccountId())) {
                throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS + " " +
                        newUsername);
            }
        } else {
            if (accountByNewUsername != null) {
                throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS + " " +
                        newUsername);
            }
        }
    }
// Encode password using BCryptPasswordEncoder
// Validate if a new username is available
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
 // Get Role enum based on role name
 // Get the Role enum based on its name
    public Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }
// Generate HTTP response entity with specified status and message
// Create an HTTP response entity with the given status code and message
    public ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
// Get authenticated username from SecurityContextHolder
 // Get the username of the authenticated user
    public String getUsernameAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "";
        } else {
            return authentication.getName();
        }
    }
// Generate activation code
// Generate a random activation code
    public String generateActivationCode() {
        return RandomStringUtils.randomNumeric(6);
    }
// Check if the content type is supported for profile images
// Check if the content type is supported for a profile image
    public boolean isSupportedContentTypeProfile(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
// Check if the content type is supported for general file uploads
// Check if the content type is supported
    public boolean isSupportedContentType(String contentType) {
        return contentType.equals("application/pdf")
                || contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
 // Get HTML message for change password notification
// Get the HTML message for a password change
    public String getChangePasswordHtmlMsg() {
        StringBuilder msg = new StringBuilder();
        msg.append("Your Password Changed Successfully");
        return msg.toString();
    }
// Change date values based on specified parameters
// Modify date values based on the specified parameters
    public Date changeDateValues(Date initialReminderDate, int YEAR, int MONTH, int DAY, int HOUR, int WEEK_OF_MONTH) {
        Calendar c = Calendar.getInstance();
        c.setTime(initialReminderDate);
        if (YEAR > 0) {
            c.add(Calendar.YEAR, YEAR);
        }
        if (MONTH > 0) {
            c.add(Calendar.MONTH, MONTH);
        }
        if (DAY > 0) {
            c.add(Calendar.DATE, DAY);
        }
        if (HOUR > 0) {
            c.add(Calendar.HOUR, HOUR);
        }
        if (WEEK_OF_MONTH > 0) {
            c.add(Calendar.WEEK_OF_MONTH, WEEK_OF_MONTH);
        }

        // convert calendar to date
        Date reminderDate = c.getTime();

        return reminderDate;
    }

}
