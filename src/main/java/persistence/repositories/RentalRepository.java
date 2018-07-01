package persistence.repositories;

import model.Rental;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
                    createAlias("post.ownerUser", "ownerUser");
              //      .createAlias("rental.state", "state");
            criteria.add(Restrictions.eq("ownerUser.email", pattern));

            //String myArray[] = {"PendingRental", "PendingReturnRental"};
            //criteria.add(Restrictions.in("state.status", myArray));
            return criteria.list();
        });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Rental> findByTenantUser(final String pattern){

        return (List<Rental>) this.getHibernateTemplate().execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(Rental.class, "rental").
                    createAlias("rental.reservation", "reservation").
                    createAlias("reservation.tenantUser", "tenantUser");
            criteria.add(Restrictions.eq("tenantUser.email", pattern));
            return criteria.list();
        });
    }

    public void confirmedRentalByOwner(int idRental) {
        Rental r = this.findById(idRental);
        r.getState().ownerUserConfirmated(r);
        this.merge(r);
    }

    public void confirmedRentalByTenant(int idRental) {
        Rental r = this.findById(idRental);
        r.getState().tenantUserConfirmated(r);
        this.merge(r);
    }

    public void confirmedReturnByTenant(int idRental, Integer score, String comment) {
        Rental r = this.findById(idRental);
        r.getState().tenantUserConfirmated(r, score, comment);
        this.merge(r);
    }

    public void confirmedReturnByOwner(int idRental, Integer score, String comment) {
        Rental r = this.findById(idRental);
        r.getState().ownerUserConfirmated(r, score, comment);
        this.merge(r);
    }

    public List<Rental> historicalMovementsByUser(String mail) {
        List<Rental> rentals = new ArrayList<>();
        rentals.addAll(this.findByOwnerUser(mail));
        rentals.addAll(this.findByTenantUser(mail));
        return rentals;
    }
}
