package persistence.services;


import builders.VehicleBuilder;
import model.Vehicle;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.Initializable;
import persistence.repositories.VehicleRepository;

public class VehicleService extends GenericService<Vehicle> implements Initializable {

    private VehicleRepository repository;

    public VehicleRepository getRepository() {
        return repository;
    }

    public void setRepository(VehicleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void initialize() {
        this.getRepository().save(VehicleBuilder.aVehicle().withCapacity(3).build());
    }
}
