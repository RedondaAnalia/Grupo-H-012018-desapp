package service;

import model.Vehicle;
import persistence.services.VehicleService;
import service.dto.VehicleDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


public class VehicleRest {

    private VehicleService vehicleService;

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
/*
    private UserService uS;

    public void setUserService(UserService userService){
        this.uS = userService;
    }

    public UserService getUserService(){
        return this.uS;
    }
*/

    @GET
    @Path("/findVehicleById/{id}")
    @Produces("application/json")
    public VehicleDTO findVehicleById(@PathParam("id") final int id){
        return toDTO(this.getVehicleService().findVehicleById(id));
    }

    @POST
    @Path("/createVehicle")
    @Produces("application/json")
    @Consumes("application/json")
    public VehicleDTO createVehicle(VehicleDTO dto){
        Vehicle vehicle = fromDTO(dto);
        this.getVehicleService().save(vehicle);
        return dto;
    }

    @PUT
    @Path("/updateVehicle")
    @Produces("application/json")
    @Consumes("application/json")
    public VehicleDTO updateVehicle(VehicleDTO dto){
        Vehicle vehicle = fromDTO(dto);
        this.getVehicleService().update(vehicle);
        return dto;
    }


    @DELETE
    @Path("/deleteVehicle/{id}")
    @Consumes("application/json")
    public Response deleteVehicle(@PathParam("id") final int id){
        this.getVehicleService().delete(this.getVehicleService().findById(id));
        return Response.ok().build();
    }


    private Vehicle fromDTO(VehicleDTO dto){
        Vehicle vehicle = new Vehicle();
        //vehicle.setOwner(this.getUserService().findById(dto.getOwner()));
        vehicle.setType(dto.getType());
        vehicle.setCapacity(dto.getCapacity());
        vehicle.setDescription(dto.getDescription());
        for(String p:dto.getPhotos())
            vehicle.getPhotos().add(p);
        return vehicle;
    }

    private VehicleDTO toDTO(Vehicle vehicle){
        VehicleDTO dto = new VehicleDTO();
        dto.setCapacity(vehicle.getCapacity());
        dto.setDescription(vehicle.getDescription());
        dto.setType(vehicle.getType());
        return dto;
    }
}