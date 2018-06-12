package persistence.services;
import builders.PostBuilder;
import builders.UserBuilder;
import builders.VehicleBuilder;
import model.Post;
import model.User;
import model.Vehicle;
import model.enums.VehicleType;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.Initializable;
import persistence.repositories.PostRepository;
import java.util.List;

public class PostService extends GenericService<Post> implements Initializable{

    private PostRepository repository;

    public PostRepository getRepository() {
        return this.repository;
    }
    public void setRepository(final PostRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public List<Post> allPost(){
        return this.getRepository().allPost();
    }



    @Transactional
    public int sizePost() {
        return this.getRepository().count();
    }

    @Transactional
    public List<Post> postByType(String type) {
        return this.getRepository().postByType(type);
    }

    @Transactional
    public void initialize() {

        //==== Creacion de usuario ====//


        User feli = UserBuilder.anUser().withCUIL("1").
                withEmail("felipe.gil@gmail.com").
                withNameAndSurname("Felipe", "Gil").build();


        //==== Creacion de vehiculos ====//

        Vehicle v1 = VehicleBuilder.aVehicle().withCapacity(3).withType(VehicleType.CAMIONETA).
        withPhoto("https://cdn.wallpaperjam.com/content/images/9e/fd/9efd172a5aea57e895acf503100b148d67c709a6.jpg").
        withOwner(feli).build();

        Vehicle v2 = VehicleBuilder.aVehicle().withType(VehicleType.AUTO).
        withPhoto("https://vignette.wikia.nocookie.net/s__/images/c/c2/SPN_0043_%28Impala%29.jpg/revision/latest?cb=20140305225245&path-prefix=supernatural%2Fde").
        withCapacity(5).withOwner(feli).build();

        Vehicle v3 = VehicleBuilder.aVehicle().withType(VehicleType.CAMIONETA).
        withPhoto("https://www.actualidadmotor.com/wp-content/uploads/2014/07/historia-camaro-bumblebee-transformers-4-5-e1406538012544.jpg").
        withPhoto("https://patiodeautos.com/img/noticias/bumblebee-proxima.jpg").
        withPhoto("http://img.europapress.es/fotoweb/fotonoticia_20160606130445_1280.jpg").
        withCapacity(2).withOwner(feli).build();

        //==== Creacion de post ====//

        this.getRepository().save(
                PostBuilder.aPost().
                        withownerUser(feli).
                        withVehicle(v1).
                        withCostPerDay(100).
                        withLocation("Quilmes").
                        build());

        this.getRepository().save(
                PostBuilder.aPost().
                        withownerUser(feli).
                        withVehicle(v2).
                        withCostPerDay(100).
                        withLocation("Quilmes").
                        build());

        this.getRepository().save(
                PostBuilder.aPost().
                        withownerUser(feli).
                        withVehicle(v3).
                        withCostPerDay(100).
                        withLocation("Quilmes").
                        build());

        this.getRepository().save(
                PostBuilder.aPost().
                        withownerUser(feli).
                        withVehicle(v3).
                        withCostPerDay(200).
                        withLocation("Wilde").
                        build());

        this.getRepository().save(
                PostBuilder.aPost().
                        withownerUser(feli).
                        withVehicle(v2).
                        withCostPerDay(150).
                        withLocation("Don Bosco").
                        build());

    }

    public Post postById(int id) {
        return this.getRepository().findById(id);
    }
}
