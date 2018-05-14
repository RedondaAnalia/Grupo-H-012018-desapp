package service;

import model.Vehicle;
import persistence.services.UserService;
import persistence.services.VehicleService;
import service.dto.VehicleDTO;
import javax.ws.rs.*;

@Path("/servicesVehicle")
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
    public VehicleDTO deleteVehicle(@PathParam("id") final int id){
        Vehicle vehicle = this.getVehicleService().findById(id);
        this.getVehicleService().delete(vehicle);
        return toDTO(vehicle);
    }


    private Vehicle fromDTO(VehicleDTO dto){
        Vehicle vehicle = new Vehicle();
        vehicle.setOwner(this.getUserService().findById(dto.getOwner()));
        vehicle.setType(dto.getType());
        vehicle.setCapacity(dto.getCapacity());
        vehicle.setDescription(dto.getDescription());
        for(String p:dto.getPhotos())
            vehicle.getPhotos().add(p);
        vehicle.setId(dto.getId());
        return vehicle;
    }

    private VehicleDTO toDTO(Vehicle vehicle){
        VehicleDTO dto = new VehicleDTO();
        dto.setCapacity(vehicle.getCapacity());
        dto.setDescription(vehicle.getDescription());
        dto.setType(vehicle.getType());
        dto.setOwner(vehicle.getOwner().getEmail());
        for(String p:vehicle.getPhotos())
            dto.getPhotos().add(p);
        dto.setId(vehicle.getId());
        return dto;
    }
}