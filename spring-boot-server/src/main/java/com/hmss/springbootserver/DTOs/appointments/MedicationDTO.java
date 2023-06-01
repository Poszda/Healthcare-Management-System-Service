package com.hmss.springbootserver.DTOs.appointments;

public class MedicationDTO {

    private Long id;
    private String name;
    private Integer numberOfDays;
    private String dose;
    private Long diagnosticId;

    public MedicationDTO() {
    }

    public MedicationDTO(Long id, Long diagnosticId,String name, Integer numberOfDays, String dose) {
        this.diagnosticId = diagnosticId;
        this.id = id;
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.dose = dose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Long getDiagnosticId() {
        return diagnosticId;
    }

    public void setDiagnosticId(Long diagnosticId) {
        this.diagnosticId = diagnosticId;
    }
}
