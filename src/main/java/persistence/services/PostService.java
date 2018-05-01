package persistence.services;

import model.minis.MiniPost;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.PostRepository;

import java.util.List;

public class PostService extends GenericService<MiniPost> {

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
}
