package model.states.rental;


import model.Mail;
import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class PendingRentalST extends IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        rental.setState(new ConfirmedByTheOwnerST());
        Mail.sendFromGMail(rental.getTenantUser().getEmail(),
                "[Carpnd] - Se ha confirmado tu alquiler :)",
                "El dueño del vehiculo ha confirmado el alquiler.\n" +
                        "Dueño del vehiculo: \n" + rental.getOwnerUser().getName()+" "+ rental.getOwnerUser().getSurname()+"\n"+
                        "Email: "+ rental.getOwnerUser().getEmail()+"\n"+
                        "Vehiculo: "+ rental.getReservation().getPost().getVehicle().getDescription());
    }

    public void tenantUserConfirmated(Rental rental) {
        rental.setState(new ConfirmedByTheTenantST());
        Mail.sendFromGMail(rental.getOwnerUser().getEmail(),
                "[Carpnd] - Se ha confirmado tu alquiler :)",
                "La persona interesada en tu vehiculo ha confirmado el alquiler.\n" +
                        "Cliente: \n" + rental.getTenantUser().getName()+" "+ rental.getTenantUser().getSurname()+"\n"+
                        "Email: "+ rental.getTenantUser().getEmail()+"\n"+
                        "Vehiculo: "+ rental.getReservation().getPost().getVehicle().getDescription());
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    @Override
    public String toString(){
        return "PendingRental";
    }
}
