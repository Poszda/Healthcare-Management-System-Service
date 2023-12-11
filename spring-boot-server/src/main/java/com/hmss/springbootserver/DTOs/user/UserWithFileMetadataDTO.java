package com.hmss.springbootserver.DTOs.user;

import com.hmss.springbootserver.DTOs.files.FileMetadataDTO;

import java.util.List;

public class UserWithFileMetadataDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private Long doctorId;
    private Long patientId;
    private Long adminId;
    private List<FileMetadataDTO> fileMetadataList;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public List<FileMetadataDTO> getFileMetadataList() {
        return fileMetadataList;
    }

    public void setFileMetadataList(List<FileMetadataDTO> fileMetadataList) {
        this.fileMetadataList = fileMetadataList;
    }
}
