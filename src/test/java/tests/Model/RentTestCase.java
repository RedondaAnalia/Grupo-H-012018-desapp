package tests.Model;


import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.Rental;
import model.Reservation;
import model.User;
import model.exceptions.CanceledRentalException;
import model.exceptions.InvalidStatusChangeException;
import model.exceptions.UserBlockedException;
import model.states.rental.PendingRentalST;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class RentTestCase {

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

        Assert.assertNotNull(reservation.beConfirm());
    }

    @Test
    public void theRentalShouldStartInPendingState(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        Assert.assertEquals(rental.getState().getClass(), PendingRentalST.class);

    }

    @Test
    public void shouldStartTheRentTimeAfterBothConfirmations(){
        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        rental.ownerConfirmation();
        rental.tenantConfirmation();

        Assert.assertTrue(rental.getRentalTime()> 0);
    }

    /*
    @Test
    public void shouldStartTheRentalAfter30MinutesAfterTheConfirmationOfTheOwner(){

    }

    @Test(expected = CanceledRentalException.class)
    public void shouldCancelTheRentalAfter30MinutesAfterTheConfirmationOfTheTenant(){

    }
    */

    @Test(expected = InvalidStatusChangeException.class)
    public void theRentalShouldNotGoFromConfirmedStateByTheOwnerToConfirmedByTheOwner(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        rental.ownerConfirmation();
        rental.ownerConfirmation();

    }

    @Test(expected = InvalidStatusChangeException.class)
    public void theRentalShouldNotGoFromConfirmedStateByTheTenantToConfirmedByTheTenant(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        rental.tenantConfirmation();
        rental.tenantConfirmation();

    }


}
