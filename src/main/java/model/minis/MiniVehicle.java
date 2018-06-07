package model.minis;

import model.Entity;
import model.enums.VehicleType;
import java.util.List;

public class MiniVehicle extends Entity {

    private VehicleType type;
    private String description;
    private List<String> photos;

    public MiniVehicle(){
        super();
    }

    public MiniVehicle(VehicleType type, String description, List<String> photos) {
        this.description = description;
        this.type = type;
        this.photos = photos;
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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
