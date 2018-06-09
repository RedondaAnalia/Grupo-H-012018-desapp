package service;

import model.Coord;
import model.Post;
import persistence.services.PostService;
import service.dto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public List<PostDTO> allPostRest(){
        return postToPostDTO(this.getPostService().allPost());
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

    @GET
    @Path("/sizePost")
    @Produces("application/json")
    public int sizePost(){
        return this.getPostService().sizePost();
    }

    private List<MiniPostDTO> postToMiniPostDTO(List<Post> posts){
        List<MiniPostDTO> list = new ArrayList<MiniPostDTO>();
        for(Post p: posts){
            MiniPostDTO mp = new MiniPostDTO();

            mp.setCostPerDay(p.getCostPerDay());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatSinceDate = p.getSinceDate().format(formatter);
            mp.setSinceDate(formatSinceDate);
            String formatUntilDate = p.getUntilDate().format(formatter);
            mp.setUntilDate(formatUntilDate);

            mp.setVehicle(new MiniVehicleDTO(p.getVehicle().getType().toString(), p.getVehicle().
                    getDescription(), p.getVehicle().getPhotos()));

            list.add(mp);
        }
        return list;
    }

    private List<PostDTO> postToPostDTO(List<Post> posts) {
        List<PostDTO> list = new ArrayList<PostDTO>();
        for(Post p: posts) {
            PostDTO dto = new PostDTO();
            dto.setCostPerDay(p.getCostPerDay());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatSinceDate = p.getSinceDate().format(formatter);
            dto.setSinceDate(formatSinceDate);
            String formatUntilDate = p.getUntilDate().format(formatter);
            dto.setUntilDate(formatUntilDate);

            dto.setVehicle(new VehicleDTO(p.getVehicle().getType(), p.getVehicle().getDescription(), p.getVehicle().getPhotos()));
/*
    private UserDTO ownerUser;
 */
            dto.setPhone(p.getPhone());
            dto.setPickUpCoord(coordToCoordDTO(p.getPickUpCoord()));

            List<CoordDTO> returnCoords = new ArrayList<CoordDTO>();
            for(Coord c: p.getReturnCoords()){
                CoordDTO cdto = new CoordDTO();
                cdto.setLng(c.getLng());
                cdto.setLat(c.getLat());
                returnCoords.add(cdto);
            }
            dto.setReturnCoords(returnCoords);
            list.add(dto);
        }
        return list;
    }

    private CoordDTO coordToCoordDTO(Coord c){
        CoordDTO dto = new CoordDTO();
        dto.setLat(c.getLat());
        dto.setLng(c.getLat());
        return dto;
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
        post.setSinceDate(LocalDateTime.parse(dto.getSinceDate()));
        //post.setUntilDate(LocalDateTime.parse(dto.getUntilDate()));
        return post;
    }

}
