package model.states.rental;


import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class PendingReturnRentalST implements IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        rental.setState(new ReturnConfirmedByTheOwner());
    }

    public void tenantUserConfirmated(Rental rental) {
        rental.setState(new ReturnConfirmedByTheTenant());
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {
        throw new InvalidStatusChangeException("Estado inválido");
    }
}