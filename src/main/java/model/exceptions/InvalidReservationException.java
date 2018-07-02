package model.exceptions;

public class InvalidReservationException extends RuntimeException{

    public InvalidReservationException(){
        super("No podes alquilar tu propio vehículo !!");
    }
}
