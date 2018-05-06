package persistence.repositories;

import model.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository
        extends HibernateGenericDAO<Vehicle>
        implements GenericRepository<Vehicle> {


    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }
}
