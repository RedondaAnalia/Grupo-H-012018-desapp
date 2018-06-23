package persistence.services;

import model.Post;
import model.Rental;
import model.Reservation;
import model.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.Initializable;
import persistence.repositories.PostRepository;
import persistence.repositories.ReservationRepository;
import persistence.repositories.UserRepository;

import java.time.LocalDateTime;

public class ReservationService extends GenericService<Reservation> implements Initializable {

    private ReservationRepository reservationRepository;

    private UserRepository userRepository;

    private PostRepository postRepository;

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
        return r.beConfirm();
    }

    @Transactional
    public void rejectReservation(int idReserv) {
        Reservation r = this.getReservationRepository().findById(idReserv);
        r.beReject();
    }
}
