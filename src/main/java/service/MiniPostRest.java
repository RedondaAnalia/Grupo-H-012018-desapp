package service;

import model.enums.VehicleType;
import model.minis.MiniPost;
import model.minis.MiniVehicle;
import persistence.services.MiniPostService;
import service.dto.MiniPostDTO;
import service.dto.MiniVehicleDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
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
    public MiniPostDTO createPostRest(MiniPostDTO dto) {
        MiniPost miniPost = fromDTO(dto);
        this.getMiniPostService().getRepository().save(miniPost);
        return toDTO(miniPost);
    }

    private MiniPostDTO toDTO(MiniPost mp){
        MiniPostDTO dto = new MiniPostDTO();
        dto.setCostPerDay(mp.getCostPerDay());
        dto.setId(mp.getId());
        dto.setVehicle(new MiniVehicleDTO(mp.getVehicle().getType().toString(),
                mp.getVehicle().getDescription(),
                mp.getVehicle().getPhotos()));
        dto.setSinceDate(String.valueOf(mp.getSinceDate().getYear())+String.valueOf(mp.getSinceDate().getMonth()));
        dto.setUntilDate(String.valueOf(mp.getUntilDate().getYear())+String.valueOf(mp.getUntilDate().getMonth()));
        return dto;
    }

    private MiniPost fromDTO(MiniPostDTO dto){
        MiniPost post = new MiniPost();
        LocalDateTime date;
        post.setSinceDate(LocalDateTime.parse(dto.getSinceDate()));
        post.setUntilDate(LocalDateTime.parse(dto.getUntilDate()));
        MiniVehicle miniVehicle = new MiniVehicle(
                VehicleType.valueOf(dto.getVehicle().getType()),
                dto.getVehicle().getDescription(), dto.getVehicle().getPhotos());
        post.setVehicle(miniVehicle);
        post.setCostPerDay(dto.getCostPerDay());
        return post;
    }

}
