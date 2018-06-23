package service;

import model.Rental;
import model.Reservation;
import persistence.services.ReservationService;
import service.dto.RentalDTO;
import service.dto.ReservationDTO;
import javax.ws.rs.*;
import java.time.format.DateTimeFormatter;

@Path("/servicesReservation")
public class ReservationRest {

    private ReservationService reservationService;

    private ReservationService getReservationService() {
        return reservationService;
    }

    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @POST
    @Path("/createReservation/{postId}/{mail}/{sinceDate}/{untilDate}")
    @Consumes("application/json")
    @Produces("application/json")
    public ReservationDTO createPost(
                                @PathParam("postId") int postId,
                                @PathParam("mail") String mail,
                                @PathParam("sinceDate") String reservationSinceDate,
                                @PathParam("untilDate") String reservationUntilDate)
    {
        return reservationDTOToReservation(this.getReservationService().
                reservation(postId,mail,reservationSinceDate, reservationUntilDate));
    }

    private ReservationDTO reservationDTOToReservation(Reservation r){

        return new ReservationDTO(
                r.getTenantUser().getEmail(),
                r.getPost().getId(),
                r.getReservationSinceDate().format(formatter),
                r.getReservationUntilDate().format(formatter),
                r.getStatus().toString(), r.getId());
    }

    @PUT
    @Path("/confirmedReservation/{idReserv}")
    @Consumes("application/json")
    @Produces("application/json")
    public RentalDTO confirmedRest(@PathParam("idReserv") final int idReserv){
        return rentalToRentalDTO(this.getReservationService().confirmedReservation(idReserv));
    }

    @PUT
    @Path("/rejectReservation/{idReserv}")
    @Consumes("application/json")
    @Produces("application/json")
    public void rejectRest(@PathParam("idReserv") final int idReserv){
        this.getReservationService().rejectReservation(idReserv);
    }

    //todas las reservas  y rentals por id usuario owner sin filtrar
    // todas las reservas y rentals por usuario tenant


    private RentalDTO rentalToRentalDTO(Rental rental){
        RentalDTO dto = new RentalDTO();
        dto.setId(rental.getId());
        dto.setBeginRentalTime(rental.getBeginRentalTime().format(formatter));
        dto.setIdReservation(rental.getReservation().getId());
        //dto.setRentalState(rental.getState());
        return dto;
    }

}
