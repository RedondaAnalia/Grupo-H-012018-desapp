package model.states.rental;


import model.AccountManager;
import model.Mail;
import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

import java.time.LocalDateTime;

public class PendingReturnRentalST extends IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void tenantUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {
        rental.getOwnerUser().processScore(score);
        LocalDateTime endRentalTime = rental.getReservation().getReservationUntilDate();
        AccountManager.processPayment(rental.rentCost(endRentalTime),
                rental.getTenantUser(), rental.getOwnerUser());
        rental.setOwnerComment(comment);
        rental.setState(new ReturnConfirmedByTheOwner());

        Mail.sendFromGMail(rental.getTenantUser().getEmail(),
                "[Carpnd] - Se ha confirmado la devolución del vehículo por parte del dueño",
                "El dueño del vehículo nos confirma que ya realizaste la devolución.\n" +
                        "Dueño: \n" + rental.getOwnerUser().getName()+" "+ rental.getOwnerUser().getSurname()+"\n"+
                        "Email: "+ rental.getOwnerUser().getEmail()+"\n"+
                        "Vehiculo: "+ rental.getReservation().getPost().getVehicle().getDescription()+
                        "\n\n"+"Comentario del dueño: "+ rental.getOwnerComment() +"\n"+
                        "\n\n"+"Por favor, no olvides confirmar la devolución y calificar al dueño.");
    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {
        rental.getTenantUser().processScore(score);
        LocalDateTime endRentalTime = rental.getReservation().getReservationUntilDate();
        AccountManager.processPayment(rental.rentCost(endRentalTime),
                rental.getTenantUser(), rental.getOwnerUser());
        rental.setTenantComment(comment);
        rental.setState(new ReturnConfirmedByTheTenant());

        Mail.sendFromGMail(rental.getOwnerUser().getEmail(),
                "[Carpnd] - Se ha confirmado la devolución del vehículo por parte del cliente",
                "El cliente nos confirma que ya realizó la devolución del vehículo.\n" +
                        "Cliente: \n" + rental.getTenantUser().getName()+" "+ rental.getTenantUser().getSurname()+"\n"+
                        "Email: "+ rental.getTenantUser().getEmail()+"\n"+
                        "Vehiculo: "+ rental.getReservation().getPost().getVehicle().getDescription()+
                        "\n\n"+"Comentario del cliente: "+ rental.getTenantComment() +"\n"+
                        "\n\n"+"Por favor, no olvides confirmar la devolución y calificar a al persona que te alquilo el vehículo.");
    }

    @Override
    public String toString(){
        return "PendingReturnRental";
    }
}
