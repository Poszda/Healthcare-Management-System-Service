package com.hmss.springbootserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hmss.springbootserver.DTOs.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User { //parent
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('admin', 'doctor', 'patient')")
    private UserType type;
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) //nu merge cu orphanRemoval // nu se aplica la baza de date ce cretin
    private Patient patient;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) //nu merge cu orphanRemoval // nu se aplica la baza de date ce cretin
    private Admin admin;

    public User(Long id, String lastName, String firstName, String email, String password, UserType type, Patient patient, Admin admin) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.patient = patient;
        this.admin = admin;
    }

    public User() {

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
