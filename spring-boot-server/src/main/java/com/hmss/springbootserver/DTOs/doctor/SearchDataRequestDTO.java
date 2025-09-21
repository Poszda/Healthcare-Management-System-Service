package com.hmss.springbootserver.DTOs.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SearchDataRequestDTO {
    @NotBlank(message = "Doctor name is required.")
    String name;
    @NotNull(message = "Speciality ID is required.")
    Long specialityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }
}
