package model.states.rental;


import model.AccountManager;
import model.Mail;
import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class ReturnConfirmedByTheTenant extends IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void tenantUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {
        rental.getTenantUser().processScore(score);
        rental.setOwnerComment(comment);
        rental.setState(new FinalizedRentalST());

        Mail.sendFromGMail(rental.getTenantUser().getEmail(),
                "[Carpnd] - Se ha confirmado la devolución del vehículo por parte del dueño",
                "El dueño del vehículo nos confirma que ya realizaste la devolución.\n" +
                        "Dueño: \n" + rental.getOwnerUser().getName()+" "+ rental.getOwnerUser().getSurname()+"\n"+
                        "Email: "+ rental.getOwnerUser().getEmail()+"\n"+
                        "Vehiculo: "+ rental.getReservation().getPost().getVehicle().getDescription()+
                        "\n\n"+"Comentario del dueño: "+ rental.getOwnerComment() +"\n"+
                        "\n\n"+"GRACIAS POR ELEGIRNOS!!");
    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Ya contamos con la confirmación del inquilino");
    }

    @Override
    public String toString(){
        return "ReturnConfirmedByTheTenant";
    }
}
