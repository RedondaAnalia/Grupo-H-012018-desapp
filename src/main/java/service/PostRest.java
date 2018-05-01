package service;

import model.minis.MiniPost;
import persistence.services.PostService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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


}
