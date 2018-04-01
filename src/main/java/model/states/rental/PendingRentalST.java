package model.states.rental;


import model.Rental;
import model.interfaces.IRentalState;

public class PendingRentalST implements IRentalState {

    public void confirmationTheOwnerUser(Rental rental) {
        rental.setState(new ConfirmedByTheOwnerST());
        rental.startTimeAfterTheOwnerConfirmation();
    }

    public void confirmationTheTenantUser(Rental rental) {
        rental.setState(new ConfirmedByTheTenantST());
        rental.startTimeAfterTheTenantConfirmation();
    }
}
