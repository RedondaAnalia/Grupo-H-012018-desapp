package service;

import model.Vehicle;
import persistence.services.UserService;
import persistence.services.VehicleService;
import service.dto.VehicleDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

//@Path("/servicesVehicle")
public class VehicleRest {

    private VehicleService vehicleService;

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public UserService getUserService(){
        return this.userService;
    }


    @GET
    @Path("/findVehicleById/{id}")
    @Produces("application/json")
    public Vehicle findVehicleById(@PathParam("id") final int id){
        return this.getVehicleService().findVehicleById(id);
    }

    @POST
    @Path("/createVehicle")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createVehicle(VehicleDTO dto){
        Vehicle vehicle = fromDTO(dto);
        this.getVehicleService().save(vehicle);
        return Response.ok().build();
    }


    @PUT
    @Path("/updateVehicle")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateVehicle(VehicleDTO dto){
        Vehicle vehicle = fromDTO(dto);
        this.getVehicleService().update(vehicle);
        return Response.ok().build();
    }


    @DELETE
    @Path("/deleteVehicle/{id}")
    @Consumes("text/plain")
    public void deleteVehicle(@PathParam("id") final int id){
        this.getVehicleService().delete(this.getVehicleService().findById(id));
        //return Response.ok().build();
    }


    private Vehicle fromDTO(VehicleDTO dto){
        Vehicle vehicle = new Vehicle();
        //vehicle.setOwner(this.getUserService().findById(dto.getOwner()));
        vehicle.setType(dto.getType());
        vehicle.setCapacity(dto.getCapacity());
        vehicle.setDescription(dto.getDescription());
        //private List<String> photos;
        return vehicle;
    }
}