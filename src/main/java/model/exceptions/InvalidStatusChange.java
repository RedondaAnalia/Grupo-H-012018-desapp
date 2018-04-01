package model.exceptions;


public class InvalidStatusChange extends RuntimeException{

    public InvalidStatusChange(String message){
        super(message);
    }
}
