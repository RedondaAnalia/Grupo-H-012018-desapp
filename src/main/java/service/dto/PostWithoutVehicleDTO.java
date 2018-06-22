package service.dto;

import model.Entity;

import java.util.ArrayList;
import java.util.List;

public class PostWithoutVehicleDTO{


    private int vehicle;
    private String ownerUser;
    private CoordDTO pickUpCoord;
    private CoordDTO returnCoords;
    private String sinceDate;
    private String UntilDate;
    private double costPerDay;
    private int phone;
    private String location;

    public PostWithoutVehicleDTO(){}

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public String getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
    }

    public CoordDTO getPickUpCoord() {
        return pickUpCoord;
    }

    public void setPickUpCoord(CoordDTO pickUpCoord) {
        this.pickUpCoord = pickUpCoord;
    }

    public CoordDTO getReturnCoords() {
        return returnCoords;
    }

    public void setReturnCoords(CoordDTO returnCoords) {
        this.returnCoords = returnCoords;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
