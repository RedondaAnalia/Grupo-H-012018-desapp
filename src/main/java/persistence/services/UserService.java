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
    public List<User> filterUser(final String pattern){
        return this.getRepository().filterUser(pattern);
    }

    @Transactional
    public void initialize() {
       // this.getRepository().save(
        //        UserBuilder.anUser().withCUIL("1").build());
    }
}
