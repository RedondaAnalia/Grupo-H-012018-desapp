package model.interfaces;

import model.Entity;
import model.Rental;
import model.Reservation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="statusReservationT")
public abstract class IReservationState extends Entity{
    private String status;
    public void beReject(Reservation reservation){}
    public Rental beConfirm(Reservation reservation){return null;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
