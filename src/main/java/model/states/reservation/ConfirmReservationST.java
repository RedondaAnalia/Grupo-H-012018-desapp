package model.states.reservation;

import model.Reservation;
import model.exceptions.InvalidStatusChange;
import model.interfaces.IReservationStatus;


public class ConfirmReservationST implements IReservationStatus {
    @Override
    public void beReject(Reservation reservation) {
        throw new InvalidStatusChange
                ("No podes cambiar el estado de la reserva, de Confirmada a Rechazada");
    }

    @Override
    public void beConfirm(Reservation reservation) {
        throw new InvalidStatusChange
                ("No podes cambiar el estado de la reserva, de Confirmada a Confirmada");
    }
}
