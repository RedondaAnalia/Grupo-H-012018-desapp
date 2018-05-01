package model.minis;

import model.Entity;
import model.enums.VehicleType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="miniVehicle")
public class MiniVehicle extends Entity {

    private VehicleType type;
    private String description;

    public MiniVehicle(){
        super();
    }

    public MiniVehicle(VehicleType type, String description) {
        this.description = description;
        this.type = type;
    }

    public String getdesc() {
        return this.description;
    }
}
