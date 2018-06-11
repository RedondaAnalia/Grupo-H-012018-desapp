package service.dto;

import model.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="coord")
public class CoordDTO extends Entity {

    private double lat;
    private double lng;

    public CoordDTO(){}

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
