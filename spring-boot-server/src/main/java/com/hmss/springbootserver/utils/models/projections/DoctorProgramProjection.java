package com.hmss.springbootserver.utils.models.projections;

import java.time.LocalTime;

public interface DoctorProgramProjection {
    Long getId();
    LocalTime getProgramStart();
    LocalTime getProgramEnd();
}
