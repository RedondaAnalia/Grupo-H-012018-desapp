package model.states.rental;


import model.Rental;
import model.interfaces.IRentalState;

public class PendingReturnRentalST implements IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        rental.setState(new ReturnConfirmedByTheOwner());
    }

    public void tenantUserConfirmated(Rental rental) {
        rental.setState(new ReturnConfirmedByTheTenant());
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {

    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {

    }
}
