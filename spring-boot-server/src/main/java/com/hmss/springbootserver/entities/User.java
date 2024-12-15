package com.hmss.springbootserver.entities;

import com.hmss.springbootserver.enums.UserType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // fetch = FetchType.LAZY nu ar trebui sa mearga
    // bytecode enhancement daca vreau sa fie si asa lazy pacientul lazyloaded
    //@LazyToOne(LazyToOneOption.NO_PROXY)
    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FileMetadata> fileMetadataList = new ArrayList<>();

    public void addPatient(Patient patient){
        this.patient = patient;
        this.patient.setUser(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<FileMetadata> getFileMetadataList() {
        return fileMetadataList;
    }

    public void setFileMetadataList(List<FileMetadata> fileMetadataList) {
        this.fileMetadataList = fileMetadataList;
    }
}
