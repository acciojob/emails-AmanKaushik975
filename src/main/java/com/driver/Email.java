package com.driver;

import java.time.LocalTime;
import java.util.Date;

public class Email {
    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123"; // Default password
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        // Check if the old password matches the current password
        if (!oldPassword.equals(password)) {
            return false; // Password change failed, old password is incorrect
        }

        // Check if the new password meets the specified criteria
        if (!isPasswordValid(newPassword)) {
            return false; // Password change failed, new password does not meet criteria
        }

        // Update the password
        this.password = newPassword;
        return true; // Password change successful
    }

    private boolean isPasswordValid(String newPassword) {
        return newPassword.length() >= 8 &&
                newPassword.chars().anyMatch(Character::isUpperCase) &&
                newPassword.chars().anyMatch(Character::isLowerCase) &&
                newPassword.chars().anyMatch(Character::isDigit) &&
                newPassword.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }
}