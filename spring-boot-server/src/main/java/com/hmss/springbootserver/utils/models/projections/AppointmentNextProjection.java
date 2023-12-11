package com.hmss.springbootserver.utils.models.projections;

import java.time.LocalDateTime;

public interface AppointmentNextProjection {
    String getFirstName();
    String getLastName();
    LocalDateTime getDateTime();
    int getDuration();
    Long getOtherId();
    Long getId();
    String getProfileImage();
}
