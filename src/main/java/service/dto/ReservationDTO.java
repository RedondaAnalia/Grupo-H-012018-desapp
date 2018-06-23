package service.dto;

import model.Entity;

public class ReservationDTO extends Entity{

    private String tenantUser;
    private int post;
    private String reservationSinceDate;
    private String reservationUntilDate;
    private String statusReservation;

    public ReservationDTO(){}

    public ReservationDTO(String tenant, int post,
                          String sinceDate, String untilDate, String status, int id){
        this.tenantUser=tenant;
        this.post=post;
        this.reservationSinceDate=sinceDate;
        this.reservationUntilDate=untilDate;
        this.statusReservation=status;
        this.setId(id);
    }

    public String getTenantUser() {
        return tenantUser;
    }

    public void setTenantUser(String tenantUser) {
        this.tenantUser = tenantUser;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getReservationSinceDate() {
        return reservationSinceDate;
    }

    public void setReservationSinceDate(String reservationSinceDate) {
        this.reservationSinceDate = reservationSinceDate;
    }

    public String getReservationUntilDate() {
        return reservationUntilDate;
    }

    public void setReservationUntilDate(String reservationUntilDate) {
        this.reservationUntilDate = reservationUntilDate;
    }

    public String isStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(String statusReservation) {
        this.statusReservation = statusReservation;
    }
}