package service;

import model.Vehicle;
import persistence.services.VehicleService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/servicesVehicle")
public class VehicleRest {

    private VehicleService vehicleService;

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GET
    @Path("/findVehicleById/{id}")
    @Produces("application/json")
    public Vehicle findVehicleById(@PathParam("id") final int id){
        return this.vehicleService.findVehicleById(id);
    }

}
