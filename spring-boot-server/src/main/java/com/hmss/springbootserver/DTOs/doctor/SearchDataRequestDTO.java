package com.hmss.springbootserver.DTOs.doctor;

public class SearchDataRequestDTO {
    String name;
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
