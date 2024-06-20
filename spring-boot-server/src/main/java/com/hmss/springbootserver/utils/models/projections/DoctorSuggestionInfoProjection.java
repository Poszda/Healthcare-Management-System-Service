package com.hmss.springbootserver.utils.models.projections;

public interface DoctorSuggestionInfoProjection {
    Long getId();
    String getFirstName();
    String getLastName();
    String getSpeciality();
    String getHospital();
    String getProfileImage();
}
