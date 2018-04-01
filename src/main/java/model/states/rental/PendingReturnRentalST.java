package model.states.rental;


import model.Rental;
import model.interfaces.IRentalState;

public class PendingReturnRentalST implements IRentalState {

    public void confirmationTheOwnerUser(Rental rental) {
        //puntuar al inquilino - user.processScore
        rental.setState(new ReturnConfirmedByTheOwner());
    }

    public void confirmationTheTenantUser(Rental rental) {
        //puntuar al dueño - user.processScore
        rental.setState(new ReturnConfirmedByTheTenant());
    }
}
