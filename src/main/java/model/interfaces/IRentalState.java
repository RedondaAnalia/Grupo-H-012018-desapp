package model.interfaces;


import model.Rental;

public interface IRentalState {

    void confirmationTheOwnerUser(Rental rental);
    void confirmationTheTenantUser(Rental rental);

}
