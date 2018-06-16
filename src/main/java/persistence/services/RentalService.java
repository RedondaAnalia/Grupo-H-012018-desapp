package persistence.services;

import model.Rental;
import persistence.repositories.Initializable;
import persistence.repositories.PostRepository;
import persistence.repositories.RentalRepository;
import persistence.repositories.UserRepository;

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
/*
    public void reservation(int postId, String mail,
                            String reservationSinceDate, String reservationUntilDate) {
        User user = this.getUserRepository().filterUser(mail);
        Post post = this.getPostRepository().findById(postId);
        Reservation reservation = user.rent(post,reservationSinceDate, reservationUntilDate);
    }
    */
}
