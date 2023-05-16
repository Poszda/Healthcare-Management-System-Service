package com.hmss.springbootserver.DTOs.admin;

import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;

public class AdminWithHospitalDTO {
    private String userId;
    private HospitalDTO hospital;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HospitalDTO getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDTO hospital) {
        this.hospital = hospital;
    }
}
