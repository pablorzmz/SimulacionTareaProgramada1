package Programada1;

/**
 * This class corresponds to the events of the simulation
 */
public class Event  {
    //  1 to arrive and 0 to leave
    private int type;
    private double clockTime;
    int client;

    /**
     * Standard constructor
     */
    public Event()
    {

    }

    /**
     * Constructor
     * @param type indicates the type of event that is created
     * @param clockTime indicates the clock time in which the event takes place
     * @param client indicates the customer number to which the event is associated
     */
    public Event(int type, double clockTime, int client) {
        this.type = type;
        this.clockTime = clockTime;
        this.client = client;
    }

    /**
     * Method to request the type of event.
     * @return The event type
     */
    public int getType() {
        return type;
    }

    /**
     * Method to set the type of the event
     * @param type The type to set in the event
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Method to request the client of event
     * @return The event client
     */
    public int getClient() {
        return client;
    }

    /**
     * Method to set the client of the event
     * @param client The client to set in the event
     */
    public void setClient(int client) {
        this.client = client;
    }

    /**
     * Method to request the clock time of event
     * @return The event clock time.
     */
    public double getClockTime() {
        return clockTime;
    }

    /**
     * Method to set the time clock of the event
     * @param clockTime The time of the clock to set in the event
     */
    public void setClockTime(double clockTime) {
        this.clockTime = clockTime;
    }
}
