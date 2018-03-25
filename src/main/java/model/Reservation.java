package model;


import java.time.LocalDateTime;

public class Reservation {

    private User tenantUser;
    private Post post;
    private LocalDateTime reservationSinceDate;
    private LocalDateTime reservationUntilDate;

    public Reservation(){};

    public Reservation(Post post, LocalDateTime reservationSinceDate,
                       LocalDateTime reservationUntilDate, User tenantUser){
        this.tenantUser=tenantUser;
        this.post=post;
        this.reservationSinceDate=reservationSinceDate;
        this.reservationUntilDate=reservationUntilDate;
    }
}
