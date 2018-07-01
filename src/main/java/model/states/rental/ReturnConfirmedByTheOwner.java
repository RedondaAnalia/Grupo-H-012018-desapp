package model.states.rental;


import model.AccountManager;
import model.Mail;
import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

import java.time.LocalDateTime;

public class ReturnConfirmedByTheOwner extends IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void tenantUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Ya contamos con la confirmación del dueño");
    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {
        rental.getOwnerUser().processScore(score);
        LocalDateTime endRentalTime = LocalDateTime.now();
        AccountManager.processPayment(rental.rentCost(endRentalTime),
                rental.getTenantUser(), rental.getOwnerUser());
        rental.setTenantComment(comment);
        rental.setState(new FinalizedRentalST());

        Mail.sendFromGMail(rental.getOwnerUser().getEmail(),
                "[Carpnd] - Se ha confirmado la devolución del vehículo por parte del cliente",
                "El cliente nos confirma que ya realizó la devolución del vehículo.\n" +
                        "Cliente: \n" + rental.getTenantUser().getName()+" "+ rental.getTenantUser().getSurname()+"\n"+
                        "Email: "+ rental.getTenantUser().getEmail()+"\n"+
                        "Vehiculo: "+ rental.getReservation().getPost().getVehicle().getDescription()+
                        "\n\n"+"Comentario del clinte: "+ rental.getTenantComment() +"\n"+
                        "\n\n"+"GRACIAS POR ELEGIRNOS!!");
    }

    @Override
    public String toString(){
        return "ReturnConfirmedByTheOwner";
    }
}
