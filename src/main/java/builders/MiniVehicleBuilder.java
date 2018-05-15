package builders;

import model.enums.VehicleType;
import model.minis.MiniVehicle;

public class MiniVehicleBuilder {

    private VehicleType type= VehicleType.AUTO;
    private String description= "A default description, a little bit longer " +
            "because is not allowed a shorter description";

    public static MiniVehicleBuilder aVehicle(){
        return new MiniVehicleBuilder();
    }

    public MiniVehicle build(){
        return new MiniVehicle(type,description);
    }

    public MiniVehicleBuilder withType(VehicleType type){
        this.type=type;
        return this;
    }

    public MiniVehicleBuilder withDescription(String description){
        this.description=description;
        return this;
    }

}
