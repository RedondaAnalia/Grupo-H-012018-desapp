package service;

import persistence.services.UserService;
import service.dto.UserDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

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
        return this.userService.filterUser(mail);
    }
/*
    @POST
    @Path("/login/{mail}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response.ResponseBuilder login(
            @PathParam("mail") final String mail){
        if (this.userService.filterUser(mail)!=null)
            return ok();
        else
            return noContent();
    }
*/
}
