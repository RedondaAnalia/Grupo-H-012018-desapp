package tests.model;


import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.Rental;
import model.Reservation;
import model.User;
import model.states.rental.PendingReturnRentalST;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class RentalConfirmationTestCase {

    @Test
    public void shouldStartTheRentalAfter30MinutesAfterTheConfirmationOfTheOwner(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental9 = reservation.beConfirm();
        //PendingRental

        rental9.ownerConfirmation();
        //ConfirmedByTheOwnerST

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //assertEquals(rental9.getState().getClass(), PendingReturnRentalST.class);

    }
}
