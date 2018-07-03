package persistence.services;

import model.*;
import model.states.reservation.RejectedReservationST;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.*;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationService extends GenericService<Reservation> implements Initializable {

    private ReservationRepository reservationRepository;

    private UserRepository userRepository;

    private PostRepository postRepository;

    private RentalRepository rentalRepository;

    public ReservationRepository getReservationRepository() {
        return reservationRepository;
    }

    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public RentalRepository getRentalRepository() {
        return rentalRepository;
    }

    public void setRentalRepository(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void initialize() {

    }

    @Transactional
    public Reservation reservation(int postId,
                            String mail,
                            String reservationSinceDate,
                            String reservationUntilDate) {
        User user = this.getUserRepository().filterUser(mail);
        Post post = this.getPostRepository().findById(postId);

        LocalDateTime sinceDate =
                LocalDateTime.of(
                        Integer.valueOf(reservationSinceDate.substring(0,4)),
                        Integer.valueOf(reservationSinceDate.substring(5,7)),
                        Integer.valueOf(reservationSinceDate.substring(8,10)),
                        0,
                        0
                );

        LocalDateTime untilDate =
                LocalDateTime.of(
                        Integer.valueOf(reservationUntilDate.substring(0,4)),
                        Integer.valueOf(reservationUntilDate.substring(5,7)),
                        Integer.valueOf(reservationUntilDate.substring(8,10)),
                        0,
                        0
                );

        Reservation reservation = user.rent(post,sinceDate, untilDate);
        return this.getReservationRepository().merge(reservation);
    }

    @Transactional
    public Reservation reservationById(int id) {
        return this.getReservationRepository().findById(id);
    }

    @Transactional
    public Rental confirmedReservation(Integer id){
        Reservation r = this.getReservationRepository().findById(id);

        Rental rental = this.getRentalRepository().merge(r.beConfirm());

        List<Reservation> lr = this.getReservationRepository().cancelOthersReservation(r.getPost().getId());
        for(Reservation res: lr){
            res.setStatus(new RejectedReservationST());
            this.getReservationRepository().merge(res);
        }
        return rental;
    }

    @Transactional
    public Response rejectReservation(int idReserv) {
        Reservation r = this.getReservationRepository().findById(idReserv);
        r.beReject();
        return Response.ok().build();
    }

    @Transactional
    public List<Reservation> findAllOwnerPendingReservations(String mail) {
        return this.getReservationRepository().findByOwnerUser(mail);
    }

    @Transactional
    public List<Rental> findAllOwnerRentals(String mail) {
        return this.getRentalRepository().findByOwnerUser(mail);
    }

    @Transactional
    public List<Rental> findAllTenantRentals(String mail) {
        return this.getRentalRepository().findByTenantUser(mail);
    }

    @Transactional
    public List<Reservation> findAllTenantPendingReservations(String mail) {
        return this.getReservationRepository().findByTenantUser(mail);
    }

    @Transactional
    public void confirmedRentalByOwner(int idRental) {
        this.getRentalRepository().confirmedRentalByOwner(idRental);
    }

    @Transactional
    public void confirmedRentalByTenant(int idRental) {
        this.getRentalRepository().confirmedRentalByTenant(idRental);
    }

    @Transactional
    public void confirmedReturnByTenant(int idRental, Integer score, String comment) {
        this.getRentalRepository().confirmedReturnByTenant(idRental, score, comment);
    }

    @Transactional
    public void confirmedReturnByOwner(int idRental, Integer score, String comment) {
        this.getRentalRepository().confirmedReturnByOwner(idRental, score, comment);
    }

    @Transactional
    public List<Rental> historicalMovementsByUser(String mail) {
        return this.getRentalRepository().historicalMovementsByUser(mail);
    }
}
