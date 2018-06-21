package persistence.services;

import model.Post;
import model.Rental;
import model.Reservation;
import model.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.Initializable;
import persistence.repositories.PostRepository;
import persistence.repositories.RentalRepository;
import persistence.repositories.UserRepository;

import java.time.LocalDateTime;

public class RentalService  extends GenericService<Rental> implements Initializable {

    private RentalRepository rentalRepository;

    private UserRepository userRepository;

    private PostRepository postRepository;

    public RentalRepository getRentalRepository() {
        return rentalRepository;
    }

    public void setRentalRepository(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
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

    public void reservation(int postId,
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
    }

    @Transactional
    public Rental reservationById(int id) {
        return this.getRentalRepository().findById(id);
    }
}
