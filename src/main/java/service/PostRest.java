package service;

import model.Coord;
import model.Post;
import model.Vehicle;
import persistence.services.PostService;
import persistence.services.UserService;
import persistence.services.VehicleService;
import service.dto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Path("/servicesPost")
public class PostRest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private PostService postService;

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    private PostService getPostService() {
        return this.postService;
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService getUserService() {
        return this.userService;
    }

    private VehicleService vehicleService;

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GET
    @Path("/allPost")
    @Produces("application/json")
    public List<PostDTO> allPostRest() {
        return postsToPostsDTO(this.getPostService().allPost());
    }

    @POST
    @Path("/createPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PostDTO createPostRest(PostWithoutVehicleDTO dto) {
        Post post = fromDTO(dto);
        post = this.getPostService().merge(post);
        return postToPostDTO(post);
    }

    @GET
    @Path("/allMiniPost")
    @Produces("application/json")
    public List<MiniPostDTO> allMiniPostRest() {
        return postToMiniPostDTO(this.getPostService().allPost());
    }

    @GET
    @Path("/sizePost")
    @Produces("application/json")
    public int sizePost() {
        return this.getPostService().sizePost();
    }


    @GET
    @Path("/PostByType/{type}")
    @Produces("application/json")
    public List<PostDTO> PostByTypeRest(@PathParam("type") final String type) {
        return postsToPostsDTO(this.getPostService().postByType(type));
    }

    @GET
    @Path("/postById/{id}")
    @Produces("application/json")
    public PostDTO postByIdRest(@PathParam("id") final int id) {
        return postToPostDTO(this.getPostService().postById(id));
    }

    @GET
    @Path("/postByLocation/{location}")
    @Produces("application/json")
    public List<PostDTO>  postByLocationRest(@PathParam("location") final String location){
        return postsToPostsDTO(this.getPostService().postByLocation(location));
    }

    /* =======================
    /     de POST a DTO
    / =======================*/

    //lista de post a lista de mini dtos
    private List<MiniPostDTO> postToMiniPostDTO(List<Post> posts){
        List<MiniPostDTO> list = new ArrayList<>();
        for(Post p: posts){
            MiniPostDTO mp = new MiniPostDTO();
            mp.setId(p.getId());
            mp.setLocation(p.getLocation());
            mp.setCostPerDay(p.getCostPerDay());
            mp.setPickUpCoord(coordToCoordDTO(p.getPickUpCoord()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatSinceDate = p.getSinceDate().format(formatter);
            mp.setSinceDate(formatSinceDate);
            String formatUntilDate = p.getUntilDate().format(formatter);
            mp.setUntilDate(formatUntilDate);

            mp.setVehicle(new MiniVehicleDTO(
                    p.getVehicle().getType().toString(),
                    p.getVehicle().getDescription(),
                    p.getVehicle().getPhotos(),
                    p.getVehicle().getId()));

            list.add(mp);
        }
        return list;
    }

    //post a dto
    private PostDTO postToPostDTO(Post p) {
        PostDTO dto = new PostDTO();
        dto.setId(p.getId());
        dto.setCostPerDay(p.getCostPerDay());
        dto.setLocation(p.getLocation());
        String formatSinceDate = p.getSinceDate().format(this.formatter);
        dto.setSinceDate(formatSinceDate);
        String formatUntilDate = p.getUntilDate().format(this.formatter);
        dto.setUntilDate(formatUntilDate);
        dto.setVehicle(toDTO(p.getVehicle()));
        dto.setOwnerUser(p.getOwnerUser().getEmail());
        dto.setPhone(p.getPhone());
        dto.setPickUpCoord(coordToCoordDTO(p.getPickUpCoord()));
        dto.setReturnCoords(coordToCoordDTO(p.getReturnCoords()));
        return dto;
    }

    //coord a dto
    private CoordDTO coordToCoordDTO(Coord c){
        CoordDTO dto = new CoordDTO();
        dto.setLat(c.getLat());
        dto.setLng(c.getLat());
        dto.setId(c.getId());
        return dto;
    }

    //lista Post a lista dtos
    private List<PostDTO> postsToPostsDTO(List<Post> posts) {
        List<PostDTO> list = new ArrayList<>();
        for(Post p: posts) {
            list.add(postToPostDTO(p));
        }
        return list;
    }

    /* =======================
    /     de DTO a POST
    / =======================*/



    private Coord coordDTOToCoord(CoordDTO dto){
        Coord c = new Coord();
        c.setLat(dto.getLat());
        c.setLng(dto.getLng());
        return c;
    }

    //de dto a Post
    private Post fromDTO(PostWithoutVehicleDTO dto){
        Post post = new Post();
        post.setVehicle(this.getVehicleService().findVehicleById(dto.getVehicle()));
        post.setCostPerDay(dto.getCostPerDay());
        post.setOwnerUser(this.getUserService().filterUser(dto.getOwnerUser()));
        post.setPhone(dto.getPhone());
        post.setPickUpCoord(coordDTOToCoord(dto.getPickUpCoord()));
        post.setReturnCoords(coordDTOToCoord(dto.getReturnCoords()));
        post.setSinceDate(
                LocalDateTime.of(
                        Integer.valueOf(dto.getSinceDate().substring(0,4)),
                        Integer.valueOf(dto.getSinceDate().substring(5,7)),
                        Integer.valueOf(dto.getSinceDate().substring(8,10)),
                        0,
                        0
                )
        );

        post.setUntilDate(
                LocalDateTime.of(
                        Integer.valueOf(dto.getUntilDate().substring(0,4)),
                        Integer.valueOf(dto.getUntilDate().substring(5,7)),
                        Integer.valueOf(dto.getUntilDate().substring(8,10)),
                        0,
                        0
                )
        );
        post.setLocation(dto.getLocation());
        return post;
    }

    private VehicleDTO toDTO(Vehicle vehicle){
        VehicleDTO dto = new VehicleDTO();
        dto.setCapacity(vehicle.getCapacity());
        dto.setDescription(vehicle.getDescription());
        dto.setType(vehicle.getType());
        dto.setOwner(vehicle.getOwner().getEmail());
        if (!vehicle.getPhotos().isEmpty()) {
            for (String p : vehicle.getPhotos())
                dto.getPhotos().add(p);
        }
        dto.setId(vehicle.getId());
        return dto;
    }

}
