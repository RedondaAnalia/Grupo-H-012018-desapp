package persistence.repositories;

import model.Post;
import model.enums.StatesPost;
import model.enums.VehicleType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Post> allPost(){

        return (List<Post>) this.getHibernateTemplate().execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(Post.class, "post");
            criteria.add(Restrictions.eq("post.postState", StatesPost.AVAILABLE));
            return criteria.list();
        });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Post> postByType(String type){

        return (List<Post>) this.getHibernateTemplate().execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(Post.class, "post").createAlias("post.vehicle", "vehicle");
            criteria.add(Restrictions.eq("vehicle.type", VehicleType.valueOf(type)));
            criteria.add(Restrictions.eq("post.postState", StatesPost.AVAILABLE));
            return criteria.list();
        });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Post> postByLocation(String location) {
        return (List<Post>) this.getHibernateTemplate().execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(Post.class, "post");
            criteria.add(Restrictions.eq("location", location));
            criteria.add(Restrictions.eq("post.postState", StatesPost.AVAILABLE));
            return criteria.list();
        });
    }
}
