package persistence.repositories;

import model.Vehicle;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleRepository
        extends HibernateGenericDAO<Vehicle>
        implements GenericRepository<Vehicle> {


    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Vehicle> filterVehicleByUser(final String pattern) {

        return (List<Vehicle>) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<Vehicle> doInHibernate(final Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(Vehicle.class);
                criteria.add(Restrictions.like("owner.email", "%" + pattern + "%"));
                return (List<Vehicle>)criteria.list();
            }

        });
    }
}
