package model.interfaces;

import model.Reservation;

public interface IReservationStatus {
    void beReject(Reservation reservation);
    void beConfirm(Reservation reservation);
}
