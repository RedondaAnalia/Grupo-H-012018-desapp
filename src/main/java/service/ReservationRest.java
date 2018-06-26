package service;

import model.Rental;
import model.Reservation;
import persistence.services.ReservationService;
import service.dto.RentalDTO;
import service.dto.ReservationDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    private List<ReservationDTO>listReservationDTOToReservation(List<Reservation> lr){
        List<ReservationDTO> ldto = new ArrayList<>();
        for(Reservation r: lr){
            ldto.add(reservationDTOToReservation(r));
        }
        return ldto;
    }

    private ReservationDTO reservationDTOToReservation(Reservation r){

        return new ReservationDTO(
                r.getTenantUser().getEmail(),
                r.getPost().getId(),
                r.getReservationSinceDate().format(formatter),
                r.getReservationUntilDate().format(formatter),
                r.getStatus().toString(),
                r.getId(),
                r.getPost().getOwnerUser().getEmail());
    }

    @PUT
    @Path("/confirmedReservation/{idReserv}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response confirmedRest(@PathParam("idReserv") final int idReserv){
        Response.Status r = Response.Status.OK;
        try{
            this.getReservationService().confirmedReservation(idReserv);
        }catch (RuntimeException e){
            r = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(r).build();
    }

    @PUT
    @Path("/rejectReservation/{idReserv}")
    @Consumes("application/json")
    @Produces("application/json")
    public void rejectRest(@PathParam("idReserv") final int idReserv){
        this.getReservationService().rejectReservation(idReserv);
    }

    @GET
    @Path("/allOwnerPendingReservations/{mail}")
    @Produces("application/json")
    public List<ReservationDTO> allOwnerPendingReservationsRest(@PathParam("mail") final String mail) {
        return listReservationDTOToReservation(this.getReservationService().findAllOwnerPendingReservations(mail));
    }

    @GET
    @Path("/allTenantPendingReservations/{mail}")
    @Produces("application/json")
    public List<ReservationDTO> allTenantPendingReservationsRest(@PathParam("mail") final String mail) {
        return listReservationDTOToReservation(this.getReservationService().findAllTenantPendingReservations(mail));
    }

    @GET
    @Path("/allOwnerRentals/{mail}")
    @Produces("application/json")
    public List<RentalDTO> allRentalsRest(@PathParam("mail") final String mail) {
        return listRentalToRentalDTO(this.getReservationService().findAllOwnerRentals(mail));
    }

    @GET
    @Path("/allTenantRentals/{mail}")
    @Produces("application/json")
    public List<RentalDTO> allRentalsTenantRest(@PathParam("mail") final String mail) {
        return listRentalToRentalDTO(this.getReservationService().findAllTenantRentals(mail));
    }


    //el dueño confirma que se llevaron el auto
    @PUT
    @Path("/confirmedRentalByOwner/{idRental}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response confirmedRentalByOwnerRest(@PathParam("idRental") final int idRental){
        Response.Status r = Response.Status.OK;
        try{
            this.getReservationService().confirmedRentalByOwner(idRental);
        }catch (RuntimeException e){
            r = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(r).build();
    }

    // el inquilino confirma que se llevó el auto
    @PUT
    @Path("/confirmedRentalByTenant/{idRental}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response confirmedRentalByTenantRest(@PathParam("idRental") final int idRental){
        Response.Status r = Response.Status.OK;
        try{
            this.getReservationService().confirmedRentalByTenant(idRental);
        }catch (RuntimeException e){
            r = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(r).build();
    }

    // el inquilino confirma que devolvió el vehiculo
    @PUT
    @Path("/confirmedReturnByTenant/{idRental}/{score}/{comment}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response confirmedReturnByTenantRest(
            @PathParam("idRental") int idRental,
            @PathParam("score") Integer score,
            @PathParam("comment") String comment
            ){
        Response.Status r = Response.Status.OK;
        try{
            this.getReservationService().confirmedReturnByTenant(idRental, score, comment);
        }catch (RuntimeException e){
            r = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(r).build();
    }

    // el dueño confirma que le devolvieron el vehiculo
    @PUT
    @Path("/confirmedReturnByOwner/{idRental}/{score}/{comment}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response confirmedReturnByOwnerRest(
            @PathParam("idRental") int idRental,
            @PathParam("score") Integer score,
            @PathParam("comment") String comment
    ){
       Response.Status r = Response.Status.OK;
        try{
            this.getReservationService().confirmedReturnByOwner(idRental, score, comment);
        }catch (RuntimeException e){
            r = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(r).build();
    }



    private List<RentalDTO> listRentalToRentalDTO(List<Rental> lr){
        List<RentalDTO> ldto = new ArrayList<>();
        for(Rental r: lr){
           ldto.add(rentalToRentalDTO(r));
        }
        return ldto;
    }

    private RentalDTO rentalToRentalDTO(Rental r){
        RentalDTO dto = new RentalDTO();
        dto.setRentalState(r.getState().toString());
        dto.setId(r.getId());
        dto.setIdReservation(r.getReservation().getId());
        //dto.setBeginRentalTime(r.getRentalTime());
        return dto;
    }

}
