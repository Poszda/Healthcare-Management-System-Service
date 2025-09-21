package com.hmss.springbootserver.DTOs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//@Getter
//@Setter
public class LoginRequestDTO {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    String email;
    @NotBlank(message = "Password is required.")
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
