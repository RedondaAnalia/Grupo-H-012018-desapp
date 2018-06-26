package model.interfaces;


import model.Entity;
import model.Rental;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="statusRentalT")
public abstract class IRentalState extends Entity{

    private String status;

    public void ownerUserConfirmated(Rental rental){};
    public void tenantUserConfirmated(Rental rental){};

    public void ownerUserConfirmated(Rental rental, Integer score, String comment){};
    public void tenantUserConfirmated(Rental rental, Integer score, String comment){};

}
