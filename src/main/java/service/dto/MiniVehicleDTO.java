package service.dto;

import model.Entity;

public class MiniVehicleDTO extends Entity{


    private String type;
    private String description;

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

}
