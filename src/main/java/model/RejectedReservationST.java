package model;

import model.interfaces.IReservationStatus;


public class RejectedReservationST implements IReservationStatus {

    @Override
    public void beReject(Reservation reservation) {
        //veamos si lanza excepción
    }

    @Override
    public void beConfirm(Reservation reservation) {

    }
}
