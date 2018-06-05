package service;

import model.Post;
import persistence.services.PostService;
import service.dto.PostDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Path("/allPost")
    @Produces("application/json")
    public List<Post> allPostRest(){
        return this.postService.allPost();
    }

    @POST
    @Path("/createPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post createPostRest(PostDTO dto) {
        Post post = fromDTO(dto);
        this.getPostService().getRepository().save(post);
        return post;
    }

    private Post fromDTO(PostDTO dto){
        Post post = new Post();
        //post.setVehicle(dto.getVehicle());
        post.setCostPerDay(dto.getCostPerDay());
        //post.setOwnerUser(dto.getOwnerUser());
        post.setPhone(dto.getPhone());
        post.setId(dto.getId());
        //post.setPickUpCoord(dto.getPickUpCoord());
        //post.setReturnCoords(dto.getReturnCoords());
        //post.setSinceDate(LocalDateTime.parse(dto.getSinceDate()));
        //post.setUntilDate(LocalDateTime.parse(dto.getUntilDate()));
        return post;
    }

}
