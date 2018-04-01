package model;


import model.exceptions.CanceledRentalException;
import model.interfaces.IRentalState;
import model.states.rental.PendingRentalST;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Rental {

    private Reservation reservation;
    private IRentalState state;
    private LocalDateTime startRentalTime;
    private LocalDateTime timeAfterTheOwnerConfirmation;
    private LocalDateTime timeAfterTheTenantConfirmation;

    public Rental(Reservation reservation){

        this.reservation = reservation;
        this.state = new PendingRentalST();

    }

    public void setState(IRentalState newState){
        this.state=newState;
    }

    public void startTimeAfterTheOwnerConfirmation(){
        this.timeAfterTheOwnerConfirmation= LocalDateTime.now();
    }

    public void startTimeAfterTheTenantConfirmation(){
        this.timeAfterTheTenantConfirmation= LocalDateTime.now();
    }

    public void startRentalTime(){
        this.startRentalTime= LocalDateTime.now();
    }

    //pensar esto de nuevo estos checks.... no me cierra
    public void checkTheTimeAfterTheTenantConfirmation(){

        long tenantConfirmation = this.timeAfterTheTenantConfirmation.
                until(LocalDateTime.now(), ChronoUnit.MINUTES);

        //da por rechazado el alquiler, por ahora lanzó excepción
        // dsp hay que definir que acciones tomar
        if(tenantConfirmation>30) {
            throw new CanceledRentalException();
        }
    }

    public void checkTheTimeAfterTheOwnerConfirmation(){

        long ownerConfirmation = this.timeAfterTheOwnerConfirmation.
                until(LocalDateTime.now(), ChronoUnit.MINUTES);

        if(ownerConfirmation>30){
            this.startRentalTime();
        }
    }
}
