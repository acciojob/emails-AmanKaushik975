package com.driver;

import java.time.LocalTime;
import java.util.Date;

public class Email {
    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        if (oldPassword.equals(password) && isPasswordValid(newPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please make sure the old password is correct and the new password meets the criteria.");
        }
    }

    private boolean isPasswordValid(String newPassword) {
        return newPassword.length() >= 8 &&
                newPassword.chars().anyMatch(Character::isUpperCase) &&
                newPassword.chars().anyMatch(Character::isLowerCase) &&
                newPassword.chars().anyMatch(Character::isDigit) &&
                newPassword.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }
}