package model.states.reservation;

import model.Rental;
import model.Reservation;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IReservationState;


public class ConfirmReservationST extends IReservationState {

    private String status="Confirmed";

    @Override
    public void beReject(Reservation reservation) {
        throw new InvalidStatusChangeException
                ("No podes cambiar el estado de la reserva, de Confirmada a Rechazada");
    }

    @Override
    public Rental beConfirm(Reservation reservation) {
        throw new InvalidStatusChangeException
                ("No podes cambiar el estado de la reserva, de Confirmada a Confirmada");
    }

    @Override
    public String toString(){
        return this.status;
    }
}
