package com.hmss.springbootserver.DTOs.speciality;

import com.hmss.springbootserver.DTOs.procedure.ProcedureDTO;
import com.hmss.springbootserver.entities.Procedure;
import jakarta.persistence.OneToMany;

import java.util.List;

public class SpecialityWithProceduresDTO {

    private Long id;
    private String name;
    private List<ProcedureDTO> procedures;

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

    public List<ProcedureDTO> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureDTO> procedures) {
        this.procedures = procedures;
    }
}
