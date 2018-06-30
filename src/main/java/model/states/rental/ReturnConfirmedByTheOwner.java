package model.states.rental;


import model.AccountManager;
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
    }

    @Override
    public String toString(){
        return "ReturnConfirmedByTheOwner";
    }
}
