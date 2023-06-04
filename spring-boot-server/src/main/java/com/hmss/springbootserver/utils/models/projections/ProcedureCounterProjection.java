package com.hmss.springbootserver.utils.models.projections;

public interface ProcedureCounterProjection {
    long getId();
    String getName();
    int getTotal();
}
