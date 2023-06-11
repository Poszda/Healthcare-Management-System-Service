package com.hmss.springbootserver.DTOs.patient;

import java.util.ArrayList;
import java.util.List;

public class PatientProfileDTO {
    PatientWithUserDTO patient;
    List<String> diagnostics = new ArrayList<>();
    List<String> medicationsLastThreeMonths = new ArrayList<>();
    List<String> medicationsLastSixMonths = new ArrayList<>();

    public PatientProfileDTO() {
    }

    public PatientProfileDTO(PatientWithUserDTO patient, List<String> diagnostics, List<String> medicationsLastThreeMonths, List<String> medicationsLastSixMonths) {
        this.patient = patient;
        this.diagnostics = diagnostics;
        this.medicationsLastThreeMonths = medicationsLastThreeMonths;
        this.medicationsLastSixMonths = medicationsLastSixMonths;
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
