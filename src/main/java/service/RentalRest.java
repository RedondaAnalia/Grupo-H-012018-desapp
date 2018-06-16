package service;

import persistence.services.RentalService;
import javax.ws.rs.*;


@Path("/servicesRental")
public class RentalRest {

    private RentalService rentalService;

    public RentalService getRentalService() {
        return rentalService;
    }

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }
/*
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPost(
                                @PathParam("post") int postId,
                                @PathParam("mail") String mail,
                                @PathParam("reservationSinceDate") String reservationSinceDate,
                                @PathParam("reservationUntilDate") String reservationUntilDate)
    {
        this.getRentalService().reservation(postId,mail,reservationSinceDate, reservationUntilDate);
        return Response.ok().build();
    }
*/
}
