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

    public String descVehicle(){
        return this.vehicle.getdesc();
    }
}
