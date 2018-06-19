package service;

import persistence.services.RentalService;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/servicesRental")
public class RentalRest {

    private RentalService rentalService;

    public RentalService getRentalService() {
        return rentalService;
    }

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @POST
    @Path("/createReservation/{post}/{mail}/{sinceDate}/{untilDate}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPost(
                                @PathParam("post") int postId,
                                @PathParam("mail") String mail,
                                @PathParam("sinceDate") String reservationSinceDate,
                                @PathParam("untilDate") String reservationUntilDate)
    {
        this.getRentalService().reservation(postId,mail,reservationSinceDate, reservationUntilDate);
        return Response.ok().build();
    }
/*
    @GET
    @Path("/rentalById/{id}")
    @Consumes("application/json")
    public Rental reservationById(@PathParam("id") final int id){
        return this.getRentalService().reservationById(id);
    }
*/
}
