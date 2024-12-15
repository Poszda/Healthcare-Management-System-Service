package com.hmss.springbootserver.DTOs.patient;

import java.util.List;

public class PatientProfileDTO {
    PatientWithUserDTO patient;
    List<String> diagnostics;
    List<String> medicationsLastThreeMonths;
    List<String> medicationsLastSixMonths;
    String profileImage;

    public PatientProfileDTO(PatientWithUserDTO patient, List<String> diagnostics, List<String> medicationsLastThreeMonths, List<String> medicationsLastSixMonths, String profileImage) {
        this.patient = patient;
        this.diagnostics = diagnostics;
        this.medicationsLastThreeMonths = medicationsLastThreeMonths;
        this.medicationsLastSixMonths = medicationsLastSixMonths;
        this.profileImage = profileImage;
    }

    public PatientWithUserDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientWithUserDTO patient) {
        this.patient = patient;
    }

    public List<String> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<String> diagnostics) {
        this.diagnostics = diagnostics;
    }

    public List<String> getMedicationsLastThreeMonths() {
        return medicationsLastThreeMonths;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setMedicationsLastThreeMonths(List<String> medicationsLastThreeMonths) {
        this.medicationsLastThreeMonths = medicationsLastThreeMonths;
    }

    public List<String> getMedicationsLastSixMonths() {
        return medicationsLastSixMonths;
    }

    public void setMedicationsLastSixMonths(List<String> medicationsLastSixMonths) {
        this.medicationsLastSixMonths = medicationsLastSixMonths;
    }
}
