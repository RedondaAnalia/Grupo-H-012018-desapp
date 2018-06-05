package persistence.repositories;

import model.minis.MiniPost;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MiniPostRepository
        extends HibernateGenericDAO<MiniPost>
        implements GenericRepository<MiniPost> {

    @Override
    protected Class<MiniPost> getDomainClass() {
        return MiniPost.class;
    }

    public List<MiniPost> allMiniPost() {
        return this.findAll();
    }
}
