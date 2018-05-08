package Programada1;

/**
 *  This class corresponds to the clients that interact in the simulation
 */
public class Client {

    private int id;
    private double  systemTimeArrived;
    private double systemTimeLeave;
    private double queueTimeLeave;

    /**
     * Standard constructor.
     */
    public Client()
    {

    }

    /**
     * Constructor.
     * @param id indicates the client number.
     * @param systemTimeArrived indicates the time in which the client arrives at the system.
     * @param systemTimeLeave indicates the time the client goes from the system.
     * @param queueTimeLeave indicates the time the client goes from the queue.
     */
    public Client(int id, double systemTimeArrived, double systemTimeLeave, double queueTimeLeave) {
        this.id = id;
        this.systemTimeArrived = systemTimeArrived;
        this.systemTimeLeave = systemTimeLeave;
        this.queueTimeLeave = queueTimeLeave;
    }

    /**
     * Method to request the identification of client.
     * @return The identification of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the identification of the client
     * @param id The identification of the client to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to request the time at the client that arrives at the system.
     * @return The time at which the client arrives at the system
     */
    public double getSystemTimeArrived() {
        return systemTimeArrived;
    }

    /**
     * Method to set the time in which the client arrives in the system.
     * @param systemTimeArrived The time to set in which the client arrives in the system.
     */
    public void setSystemTimeArrived(double systemTimeArrived) {
        this.systemTimeArrived = systemTimeArrived;
    }

    /**
     * Method to request the time in the client that goes from the system.
     * @return The time at which the client goes from the system.
     */
    public double getSystemTimeLeave() {
        return systemTimeLeave;
    }

    /**
     * Method to set the time when the client leaves the system.
     * @param systemTimeLeave The time at which the client goes from the system.
     */
    public void setSystemTimeLeave(double systemTimeLeave) {
        this.systemTimeLeave = systemTimeLeave;
    }

    /**
     * Method to request the time in the client that goes from the queue.
     * @return The time at which the client goes from the queue.
     */
    public double getQueueTimeLeave() {
        return queueTimeLeave;
    }

    /**
     * Method to set the time when the client leaves the queue.
     * @param queueTimeLeave The time at which the client goes from the queue.
     */
    public void setQueueTimeLeave(double queueTimeLeave) {
        this.queueTimeLeave = queueTimeLeave;
    }
}
