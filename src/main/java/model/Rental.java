package model;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Rental {

    private Vehicle vehicle;
    private User ownerUser;
    private User tenantUser;
    private Coord pickUpCoord;
    private ArrayList<Coord> returnCoords;


    public Rental(User tenantUser, Post post, LocalDateTime reservationSinceDate,
                  LocalDateTime reservationUntilDate){

        this.ownerUser = post.getUser();
        this.tenantUser = tenantUser;
        this.pickUpCoord = post.getPickUpCoord();
        this.returnCoords = post.getReturnCoords();
        this.vehicle = post.getVehicle();

    }
}
