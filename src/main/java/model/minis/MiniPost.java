package model.minis;

import model.Entity;
import java.time.LocalDateTime;

public class MiniPost extends Entity {

    private MiniVehicle vehicle;
    private LocalDateTime sinceDate;
    private LocalDateTime UntilDate;
    private double costPerDay;

    public MiniPost(){
        super();
    }

    public MiniPost(MiniVehicle vehicle, LocalDateTime sinceDate, LocalDateTime untilDate, double cost) {
        this.vehicle = vehicle;
        this.sinceDate = sinceDate;
        this.UntilDate = untilDate;
        this.costPerDay = cost;
    }

    public MiniVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(MiniVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(LocalDateTime sinceDate) {
        this.sinceDate = sinceDate;
    }

    public LocalDateTime getUntilDate() {
        return UntilDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        UntilDate = untilDate;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

}
