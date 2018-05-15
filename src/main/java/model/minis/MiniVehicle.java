package model.minis;

import model.Entity;
import model.enums.VehicleType;
import javax.xml.bind.annotation.XmlRootElement;

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

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
