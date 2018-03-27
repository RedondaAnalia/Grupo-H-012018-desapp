package model;


import model.interfaces.IRentalState;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Rental {

    private Reservation reservation;
    private IRentalState state;

    public Rental(Reservation reservation){

        this.reservation = reservation;
        this.state = new PendingRentalST();

    }
}
