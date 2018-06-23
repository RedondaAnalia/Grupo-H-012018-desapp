package persistence.repositories;

import model.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository
        extends HibernateGenericDAO<Reservation>
        implements GenericRepository<Reservation> {

    @Override
    protected Class<Reservation> getDomainClass() {
        return Reservation.class;
    }
}
