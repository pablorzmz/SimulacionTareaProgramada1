package Programada1;

public class Client {

    private int id;
    private double  systemTimeArrived;
    private double systemTimeLeave;
    private double queueTimeLeave;

    public Client()
    {

    }

    public Client(int id, double systemTimeArrived, double systemTimeLeave, double queueTimeLeave) {
        this.id = id;
        this.systemTimeArrived = systemTimeArrived;
        this.systemTimeLeave = systemTimeLeave;
        this.queueTimeLeave = queueTimeLeave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSystemTimeArrived() {
        return systemTimeArrived;
    }

    public void setSystemTimeArrived(double systemTimeArrived) {
        this.systemTimeArrived = systemTimeArrived;
    }

    public double getSystemTimeLeave() {
        return systemTimeLeave;
    }

    public void setSystemTimeLeave(double systemTimeLeave) {
        this.systemTimeLeave = systemTimeLeave;
    }

    public double getQueueTimeLeave() {
        return queueTimeLeave;
    }

    public void setQueueTimeLeave(double queueTimeLeave) {
        this.queueTimeLeave = queueTimeLeave;
    }
}
