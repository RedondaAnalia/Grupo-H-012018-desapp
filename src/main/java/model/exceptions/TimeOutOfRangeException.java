package model.exceptions;

public class TimeOutOfRangeException extends RuntimeException {

    public TimeOutOfRangeException() {
        super("El tiempo mínimo de alquiler es 1 hora y el máximo 5 días.");
    }
}
