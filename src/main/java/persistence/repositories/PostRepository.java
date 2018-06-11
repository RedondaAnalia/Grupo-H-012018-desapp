package persistence.repositories;

import model.Post;
import model.enums.VehicleType;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

    public List<Post> allPost() {
        return this.findAll();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Post> postByType(VehicleType type){

        return (List<Post>) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<Post> doInHibernate(final Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(Post.class, "post").createAlias("post.vehicle", "vehicle");
                criteria.add(Restrictions.like("vehicle.type", "%" + type + "%"));
                return criteria.list();
            }

        });
    }
}
