package service.dto;

import model.Entity;

public class RentalDTO  extends Entity {

    private String rentalState;
    private String beginRentalTime;
    private int idReservation;

    public String getRentalState() {
        return rentalState;
    }

    public void setRentalState(String rentalState) {
        this.rentalState = rentalState;
    }

    public String getBeginRentalTime() {
        return beginRentalTime;
    }

    public void setBeginRentalTime(String beginRentalTime) {
        this.beginRentalTime = beginRentalTime;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
}
