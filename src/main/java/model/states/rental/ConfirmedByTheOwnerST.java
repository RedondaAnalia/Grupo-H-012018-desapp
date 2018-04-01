package model.states.rental;


import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class ConfirmedByTheOwnerST implements IRentalState {

    public void confirmationTheOwnerUser(Rental rental) {
        throw new InvalidStatusChangeException("Ya contamos con la confirmación del dueño");
    }


    public void confirmationTheTenantUser(Rental rental) {
        rental.startRentalTime();
    }
}
