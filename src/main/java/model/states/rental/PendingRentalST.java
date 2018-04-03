package model.states.rental;


import model.Rental;
import model.interfaces.IRentalState;

public class PendingRentalST implements IRentalState {

    public void ownerUserConfirmated(Rental rental) {
        rental.setState(new ConfirmedByTheOwnerST());
        rental.startTimeAfterTheOwnerConfirmation();
    }

    public void tenantUserConfirmated(Rental rental) {
        rental.setState(new ConfirmedByTheTenantST());
        rental.startTimeAfterTheTenantConfirmation();
    }

    public void ownerUserConfirmated(Rental rental, Integer score, String comment) {

    }

    public void tenantUserConfirmated(Rental rental, Integer score, String comment) {

    }
}
