package vehiman.amoebiq.android.com.vehiman.model;

import java.util.Date;

/**
 * Created by skadavath on 4/18/18.
 */

public class ServiceDetails {

    private long id;
    private String serviceType;
    private boolean done;
    private Date serviceDate;
    private Date nextServiceDate;
    private long currentOdo;
    private long nextServiceOdo;
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Date getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(Date nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public long getCurrentOdo() {
        return currentOdo;
    }

    public void setCurrentOdo(long currentOdo) {
        this.currentOdo = currentOdo;
    }

    public long getNextServiceOdo() {
        return nextServiceOdo;
    }

    public void setNextServiceOdo(long nextServiceOdo) {
        this.nextServiceOdo = nextServiceOdo;
    }
}
