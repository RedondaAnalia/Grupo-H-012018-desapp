package service.dto;

import model.Entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name="minipost")
public class MiniPostDTO extends Entity {

    private MiniVehicleDTO vehicle;
    private LocalDateTime sinceDate;
    private LocalDateTime UntilDate;
    private double costPerDay;

    public MiniPostDTO(){}

    public MiniVehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(MiniVehicleDTO vehicle) {
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
