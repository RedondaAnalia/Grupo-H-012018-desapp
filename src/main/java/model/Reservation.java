package model;


import model.interfaces.IReservationStatus;
import model.state.reservation.PendingReservationST;

import java.time.LocalDateTime;

public class Reservation {

    private User tenantUser;
    private Post post;
    private LocalDateTime reservationSinceDate;
    private LocalDateTime reservationUntilDate;
    private IReservationStatus statusReservation;

    public Reservation(){};

    public Reservation(Post post, LocalDateTime reservationSinceDate,
                       LocalDateTime reservationUntilDate, User tenantUser){
        this.tenantUser=tenantUser;
        this.post=post;
        this.reservationSinceDate=reservationSinceDate;
        this.reservationUntilDate=reservationUntilDate;

        this.statusReservation = new PendingReservationST();

    }

    public void setStatus(IReservationStatus status){
        this.statusReservation=status;
    }

    public IReservationStatus getStatus(){
        return this.statusReservation;
    }

    public void beReject(){
        this.statusReservation.beReject(this);
    }

    public void beConfirm(){
        this.statusReservation.beConfirm(this);
    }

}
