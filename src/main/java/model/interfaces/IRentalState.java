package model.interfaces;


import model.Entity;
import model.Rental;

public abstract class IRentalState extends Entity{

    public void ownerUserConfirmated(Rental rental){};
    public void tenantUserConfirmated(Rental rental){};

    public void ownerUserConfirmated(Rental rental, Integer score, String comment){};
    public void tenantUserConfirmated(Rental rental, Integer score, String comment){};

}
