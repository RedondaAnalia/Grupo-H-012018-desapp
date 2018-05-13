package service;

import model.User;
import model.UserDisabled;
import model.UserEnabled;
import persistence.services.UserService;
import service.dto.UserDTO;
import javax.ws.rs.*;
public class UserRest{

    private UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public UserService getUserService(){
        return this.userService;
    }

    @GET
    @Path("/findUserByEmail/{mail}")
    @Produces("application/json")
    public UserDTO findUserByEmail(@PathParam("mail") final String mail){
        return toDTO(this.userService.filterUser(mail));
    }

    @GET
    @Path("/sizeUsers")
    @Produces("application/json")
    public int sizeUsers(){
        return this.userService.countUsers();
    }

    @POST
    @Path("/createUser/")
    @Produces("application/json")
    @Consumes("application/json")
    public UserDTO createUser(UserDTO dto){
        User user = fromDTO(dto);
        this.getUserService().save(user);
        return dto;
    }

    @PUT
    @Path("/updateUser")
    @Produces("application/json")
    @Consumes("application/json")
    public UserDTO updateUser(UserDTO dto){
        User user = fromDTO(dto);
        this.getUserService().update(user);
        return dto;
    }

    private User fromDTO(UserDTO dto){
        User user = new User();
        user.setCUIL(dto.getCUIL());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.addCredit(dto.getAccount());
        if (dto.getStatus()==1)
            user.setStatus(new UserEnabled());
        else user.setStatus(new UserDisabled());
        for(Integer s: dto.getScores())
            user.getScores().add(new Integer(s));
        user.setUserName(dto.getUserName());
        return user;
    }

    private UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setAddress(user.getAddress());
        dto.setCUIL(user.getCUIL());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setUserName(user.getUserName());
        if(user.getStatus().isEnabled())
            dto.setStatus(1);
            else
                dto.setStatus(0);
        for(Integer s: user.getScores())
            dto.getScores().add(s);
        dto.setAccount(user.getAccount().getCredit());
        return dto;
    }
}
