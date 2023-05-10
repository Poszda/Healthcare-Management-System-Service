package com.hmss.springbootserver.utils.models;

public class FreeTimeInterval {
    int intervalStart;
    int intervalEnd;

    public FreeTimeInterval() {
    }

    public FreeTimeInterval(int intervalStart, int intervalEnd) {
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
    }

    public int getIntervalStart() {
        return intervalStart;
    }

    public void setIntervalStart(int intervalStart) {
        this.intervalStart = intervalStart;
    }

    public int getIntervalEnd() {
        return intervalEnd;
    }

    public void setIntervalEnd(int intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    @Override
    public String toString() {
        return "FreeTimeInterval{" +
                "intervalStart=" + intervalStart +
                ", intervalEnd=" + intervalEnd +
                '}';
    }
}
