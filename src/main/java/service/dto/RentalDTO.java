package service.dto;

import model.Entity;

public class RentalDTO  extends Entity {

    private String rentalState;
    private long beginRentalTime;
    private int idReservation;

    public String getRentalState() {
        return rentalState;
    }

    public void setRentalState(String rentalState) {
        this.rentalState = rentalState;
    }

    public long getBeginRentalTime() {
        return beginRentalTime;
    }

    public void setBeginRentalTime(long beginRentalTime) {
        this.beginRentalTime = beginRentalTime;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
}
