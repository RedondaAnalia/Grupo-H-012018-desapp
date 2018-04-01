package model.states.rental;

import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class ConfirmedByTheTenantST implements IRentalState {

    public void confirmationTheOwnerUser(Rental rental) {
        rental.startRentalTime();
    }

    public void confirmationTheTenantUser(Rental rental) {
        throw new InvalidStatusChangeException("Ya contamos con la confirmación del inquilino");
    }
}
