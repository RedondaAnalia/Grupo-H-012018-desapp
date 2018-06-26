package persistence.repositories;

import model.Reservation;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
    public List<Reservation> findByOwnerUser(final String pattern){

        return (List<Reservation>) this.getHibernateTemplate().execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(Reservation.class, "reservation").
                    setFetchMode("post", FetchMode.JOIN)
                    .setFetchMode("user", FetchMode.JOIN).
                    createAlias("post.ownerUser", "ownerUser")
                    .createAlias("reservation.statusReservation", "status");
            criteria.add(Restrictions.like("ownerUser.email", pattern));
                    criteria.add(Restrictions.eq("status.status","Pending"));
            return criteria.list();
        });
    }
}
