package model.states.rental;


import model.Rental;
import model.exceptions.InvalidStatusChangeException;
import model.interfaces.IRentalState;

public class ReturnConfirmedByTheTenant implements IRentalState {

    public void confirmationTheOwnerUser(Rental rental) {
        //fijar tiempo de alquier
        //calcular costo
        //ingresar comentarios al momento de puntuar a su contraparte
    }

    public void confirmationTheTenantUser(Rental rental) {
        throw new InvalidStatusChangeException("Ya contamos con la confirmación del inquilino");
    }
}
