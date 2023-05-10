package com.hmss.springbootserver.utils.models;

public class DoctorProgramSimplified {
    int programStart;
    int programEnd;

    public DoctorProgramSimplified(int programStart, int programEnd) {
        this.programStart = programStart;
        this.programEnd = programEnd;
    }

    public DoctorProgramSimplified() {
    }

    public int getProgramStart() {
        return programStart;
    }

    public void setProgramStart(int programStart) {
        this.programStart = programStart;
    }

    public int getProgramEnd() {
        return programEnd;
    }

    public void setProgramEnd(int programEnd) {
        this.programEnd = programEnd;
    }

    @Override
    public String toString() {
        return "DoctorProgramSimplified{" +
                "programStart=" + programStart +
                ", programEnd=" + programEnd +
                '}';
    }
}
