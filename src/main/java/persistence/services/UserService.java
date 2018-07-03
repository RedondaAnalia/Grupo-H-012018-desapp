package persistence.services;

import builders.UserBuilder;
import model.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.Initializable;
import persistence.repositories.UserRepository;

import java.util.List;

public class UserService extends GenericService<User> implements Initializable{

    private UserRepository repository;

    public UserRepository getRepository() {
        return this.repository;
    }
    public void setRepository(final UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User filterUser(final String pattern){
        User user = this.getRepository().filterUser(pattern);
        return user;
    }

    @Transactional
    public void initialize() {
        /*
        this.getRepository().save(UserBuilder.anUser().withCUIL("1").withEmail("a.redonda89@gmail.com").withNameAndSurname("Analia", "Redonda").
                withCUIL("27298009051").withAddress("Av. Corrientes 1241, C1043AAM CABA, Argentina").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("2").withEmail("niñita.violenta@gmail.com").withNameAndSurname("Ines", "Sosa").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("3").withEmail("ultraK@gmail.com").withNameAndSurname("Paula", "Schab").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("4").withEmail("gallina@gmail.com").withNameAndSurname("Marty", "MacFly").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("5").withEmail("genio_total@gmail.com").withNameAndSurname("Tyrion", "Lannister").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("6").withEmail("mother_of_dragons@gmail.com").withNameAndSurname("Daenerys", "Targaryen").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("7").withEmail("espinarys@gmail.com").withNameAndSurname("Espi", "Noso").build());
*/
        this.getRepository().save(UserBuilder.anUser().withCUIL("27298009051").
                withEmail("gil.maricruz@gmail.com").
                withNameAndSurname("Maricruz", "Gil").
                withAddress("Av. Hipólito Yrigoyen 3671, C1208ABD CABA, Argentina").build());
    }

    @Transactional
    public int countUsers() {
        return this.getRepository().count();
    }

    @Transactional
    public User addCreditToUser(double credit, String mail) {
        User user = this.getRepository().filterUser(mail);
        user.addCredit(credit);
        this.getRepository().update(user);
        return user;
    }

    @Transactional
    public User debitCreditToUser(double credit, String mail) {
        User user = this.getRepository().filterUser(mail);
        user.debitCredit(credit);
        this.getRepository().update(user);
        return user;
    }

    public List<User> findAll() {
        return this.getRepository().findAll();
    }
}
