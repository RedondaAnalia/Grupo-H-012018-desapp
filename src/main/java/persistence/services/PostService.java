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

        User feli = UserBuilder.anUser().withCUIL("1").
                withEmail("felipe.gil@gmail.com").
                withNameAndSurname("Felipe", "Gil").build();

        this.getRepository().save(
                PostBuilder.aPost().
                        withownerUser(feli).
                        withCostPerDay(100).
                        withLocation("Quilmes").
                        build());
    }

    @Transactional
    public int sizePost() {
        return this.getRepository().count();
    }
}
