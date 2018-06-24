package persistence.repositories;

import model.Reservation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository
        extends HibernateGenericDAO<Reservation>
        implements GenericRepository<Reservation> {

    @Override
    protected Class<Reservation> getDomainClass() {
        return Reservation.class;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Reservation> findByUser(final String pattern){

        return (List<Reservation>) this.getHibernateTemplate().execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(Reservation.class, "reservation").
                    createAlias("reservation.post", "post").
                    createAlias("post.ownerUser", "ownerUser");
            criteria.add(Restrictions.eq("ownerUser.email", pattern));
            return criteria.list();
        });
    }
}
