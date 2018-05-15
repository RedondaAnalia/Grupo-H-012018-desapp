package persistence.services;

import builders.MiniPostBuilder;
import builders.MiniVehicleBuilder;
import model.minis.MiniPost;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.Initializable;
import persistence.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PostService extends GenericService<MiniPost> implements Initializable{

    private PostRepository repository;

    public PostRepository getRepository() {
        return this.repository;
    }
    public void setRepository(final PostRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public List<MiniPost> allMiniPost(){
        return this.getRepository().allMiniPost();
    }

    @Transactional
    public void initialize() {
        this.getRepository().save(MiniPostBuilder.aMiniPost().build());

        this.getRepository().save(MiniPostBuilder.aMiniPost().
                withVehicle(MiniVehicleBuilder.aVehicle().build()).
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.MIN).build());

    }
}
