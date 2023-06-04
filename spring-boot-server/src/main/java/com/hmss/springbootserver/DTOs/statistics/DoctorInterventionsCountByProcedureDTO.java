package com.hmss.springbootserver.DTOs.statistics;

import com.hmss.springbootserver.entities.ProcedureCounterDTO;

import java.util.List;

public class DoctorInterventionsCountByProcedureDTO {
    private String month;
    private List<ProcedureCounterDTO> procedures;

    public DoctorInterventionsCountByProcedureDTO(String month, List<ProcedureCounterDTO> procedureCounter) {
        this.month = month;
        this.procedures = procedureCounter;
    }

    public DoctorInterventionsCountByProcedureDTO() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<ProcedureCounterDTO> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureCounterDTO> procedures) {
        this.procedures = procedures;
    }
}
