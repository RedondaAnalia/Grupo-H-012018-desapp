package model.interfaces;

import model.Entity;
import model.Rental;
import model.Reservation;

public abstract class IReservationState extends Entity{
    public void beReject(Reservation reservation){}
    public Rental beConfirm(Reservation reservation){return null;}
}
