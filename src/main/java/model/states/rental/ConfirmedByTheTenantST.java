package model.states.rental;

import model.Mail;
import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class ConfirmedByTheTenantST extends IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        rental.startRentalTime();
        rental.setState(new PendingReturnRentalST());

        Mail.sendFromGMail(rental.getOwnerUser().getEmail(),
                "[Carpnd] - Se ha confirmado tu alquiler :)",
                "La persona interesada en tu vehiculo ha confirmado el alquiler.\n" +
                        "Cliente: \n" + rental.getTenantUser().getName()+" "+ rental.getTenantUser().getSurname()+"\n"+
                        "Email: "+ rental.getTenantUser().getEmail()+"\n"+
                        "Vehiculo: "+ rental.getReservation().getPost().getVehicle().getDescription());
    }

    public void tenantUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Ya contamos con la confirmación del inquilino");
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    @Override
    public String toString(){
        return "ConfirmedByTheTenant";
    }
}
