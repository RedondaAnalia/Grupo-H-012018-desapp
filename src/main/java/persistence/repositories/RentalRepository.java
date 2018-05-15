package persistence.repositories;

import model.Rental;
import org.springframework.stereotype.Repository;

@Repository
public class RentalRepository
        extends HibernateGenericDAO<Rental>
        implements GenericRepository<Rental> {

    @Override
    protected Class<Rental> getDomainClass() {
        return Rental.class;
    }
}
