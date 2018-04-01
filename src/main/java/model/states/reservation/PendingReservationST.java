package model.states.reservation;


import model.Rental;
import model.Reservation;
import model.interfaces.IReservationStatus;

public class PendingReservationST implements IReservationStatus {

    public void beReject(Reservation reservation) {
        reservation.setStatus(new RejectedReservationST());
    }

    @Override
    public void beConfirm(Reservation reservation) {
        reservation.setStatus(new ConfirmReservationST());
        new Rental(reservation);
    }

}