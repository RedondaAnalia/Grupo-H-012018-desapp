package service.dto;

import model.Entity;

public class RentalDTO  extends Entity {

    private String rentalState;
    private long beginRentalTime;
    private ReservationDTO reservation;
    private String ownerComment;
    private String tenantComment;

    public String getRentalState() {
        return rentalState;
    }

    public void setRentalState(String rentalState) {
        this.rentalState = rentalState;
    }

    public long getBeginRentalTime() {
        return beginRentalTime;
    }

    public void setBeginRentalTime(long beginRentalTime) {
        this.beginRentalTime = beginRentalTime;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public String getOwnerComment() {
        return ownerComment;
    }

    public void setOwnerComment(String ownerComment) {
        this.ownerComment = ownerComment;
    }

    public String getTenantComment() {
        return tenantComment;
    }

    public void setTenantComment(String tenantComment) {
        this.tenantComment = tenantComment;
    }
}
