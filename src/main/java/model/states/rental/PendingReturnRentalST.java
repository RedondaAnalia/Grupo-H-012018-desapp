package model.states.rental;


import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class PendingReturnRentalST extends IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void tenantUserConfirmated(Rental rental) {
        throw new InvalidStatusChangeException("Estado inválido");
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {
        rental.setState(new ReturnConfirmedByTheOwner());
    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {
        rental.setState(new ReturnConfirmedByTheTenant());
    }

    @Override
    public String toString(){
        return "PendingReturnRental";
    }
}
