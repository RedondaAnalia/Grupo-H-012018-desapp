package service.dto;

import model.Entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

public class MiniPostDTO extends Entity {

    private MiniVehicleDTO vehicle;
    private String sinceDate;
    private String UntilDate;
    private double costPerDay;
    private String location;
    private CoordDTO pickUpCoord;

    public MiniPostDTO(){}

    public MiniVehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(MiniVehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public String getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(String sinceDate) {
        this.sinceDate = sinceDate;
    }

    public String getUntilDate() {
        return UntilDate;
    }

    public void setUntilDate(String untilDate) {
        UntilDate = untilDate;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CoordDTO getPickUpCoord() {
        return pickUpCoord;
    }

    public void setPickUpCoord(CoordDTO pickUpCoord) {
        this.pickUpCoord = pickUpCoord;
    }
}
