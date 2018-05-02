package service;

import model.enums.VehicleType;
import model.minis.MiniPost;
import model.minis.MiniVehicle;
import persistence.services.PostService;
import service.dto.MiniPostDTO;

import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/servicesPost")
public class PostRest {

    private PostService postService;

    public void setPostService(PostService postService){
        this.postService = postService;
    }

    public PostService getPostService(){
        return this.postService;
    }


    @GET
    @Path("/allMiniPost")
    @Produces("application/json")
    public List<MiniPost> allMiniPost(){
        return this.postService.allMiniPost();
    }

    @POST
    @Path("/createPost")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPost(MiniPostDTO dto) {
        MiniPost miniPost = fromDTO(dto);
        this.getPostService().getRepository().save(miniPost);
        return Response.ok().build();
    }

    private MiniPost fromDTO(MiniPostDTO dto){
        MiniPost post = new MiniPost();
        post.setSinceDate(dto.getSinceDate());
        post.setUntilDate(dto.getUntilDate());
        MiniVehicle miniVehicle = new MiniVehicle(
                VehicleType.valueOf(dto.getVehicle().getType()), dto.getVehicle().getDescription());
        post.setVehicle(miniVehicle);
        return post;
    }

}
