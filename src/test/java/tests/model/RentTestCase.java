package tests.model;


import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.Rental;
import model.Reservation;
import model.User;
import model.exceptions.InvalidStatusChangeException;
import model.exceptions.UserBlockedException;
import model.states.rental.PendingRentalST;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

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

        assertNotNull(reservation.beConfirm());
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

        assertEquals(rental.getState().getClass(), PendingRentalST.class);

    }

/*
    @Test
    public void shouldStartTheRentTimeAfterBothConfirmations() throws InterruptedException {
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

        //Thread.sleep(1000);

        Assert.assertTrue(rental.getRentalTime()> 0);
    }


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

    //TESTs PENDIENTES

    //luego de ambas confirmaciones comienza el tiempo del alquiler, el rental queda
    //en estado PendingReturnRentalST

    //pasaje de estado PendingReturnRentalST a ReturnCOnfirmedByTheOwner

    //pasaje de estado PendingReturnRentalST a ReturnCOnfirmedByTheTenant

    //pasaje de estado ReturnCOnfirmedByTheOwner a ReturnCOnfirmedByTheTenant

    //pasaje de estado ReturnCOnfirmedByTheTenant a ReturnCOnfirmedByTheOwner

    //pasaje de estado ReturnCOnfirmedByTheOwner a FinalizedRentalST

    //pasaje de estado ReturnCOnfirmedByTheTenant a FinalizedRentalST

    //test del review

    //test del accountManager


}
