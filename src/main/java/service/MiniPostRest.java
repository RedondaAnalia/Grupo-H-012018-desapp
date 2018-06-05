package service;

import model.enums.VehicleType;
import model.minis.MiniPost;
import model.minis.MiniVehicle;
import persistence.services.MiniPostService;
import service.dto.MiniPostDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/servicesMiniPost")
public class MiniPostRest {

    private MiniPostService miniPostService;

    public void setMiniPostService(MiniPostService miniPostService){
        this.miniPostService = miniPostService;
    }

    public MiniPostService getMiniPostService(){
        return this.miniPostService;
    }


    @GET
    @Path("/allMiniPost")
    @Produces("application/json")
    public List<MiniPost> allMiniPostRest(){
        return this.miniPostService.allMiniPost();
    }

    @POST
    @Path("/createMiniPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MiniPost createPostRest(MiniPostDTO dto) {
        MiniPost miniPost = fromDTO(dto);
        this.getMiniPostService().getRepository().save(miniPost);
        return miniPost;
    }

    private MiniPost fromDTO(MiniPostDTO dto){
        MiniPost post = new MiniPost();
        post.setSinceDate(dto.getSinceDate());
        post.setUntilDate(dto.getUntilDate());
        MiniVehicle miniVehicle = new MiniVehicle(
                VehicleType.valueOf(dto.getVehicle().getType()), dto.getVehicle().getDescription(), dto.getVehicle().getPhotos());
        post.setVehicle(miniVehicle);

        return post;
    }

}
