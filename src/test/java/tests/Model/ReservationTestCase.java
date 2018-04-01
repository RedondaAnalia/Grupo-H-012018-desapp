package tests.Model;


import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.Reservation;
import model.User;
import model.exceptions.InvalidStatusChange;
import model.states.reservation.ConfirmReservationST;
import model.states.reservation.PendingReservationST;
import model.states.reservation.RejectedReservationST;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class ReservationTestCase {

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

    @Test
    public void theReserveShouldBeBelievedWithThePendingState(){

        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Reservation reservation = tenantUser.rent(post, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L));

        Assert.assertEquals(reservation.getStatus().getClass(), PendingReservationST.class);
    }

    @Test
    public void theReservationShouldBeConfirmed(){

        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Reservation reservation = tenantUser.rent(post, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L));

        reservation.beConfirm();

        Assert.assertEquals(reservation.getStatus().getClass(), ConfirmReservationST.class);

    }

    @Test
    public void theReservationShouldBeRejected(){

        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Reservation reservation = tenantUser.rent(post, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L));

        reservation.beReject();

        Assert.assertEquals(reservation.getStatus().getClass(), RejectedReservationST.class);

    }

    @Test(expected = InvalidStatusChange.class)
    public void itShouldNotBePossibleToModifyTheStateOfReserveOfTheConfirmedToRejected(){
        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Reservation reservation = tenantUser.rent(post, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L));

        reservation.beConfirm();

        reservation.beReject();
    }

    @Test(expected = InvalidStatusChange.class)
    public void itShouldNotBePossibleToModifyTheStateOfReserveOfTheConfirmedToConfirmed(){

        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Reservation reservation = tenantUser.rent(post, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L));

        reservation.beConfirm();

        reservation.beConfirm();

    }

    @Test(expected = InvalidStatusChange.class)
    public void itShouldNotBePossibleToModifyTheStateOfReserveOfTheRejectedToRejected(){
        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Reservation reservation = tenantUser.rent(post, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L));

        reservation.beReject();

        reservation.beReject();
    }

    @Test(expected = InvalidStatusChange.class)
    public void itShouldNotBePossibleToModifyTheStateOfReserveOfTheRejectedToConfirmed(){

        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().build();

        Reservation reservation = tenantUser.rent(post, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L));

        reservation.beReject();

        reservation.beConfirm();

    }



}
