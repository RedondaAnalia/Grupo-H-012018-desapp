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

        User espi = UserBuilder.anUser().withCUIL("7").
                withEmail("espinarys@gmail.com").withNameAndSurname("Espi", "Noso").build();

        this.getRepository().save(VehicleBuilder.aVehicle().withCapacity(3).
                withPhoto("https://cdn.wallpaperjam.com/content/images/9e/fd/9efd172a5aea57e895acf503100b148d67c709a6.jpg").
                withOwner(dany).build());

        this.getRepository().save(VehicleBuilder.aVehicle().
                withPhoto("https://vignette.wikia.nocookie.net/s__/images/c/c2/SPN_0043_%28Impala%29.jpg/revision/latest?cb=20140305225245&path-prefix=supernatural%2Fde").
                withCapacity(5).withOwner(tyrion).build());

        this.getRepository().save(VehicleBuilder.aVehicle().
                withPhoto("https://www.actualidadmotor.com/wp-content/uploads/2014/07/historia-camaro-bumblebee-transformers-4-5-e1406538012544.jpg").
                withPhoto("https://patiodeautos.com/img/noticias/bumblebee-proxima.jpg").
                withPhoto("http://img.europapress.es/fotoweb/fotonoticia_20160606130445_1280.jpg").
                withCapacity(2).withOwner(espi).build());
    }

    @Transactional
    public Vehicle findVehicleById(int id) {
        return this.getRepository().findById(id);
    }
}
