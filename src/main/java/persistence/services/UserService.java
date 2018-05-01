package persistence.services;

import builders.UserBuilder;
import model.User;
import org.springframework.transaction.annotation.Transactional;
import persistence.repositories.Initializable;
import persistence.repositories.UserRepository;
import service.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserService extends GenericService<User> implements Initializable{

    private UserRepository repository;

    public UserRepository getRepository() {
        return this.repository;
    }
    public void setRepository(final UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<UserDTO> filterUser(final String pattern){
        List<User> users = this.getRepository().filterUser(pattern);
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void initialize() {
        this.getRepository().save(UserBuilder.anUser().withCUIL("1").withNameAndSurname("Analia", "Redonda").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("2").withNameAndSurname("Ines", "Sosa").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("3").withNameAndSurname("Paula", "Schab").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("4").withNameAndSurname("Marty", "MacFly").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("5").withNameAndSurname("Tyrion", "Lannister").build());
        this.getRepository().save(UserBuilder.anUser().withCUIL("6").withNameAndSurname("Daenerys", "Targaryen").build());

    }

    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setAddress(user.getAddress());
        dto.setCUIL(user.getCUIL());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }
}
