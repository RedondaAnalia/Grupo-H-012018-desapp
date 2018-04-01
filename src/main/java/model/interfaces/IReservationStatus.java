package model.interfaces;

import model.Rental;
import model.Reservation;

public interface IReservationStatus {
    void beReject(Reservation reservation);
    Rental beConfirm(Reservation reservation);
}
