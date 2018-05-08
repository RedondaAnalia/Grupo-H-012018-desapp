package service;

import model.User;
import persistence.services.UserService;
import service.dto.UserDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/servicesUsers")
public class UserRest{

    private UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public UserService getUserService(){
        return this.userService;
    }

    /**
     * Servicio que busca al usuario por mail
     * @param mail
     * @return UserDTO
     */
    @GET
    @Path("/findUserByEmail/{mail}")
    @Produces("application/json")
    public UserDTO findUserByEmail(@PathParam("mail") final String mail){
        return toDTO(this.userService.filterUser(mail));
    }


    @POST
    @Path("/createUser")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createUser(UserDTO dto){
        User user = fromDTO(dto);
        this.getUserService().save(user);
        return Response.ok().build();
    }

    @PUT
    @Path("/updateUser")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateUser(UserDTO dto){
        User user = fromDTO(dto);
        this.getUserService().update(user);
        return Response.ok().build();
    }

    public User fromDTO(UserDTO dto){
        User user = new User();
        user.setCUIL(dto.getCUIL());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        //private Account account;
        //private IUserState status;
        //private ArrayList<Integer> scores;
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        return user;
    }

    private UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setAddress(user.getAddress());
        dto.setCUIL(user.getCUIL());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }
}
