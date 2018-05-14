package service;

import persistence.services.RentalService;
import javax.ws.rs.Path;

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
    public Response createPost(RentalDTO dto){
        Rental rental = fromDTO(dto);
        this.getRentalService().save(rental);
        return Response.ok(toDTO(rental)).build();
    }

    private Rental fromDTO(RentalDTO dto){
        Rental rental = new Rental(new Reservation());
    }
*/
}
