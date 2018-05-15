package persistence.services;

import model.Rental;
import persistence.repositories.Initializable;
import persistence.repositories.RentalRepository;

public class RentalService  extends GenericService<Rental> implements Initializable {

    private RentalRepository rentalRepository;

    public RentalRepository getRentalRepository() {
        return rentalRepository;
    }

    public void setRentalRepository(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void initialize() {

    }
}
