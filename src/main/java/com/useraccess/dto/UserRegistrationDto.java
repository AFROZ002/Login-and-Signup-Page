package com.useraccess.dto;
import lombok.Data;
@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private String confirmPassword;
}