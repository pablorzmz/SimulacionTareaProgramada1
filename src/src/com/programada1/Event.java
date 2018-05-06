package com.programada1;

public class Event  {
    //  1 to arrive and 0 to leave
    private int type;
    private double clockTime;
    int client;

    public Event()
    {

    }

    public Event(int type, double clockTime, int client) {
        this.type = type;
        this.clockTime = clockTime;
        this.client = client;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public double getClockTime() {
        return clockTime;
    }

    public void setClockTime(double clockTime) {
        this.clockTime = clockTime;
    }
}
