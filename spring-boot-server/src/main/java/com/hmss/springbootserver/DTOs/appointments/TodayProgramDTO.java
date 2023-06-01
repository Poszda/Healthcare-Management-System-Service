package com.hmss.springbootserver.DTOs.appointments;

public class TodayProgramDTO {
    private int free;
    private int busy;

    public TodayProgramDTO() {
    }

    public TodayProgramDTO(int free, int busy) {
        this.free = free;
        this.busy = busy;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }
}
