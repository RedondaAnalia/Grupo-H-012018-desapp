package service.dto;

import model.Entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="post")
public class PostDTO extends Entity{

    private VehicleDTO vehicle;
    private String ownerUser;
    private CoordDTO pickUpCoord;
    private CoordDTO returnCoords;
    private String sinceDate;
    private String UntilDate;
    private double costPerDay;
    private int phone;
    private String location;
    private String postState;

    public PostDTO(){}

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
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

    public String getPostState() {
        return postState;
    }

    public void setPostState(String postState) {
        this.postState = postState;
    }
}
