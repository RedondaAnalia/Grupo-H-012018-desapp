package model.minis;

import model.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name="miniPost")
public class MiniPost extends Entity {

    private MiniVehicle vehicle;
    private LocalDateTime sinceDate;
    private LocalDateTime UntilDate;

    public MiniPost(){
        super();
    }

    public MiniPost(MiniVehicle vehicle, LocalDateTime sinceDate, LocalDateTime untilDate) {
        this.vehicle = vehicle;
        this.sinceDate = sinceDate;
        this.UntilDate = untilDate;
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
}
