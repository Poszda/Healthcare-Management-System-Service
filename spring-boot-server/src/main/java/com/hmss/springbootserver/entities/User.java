package com.hmss.springbootserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String username;
    private String email;
    private String password;
    private int type; // 1 - admin , 2 - doctor, 3 - patient
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) //nu merge cu orphanRemoval // nu se aplica la baza de date ce cretin
    private Patient patient;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) //nu merge cu orphanRemoval // nu se aplica la baza de date ce cretin
    private Admin admin;

    public User(Long id, String lastName, String firstName, String username, String email, String password, int type, Patient patient, Admin admin) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
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

    public void setUsername(String username) {
        this.username = username;
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

    public String getUsername() {
        return username;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
