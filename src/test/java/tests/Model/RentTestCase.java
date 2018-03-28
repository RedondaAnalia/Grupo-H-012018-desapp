package tests.Model;


import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.Reservation;
import model.User;
import model.exceptions.NoCreditException;
import model.exceptions.UserBlockedException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class RentTestCase {

    @Test
    public void shouldCreateAReservation(){
        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Assert.assertNotNull(tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L)));
    }

    @Test(expected=UserBlockedException.class)
    public void shouldNotRentIfIsAUserDisabled(){

        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().buildUserDisabled();

        tenantUser.rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));
    }

    @Test
    public void shouldCreateARental(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        reservation.beConfirm();
    }
}
