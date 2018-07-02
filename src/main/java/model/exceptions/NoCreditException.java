package model.exceptions;


public class NoCreditException extends RuntimeException{

    public NoCreditException() {
        super("No tenes suficiente crédito para alquilar este vehículo");
    }
}
