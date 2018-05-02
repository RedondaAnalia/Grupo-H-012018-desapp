package service;

import persistence.services.UserService;
import service.dto.UserDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/servicesUsers")
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
    public List<UserDTO> findUserByEmail(@PathParam("mail") final String mail){
        return this.userService.filterUser(mail);
    }

}
