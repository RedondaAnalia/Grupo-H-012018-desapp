package persistence.repositories;

import model.Rental;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalRepository
        extends HibernateGenericDAO<Rental>
        implements GenericRepository<Rental> {

    @Override
    protected Class<Rental> getDomainClass() {
        return Rental.class;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Rental> findByOwnerUser(final String pattern){

        return (List<Rental>) this.getHibernateTemplate().execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(Rental.class, "rental").
                    createAlias("rental.reservation", "reservation").
                    createAlias("reservation.post", "post").
                    createAlias("post.ownerUser", "ownerUser")
                    .createAlias("rental.state", "status");
            criteria.add(Restrictions.eq("ownerUser.email", pattern));
            criteria.add(Restrictions.or(Restrictions.eq("status.status","PendingRental")));
            criteria.add(Restrictions.or(Restrictions.eq("status.status","PendingReturnRental")));
            return criteria.list();
        });
    }
}
