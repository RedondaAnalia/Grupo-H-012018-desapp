package tests.Model;


import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.User;
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
}
