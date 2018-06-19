package service;

import model.User;
import model.UserDisabled;
import model.UserEnabled;
import model.Vehicle;
import persistence.services.UserService;
import persistence.services.VehicleService;
import service.dto.UserDTO;
import service.dto.UserWithVehiclesDTO;
import service.dto.VehicleDTO;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/servicesUsers")
public class UserRest{

    private UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    private UserService getUserService(){
        return this.userService;
    }

    private VehicleService vehicleService;

    private VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GET
    @Path("/findUserByEmail/{mail}")
    @Produces("application/json")
    public UserWithVehiclesDTO findUserByEmailRest(@PathParam("mail") final String mail){
        User udto = this.userService.filterUser(mail);
        if (udto==null)
            return null;
            else
        return toDTO(this.getVehicleService().filterVehicleByUser(mail), udto);
    }

    @GET
    @Path("/sizeUsers")
    @Produces("application/json")
    public int sizeUsersRest(){
        return this.userService.countUsers();
    }

    @POST
    @Path("/createUser/")
    @Produces("application/json")
    @Consumes("application/json")
    public UserWithVehiclesDTO createUserRest(UserDTO dto){
        User user = fromDTOToCreate(dto);
        this.getUserService().save(user);
        return toDTO(this.getVehicleService().filterVehicleByUser(dto.getEmail()), user);
    }

    @PUT
    @Path("/updateUser")
    @Produces("application/json")
    @Consumes("application/json")
    public UserWithVehiclesDTO updateUserRest(UserDTO dto){
        User user = fromDTO(dto);
        this.getUserService().update(user);
        return toDTO(this.getVehicleService().filterVehicleByUser(dto.getEmail()), user);
    }

    @PUT
    @Path("/addCredit/{credit}/{mail}")
    @Produces("application/json")
    @Consumes("application/json")
    public UserWithVehiclesDTO addCreditRest(
                                        @PathParam("credit") double credit,
                                         @PathParam("mail") String mail){
        return toDTO(this.getVehicleService().filterVehicleByUser(mail),
                this.getUserService().addCreditToUser(credit, mail));
    }

    @PUT
    @Path("/debitCredit/{credit}/{mail}")
    @Produces("application/json")
    @Consumes("application/json")
    public UserWithVehiclesDTO debitCreditRest(
            @PathParam("credit") double credit,
            @PathParam("mail") String mail){
        return toDTO(this.getVehicleService().filterVehicleByUser(mail),
                this.getUserService().debitCreditToUser(credit, mail));
    }

    private User fromDTOToCreate(UserDTO dto){
        User user = new User(dto.getCUIL(), dto.getName(),
                dto.getSurname(), dto.getAddress(), dto.getEmail());
        user.setUserName(dto.getUserName());
        return user;
    }

    private User fromDTO(UserDTO dto){
        User user = new User();
        user.setCUIL(dto.getCUIL());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.addCredit(dto.getAccount());
        if (dto.getStatus())
            user.setStatus(new UserEnabled());
        else user.setStatus(new UserDisabled());
        for(Integer s: dto.getScores())
            user.getScores().add(s);
        user.setUserName(dto.getUserName());
        return user;
    }


    private UserWithVehiclesDTO toDTO(List<Vehicle> vehicles, User user){
        UserWithVehiclesDTO dto = new UserWithVehiclesDTO();

        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for(Vehicle vehicle : vehicles){
            VehicleDTO dtoV = new VehicleDTO();
            dtoV.setCapacity(vehicle.getCapacity());
            dtoV.setDescription(vehicle.getDescription());
            dtoV.setType(vehicle.getType());
            dtoV.setOwner(vehicle.getOwner().getEmail());
            for(String p:vehicle.getPhotos())
                dtoV.getPhotos().add(p);
            vehicleDTOS.add(dtoV);
        }
        dto.setVehicles(vehicleDTOS);

        dto.setAddress(user.getAddress());
        dto.setCUIL(user.getCUIL());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setUserName(user.getUserName());
        dto.setReputation(user.reputation());
        if(user.getStatus().isEnabled())
            dto.setStatus(true);
        else
            dto.setStatus(false);
        for(Integer s: user.getScores())
            dto.getScores().add(s);
        dto.setAccount(user.getAccount().getCredit());
        return dto;
    }
}
