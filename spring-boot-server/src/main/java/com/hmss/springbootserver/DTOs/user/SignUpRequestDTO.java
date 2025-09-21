package com.hmss.springbootserver.DTOs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class SignUpRequestDTO {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    String email;
    @NotBlank(message = "First name is required.")
    String firstName;
    @NotBlank(message = "Last name is required.")
    String lastName;
    @NotBlank(message = "Password is required.")
    @Size(min = 4, message = "Password must be at least 4 characters long.")
    String password;
    @Size(min = 4, message = "Password must be at least 4 characters long.")
    @NotBlank(message = "Password is required.")
    String rePassword;
    @NotNull(message = "Birth date is required.")
    LocalDate birthDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
