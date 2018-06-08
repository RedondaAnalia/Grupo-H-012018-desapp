package persistence.services;
import builders.PostBuilder;
import builders.UserBuilder;
import model.Post;
import model.User;
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
    public void initialize() {

     //   this.getRepository().save(PostBuilder.aPost().build());
/*
        this.getRepository().save(PostBuilder.aPost().
                withVehicle(VehicleBuilder.aVehicle().
                        withPhoto("https://vignette.wikia.nocookie.net/s__/images/c/c2/SPN_0043_%28Impala%29.jpg/revision/latest?cb=20140305225245&path-prefix=supernatural%2Fde").
                        build()).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).
                withCostPerDay(10).build());
*/
    //    this.getRepository().save(MiniPostBuilder.aMiniPost().withUserName("maru_maru@yahoo.com.ar").build());
/*
        this.getRepository().save(MiniPostBuilder.aMiniPost().
                withVehicle(MiniVehicleBuilder.aVehicle().build()).
                withSinceDate(LocalDateTime.now()).
                withUserName("maricruz@yahoo.com.ar").
                withUntilDate(LocalDateTime.now().plusDays(3L)).build());
*/
        User feli = UserBuilder.anUser().withCUIL("1").
                withEmail("felipe.gil@gmail.com").
                withNameAndSurname("Felipe", "Gil").build();

        this.getRepository().save(
                PostBuilder.aPost().
                        withownerUser(feli).
                        withCostPerDay(100).
                        build());
    }
}
