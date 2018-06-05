package persistence.repositories;

import model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository
        extends HibernateGenericDAO<Post>
        implements GenericRepository<Post> {

    @Override
    protected Class<Post> getDomainClass() {
        return Post.class;
    }

    public List<Post> allPost() {
        return this.findAll();
    }
}
