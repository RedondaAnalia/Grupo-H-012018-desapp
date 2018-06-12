package service;

import model.Coord;
import model.Post;
import model.Vehicle;
import persistence.services.PostService;
import persistence.services.UserService;
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
    public Post createPostRest(PostDTO dto) {
        Post post = fromDTO(dto);
        this.getPostService().save(post);
        return post;
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

    private List<MiniPostDTO> postToMiniPostDTO(List<Post> posts){
        List<MiniPostDTO> list = new ArrayList<>();
        for(Post p: posts){
            MiniPostDTO mp = new MiniPostDTO();
            mp.setLocation(p.getLocation());
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

    private PostDTO postToPostDTO(Post p) {
        PostDTO dto = new PostDTO();
        dto.setCostPerDay(p.getCostPerDay());
        dto.setLocation(p.getLocation());
        String formatSinceDate = p.getSinceDate().format(this.formatter);
        dto.setSinceDate(formatSinceDate);
        String formatUntilDate = p.getUntilDate().format(this.formatter);
        dto.setUntilDate(formatUntilDate);
        dto.setVehicle(new VehicleDTO(p.getVehicle().getType(),
                p.getVehicle().getDescription(), p.getVehicle().getPhotos()));

        //dto.setOwnerUser(p.getOwnerUser().getEmail());

        dto.setPhone(p.getPhone());
        dto.setPickUpCoord(coordToCoordDTO(p.getPickUpCoord()));
        List<CoordDTO> returnCoords = new ArrayList<>();
        for(Coord c: p.getReturnCoords()){
                CoordDTO cdto = new CoordDTO();
                cdto.setLng(c.getLng());
                cdto.setLat(c.getLat());
                returnCoords.add(cdto);
        }
        dto.setReturnCoords(returnCoords);
        return dto;
    }


    private List<PostDTO> postsToPostsDTO(List<Post> posts) {
        List<PostDTO> list = new ArrayList<>();
        for(Post p: posts) {
            list.add(postToPostDTO(p));
        }
        return list;
    }

    private CoordDTO coordToCoordDTO(Coord c){
        CoordDTO dto = new CoordDTO();
        dto.setLat(c.getLat());
        dto.setLng(c.getLat());
        return dto;
    }

    private Coord coordDTOToCoord(CoordDTO dto){
        Coord c = new Coord();
        c.setLat(dto.getLat());
        c.setLng(dto.getLng());
        return c;
    }

    private Post fromDTO(PostDTO dto){
        Post post = new Post();
        post.setVehicle(fromDTO(dto.getVehicle()));
        post.setCostPerDay(dto.getCostPerDay());
        //post.setOwnerUser(dto.getOwnerUser());
        post.setPhone(dto.getPhone());
        post.setId(dto.getId());
        post.setPickUpCoord(coordDTOToCoord(dto.getPickUpCoord()));

        List<Coord> cs = new ArrayList<>();
        for(CoordDTO cdto: dto.getReturnCoords()){
            cs.add(coordDTOToCoord(cdto));
        }
        post.setReturnCoords(cs);
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

    private Vehicle fromDTO(VehicleDTO dto){
        Vehicle vehicle = new Vehicle();
//        vehicle.setOwner(this.getUserService().findById(dto.getOwner().getEmail()));
        vehicle.setType(dto.getType());
        vehicle.setCapacity(dto.getCapacity());
        vehicle.setDescription(dto.getDescription());
        for(String p:dto.getPhotos())
            vehicle.getPhotos().add(p);
        vehicle.setId(dto.getId());
        return vehicle;
    }

}
