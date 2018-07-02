package model;


import model.exceptions.InvalidReservationException;
import model.interfaces.IReservationState;
import model.states.post.ReservedPostST;
import model.states.reservation.PendingReservationST;

import java.time.LocalDateTime;

public class Reservation extends Entity{

    private User tenantUser;
    private Post post;
    private LocalDateTime reservationSinceDate;
    private LocalDateTime reservationUntilDate;
    private IReservationState statusReservation;

    public Reservation(){}

    public Reservation(Post post, LocalDateTime reservationSinceDate,
                       LocalDateTime reservationUntilDate, User tenantUser){

        this.tenantUser=tenantUser;
        this.post=post;
        this.post.setPostState(new ReservedPostST());
        this.reservationSinceDate=reservationSinceDate;
        this.reservationUntilDate=reservationUntilDate;

        this.statusReservation = new PendingReservationST();

        if(this.tenantUser.getEmail().equals(this.post.getOwnerUser().getEmail())){
            throw new InvalidReservationException();
        }

    }

    public void setStatus(IReservationState status){
        this.statusReservation=status;
    }

    public IReservationState getStatus(){
        return this.statusReservation;
    }

    public void beReject(){
        this.statusReservation.beReject(this);
    }

    public Rental beConfirm(){
        return this.statusReservation.beConfirm(this);
    }

    public User getTenantUser(){
        return this.tenantUser;
    }

    public User getOwnerUser(){
        return this.post.getOwnerUser();
    }

    public Post getPost() {
        return post;
    }

    public void setTenantUser(User tenantUser) {
        this.tenantUser = tenantUser;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getReservationSinceDate() {
        return reservationSinceDate;
    }

    public void setReservationSinceDate(LocalDateTime reservationSinceDate) {
        this.reservationSinceDate = reservationSinceDate;
    }

    public LocalDateTime getReservationUntilDate() {
        return reservationUntilDate;
    }

    public void setReservationUntilDate(LocalDateTime reservationUntilDate) {
        this.reservationUntilDate = reservationUntilDate;
    }

    public IReservationState getStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(IReservationState statusReservation) {
        this.statusReservation = statusReservation;
    }
}
