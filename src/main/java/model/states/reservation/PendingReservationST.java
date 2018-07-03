package model.states.reservation;


import model.Mail;
import model.Rental;
import model.Reservation;
import model.enums.StatesPost;
import model.interfaces.IReservationState;

public class PendingReservationST extends IReservationState {

    private String status="Pending";

    public void beReject(Reservation reservation) {
        reservation.setStatus(new RejectedReservationST());
        Mail.sendFromGMail(reservation.getTenantUser().getEmail(),
                "[Carpnd] - Rechazo de reserva",
                "El dueño del vehiculo ha rechazado tu reserva.\n" +
                        "Dueño del vehiculo: \n" + reservation.getOwnerUser().getName()+" "+ reservation.getOwnerUser().getSurname()+"\n"+
                        "Email: "+ reservation.getOwnerUser().getEmail()+"\n"+
                        "Vehiculo: "+ reservation.getPost().getVehicle().getDescription());
    }

    @Override
    public Rental beConfirm(Reservation reservation) {
        reservation.setStatus(new ConfirmReservationST());
        reservation.getPost().setPostState(StatesPost.RESERVED);
        Rental rental = new Rental(reservation);
        Mail.sendFromGMail(reservation.getTenantUser().getEmail(),
                "[Carpnd] - Confirmación de reserva",

                "El dueño del vehiculo ha aceptado tu reserva.\n" +
                        "Dueño del vehiculo: \n" + reservation.getOwnerUser().getName()+" "+ reservation.getOwnerUser().getSurname()+"\n"+
                        "Email: "+ reservation.getOwnerUser().getEmail()+"\n"+
                        "Vehiculo: "+ reservation.getPost().getVehicle().getDescription());
        return rental;
    }

    @Override
    public String toString(){
        return this.status;
    }
}
