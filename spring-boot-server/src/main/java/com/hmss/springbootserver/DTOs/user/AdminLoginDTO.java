package com.hmss.springbootserver.DTOs.user;

import com.hmss.springbootserver.DTOs.admin.AdminWithHospitalDTO;
import com.hmss.springbootserver.enums.UserType;

public class AdminLoginDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
    private AdminWithHospitalDTO admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public AdminWithHospitalDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminWithHospitalDTO admin) {
        this.admin = admin;
    }
}
