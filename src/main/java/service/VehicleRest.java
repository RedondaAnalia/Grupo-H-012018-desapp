package service;

import model.User;
import model.Vehicle;
import persistence.services.UserService;
import persistence.services.VehicleService;
import service.dto.UserWithVehiclesDTO;
import service.dto.VehicleDTO;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/servicesVehicle")
public class VehicleRest {

    private VehicleService vehicleService;

    private VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    private UserService getUserService(){
        return this.userService;
    }

    @GET
    @Path("/findVehicleById/{id}")
    @Produces("application/json")
    public VehicleDTO findVehicleByIdRest(@PathParam("id") final int id){
        return toDTO(this.getVehicleService().findVehicleById(id));
    }

    @POST
    @Path("/createVehicle")
    @Produces("application/json")
    @Consumes("application/json")
    public UserWithVehiclesDTO createVehicleRest(VehicleDTO dto){
        Vehicle vehicle = fromDTO(dto);
        this.getVehicleService().save(vehicle);
        return toDTO(this.getVehicleService().filterVehicleByUser(dto.getOwner().getEmail()),
                vehicle.getOwner());
    }

    @PUT
    @Path("/updateVehicle")
    @Produces("application/json")
    @Consumes("application/json")
    public UserWithVehiclesDTO updateVehicleRest(VehicleDTO dto){
        Vehicle vehicle = fromDTO(dto);
        this.getVehicleService().update(vehicle);
        return toDTO(this.getVehicleService().filterVehicleByUser(dto.getOwner().getEmail()),
                vehicle.getOwner());
    }


    @DELETE
    @Path("/deleteVehicle/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public UserWithVehiclesDTO deleteVehicleRest(@PathParam("id") final int id){
        Vehicle vehicle = this.getVehicleService().findById(id);
        this.getVehicleService().delete(vehicle);
        return toDTO(this.getVehicleService().filterVehicleByUser(vehicle.getOwner().getEmail()),
        vehicle.getOwner());
    }

    private UserWithVehiclesDTO toDTO(List<Vehicle> vehicles, User user){
        UserWithVehiclesDTO dto = new UserWithVehiclesDTO();

        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for(Vehicle v : vehicles){
            vehicleDTOS.add(toDTO(v));
        }
        dto.setVehicles(vehicleDTOS);

        dto.setAddress(user.getAddress());
        dto.setCUIL(user.getCUIL());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setUserName(user.getUserName());
        if(user.getStatus().isEnabled())
            dto.setStatus(true);
        else
            dto.setStatus(false);
        for(Integer s: user.getScores())
            dto.getScores().add(s);
        dto.setAccount(user.getAccount().getCredit());
        return dto;
    }

    private Vehicle fromDTO(VehicleDTO dto){
        Vehicle vehicle = new Vehicle();
        vehicle.setOwner(this.getUserService().findById(dto.getOwner().getEmail()));
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
        if (!vehicle.getPhotos().isEmpty()) {
            for (String p : vehicle.getPhotos())
                dto.getPhotos().add(p);
        }
        dto.setId(vehicle.getId());
        return dto;
    }
}