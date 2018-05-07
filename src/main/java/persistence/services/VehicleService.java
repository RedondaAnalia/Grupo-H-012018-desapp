package persistence.services;


import builders.UserBuilder;
import builders.VehicleBuilder;
import model.User;
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

        User tyrion = UserBuilder.anUser().withCUIL("5").
                withEmail("genio_total@gmail.com").withNameAndSurname("Tyrion", "Lannister").build();
        User dany = UserBuilder.anUser().withCUIL("6").
                withEmail("mother_of_dragons@gmail.com").withNameAndSurname("Daenerys", "Targaryen").build();

        this.getRepository().save(VehicleBuilder.aVehicle().withCapacity(3).withOwner(dany).build());
        this.getRepository().save(VehicleBuilder.aVehicle().withCapacity(5).withOwner(tyrion).build());
    }

    @Transactional
    public Vehicle findVehicleById(int id) {
        return this.getRepository().findById(id);
    }
}
