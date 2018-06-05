package service.dto;

import model.Entity;

import java.util.List;

public class MiniVehicleDTO extends Entity{


    private String type;
    private String description;
    private List<String> photos;

    public MiniVehicleDTO(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
