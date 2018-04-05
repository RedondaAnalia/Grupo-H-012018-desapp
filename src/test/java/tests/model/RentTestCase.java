package tests.model;


import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.Rental;
import model.Reservation;
import model.User;
import model.exceptions.InvalidStatusChangeException;
import model.exceptions.UserBlockedException;
import model.states.rental.*;
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
    @Test
    public void elrentaldebePasarDeConfirmedByTheTenantSTAPendingReturnRentalST() {

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        //PendingRental
        rental.tenantConfirmation();
        //ConfirmedByTheOwnerST

        rental.ownerConfirmation();

        //PendingReturnRentalST

        assertEquals(rental.getState().getClass(), PendingReturnRentalST.class);
    }

    //luego de ambas confirmaciones comienza el tiempo del alquiler, el rental queda
    //en estado PendingReturnRentalST
    @Test
    public void elrentaldebePasarDeConfirmedByTheOwnerSTAPendingReturnRentalST() {

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        //PendingRental
        rental.ownerConfirmation();
        //ConfirmedByTheOwnerST

        rental.tenantConfirmation();

        //PendingReturnRentalST

        assertEquals(rental.getState().getClass(), PendingReturnRentalST.class);
    }

    //pasaje de estado PendingReturnRentalST a ReturnCOnfirmedByTheOwner
    @Test
    public void elrentaldebePasarDePendingReturnRentalSTAReturnCOnfirmedByTheOwner(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        //PendingRental
        rental.ownerConfirmation();
        //ConfirmedByTheOwnerST

        rental.tenantConfirmation();

        //PendingReturnRentalST

        rental.ownerConfirmation();
        //ReturnConfirmedByTheOwner

        assertEquals(rental.getState().getClass(), ReturnConfirmedByTheOwner.class);

    }

    //pasaje de estado PendingReturnRentalST a ReturnCOnfirmedByTheTenant
    @Test
    public void elRentaldebePasarDePendingReturnRentalSTAReturnCOnfirmedByTheTenant(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        //PendingRental
        rental.ownerConfirmation();
        //ConfirmedByTheOwnerST

        rental.tenantConfirmation();

        //PendingReturnRentalST

        rental.tenantConfirmation();
        //ReturnCOnfirmedByTheTenant

        assertEquals(rental.getState().getClass(), ReturnConfirmedByTheTenant.class);

    }

    //pasaje de estado ReturnConfirmedByTheOwner a ReturnCOnfirmedByTheTenant
    @Test
    public void elRentalDebePasarDeReturnConfirmedByTheTenantAFinalizedRentalST(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();

        //PendingRental
        rental.ownerConfirmation();
        //ConfirmedByTheOwnerST

        rental.tenantConfirmation();

        //PendingReturnRentalST

        rental.ownerConfirmation();
        //ReturnConfirmedByTheOwner

       rental.tenantUserConfirmatedReturn( 5,
               "El vehiculo estaba en perfectas condiciones");

        assertEquals(rental.getState().getClass(), FinalizedRentalST.class);

    }


    //pasaje de estado ReturnCOnfirmedByTheOwner a FinalizedRentalST
    @Test
    public void elrentaldebePasarDeReturnCOnfirmedByTheOwnerAFinalizedRentalST(){

        Post post = PostBuilder.
                aPost().
                whitCostPerHour(2).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();

        User tenantUser = UserBuilder.anUser().withCredit(100).build();

        Reservation reservation = tenantUser.
                rent(post, LocalDateTime.now(), LocalDateTime.now().plusDays(1L));

        Rental rental = reservation.beConfirm();
        //PendingRental

        // da el ok el dueño
        rental.ownerConfirmation();
        // queda en estado ConfirmedByTheOwnerST

        // da el ok el tenant
        rental.tenantConfirmation();
        //queda en estado PendingReturnRentalST

        rental.tenantConfirmation();
        //ReturnConfirmedByTheOwner

        rental.ownerUserConfirmatedReturn(3, "Salió todo ok, 100% recomendable");

        assertEquals(rental.getState().getClass(), FinalizedRentalST.class);

    }


}
