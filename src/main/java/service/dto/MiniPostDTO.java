package service.dto;

import java.time.LocalDateTime;

public class MiniPostDTO{

    private MiniVehicleDTO vehicle;
    private LocalDateTime sinceDate;
    private LocalDateTime UntilDate;

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
}
