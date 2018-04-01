package model.states.reservation;

import model.Rental;
import model.Reservation;
import model.exceptions.InvalidStatusChange;
import model.interfaces.IReservationStatus;


public class RejectedReservationST implements IReservationStatus {

    @Override
    public void beReject(Reservation reservation) {
        throw new InvalidStatusChange
                ("No podes cambiar el estado de la reserva, de Rechazada a Rechazada");
    }

    @Override
    public Rental beConfirm(Reservation reservation) {
        throw new InvalidStatusChange
                ("No podes cambiar el estado de la reserva, de Rechazada a Confirmada");
    }
}
