package builders;

import model.enums.VehicleType;
import model.minis.MiniVehicle;

import java.util.ArrayList;
import java.util.List;

public class MiniVehicleBuilder {

    private VehicleType type= VehicleType.AUTO;
    private String description= "A default description, a little bit longer " +
            "because is not allowed a shorter description";
    private List<String> photos = new ArrayList<String>();

    public static MiniVehicleBuilder aVehicle(){
        return new MiniVehicleBuilder();
    }

    public MiniVehicle build(){
        return new MiniVehicle(type,description,photos);
    }

    public MiniVehicleBuilder withType(VehicleType type){
        this.type=type;
        return this;
    }

    public MiniVehicleBuilder withDescription(String description){
        this.description=description;
        return this;
    }

    public MiniVehicleBuilder withPhoto(String photo){
        this.photos.add(photo);
        return this;
    }

}
