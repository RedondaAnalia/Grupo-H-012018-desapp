package persistence.services;
import model.Post;
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
   /*
        this.getRepository().save(MiniPostBuilder.aMiniPost().build());

        this.getRepository().save(MiniPostBuilder.aMiniPost().
                withVehicle(MiniVehicleBuilder.aVehicle().build()).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.MIN).build());
*/
    }
}
