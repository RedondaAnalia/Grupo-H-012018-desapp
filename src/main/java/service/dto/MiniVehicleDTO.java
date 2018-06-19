package service.dto;

import model.Entity;

import java.util.List;

public class MiniVehicleDTO extends Entity{


    private String type;
    private String description;
    private List<String> photos;

    public MiniVehicleDTO(){}

    public MiniVehicleDTO(String type, String description, List<String> photos, int id){
        this.type = type;
        this.description = description;
        this.photos = photos;
        this.setId(id);
    }

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
