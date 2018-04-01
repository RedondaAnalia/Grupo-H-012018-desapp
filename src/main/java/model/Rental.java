package model;


import model.interfaces.IRentalState;
import model.states.rental.PendingRentalST;

public class Rental {

    private Reservation reservation;
    private IRentalState state;

    public Rental(Reservation reservation){

        this.reservation = reservation;
        this.state = new PendingRentalST();

    }
}
