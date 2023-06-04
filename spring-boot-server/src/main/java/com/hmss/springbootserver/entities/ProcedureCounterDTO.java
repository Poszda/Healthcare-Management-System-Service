package com.hmss.springbootserver.entities;

import com.hmss.springbootserver.utils.models.projections.ProcedureCounterProjection;

public class ProcedureCounterDTO{
    private long id;
    private String name;
    private int total;

    public ProcedureCounterDTO(long id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    public ProcedureCounterDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
