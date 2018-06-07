package service;

import model.Post;
import persistence.services.PostService;
import service.dto.MiniPostDTO;
import service.dto.MiniVehicleDTO;
import service.dto.PostDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/servicesPost")
public class PostRest {

    private PostService postService;

    public void setPostService(PostService postService){
        this.postService = postService;
    }

    private PostService getPostService(){
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
        this.getPostService().save(post);
        return post;
    }

    @GET
    @Path("/allMiniPost")
    @Produces("application/json")
    public List<MiniPostDTO> allMiniPostRest(){
        return postToMiniPostDTO(this.getPostService().allPost());
    }

    private List<MiniPostDTO> postToMiniPostDTO(List<Post> posts){
        List<MiniPostDTO> list = new ArrayList<MiniPostDTO>();
        for(Post p: posts){
            MiniPostDTO mp = new MiniPostDTO();
            mp.setCostPerDay(p.getCostPerDay());
            mp.setSinceDate(p.getSinceDate().toString());
            mp.setUntilDate(mp.getUntilDate());
            mp.setVehicle(new MiniVehicleDTO(p.getVehicle().getType().toString(), p.getVehicle().
                    getDescription(), p.getVehicle().getPhotos()));
            list.add(mp);
        }
        return list;
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

    /*
        private MiniPostDTO toDTO(MiniPost mp){
        MiniPostDTO dto = new MiniPostDTO();
        dto.setCostPerDay(mp.getCostPerDay());
        dto.setId(mp.getId());
        dto.setVehicle(new MiniVehicleDTO(mp.getVehicle().getType().toString(),
                mp.getVehicle().getDescription(),
                mp.getVehicle().getPhotos()));
        dto.setSinceDate(String.valueOf(mp.getSinceDate().getYear())+String.valueOf(mp.getSinceDate().getMonth()));
        System.out.println(String.valueOf(mp.getSinceDate().getYear())+String.valueOf(mp.getSinceDate().getMonth()));
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
    */

}
