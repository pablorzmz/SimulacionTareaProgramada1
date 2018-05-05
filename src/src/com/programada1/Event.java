package com.programada1;

public class Event {
    private boolean type;
    private double clockTime;

    public Event(boolean type, double clockTime) {
        this.type = type;
        this.clockTime = clockTime;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getClockTime() {
        return clockTime;
    }

    public void setClockTime(double clockTime) {
        this.clockTime = clockTime;
    }
}
