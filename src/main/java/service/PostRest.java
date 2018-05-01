package service;

import model.minis.MiniPost;
import persistence.services.PostService;

import javax.ws.rs.*;
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
/*
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPost(MiniPost miniPost) {
        this.getPostService().getRepository().save(miniPost);
        return Response.ok().build();
    }


    public MiniPost fromDTO(MiniPostDTO dto){

    }

    public MiniPostDTO toDTO(MiniPost minip){

    }
        */
}
