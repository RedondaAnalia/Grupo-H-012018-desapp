package tests.persistence;

import builders.UserBuilder;
import static org.junit.Assert.*;

import model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import persistence.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml",
        "/META-INF/spring-services-context.xml" })
public class HibernateTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        userService.save(UserBuilder.anUser().build());
        assertEquals(1, userService.retriveAll().size());
    }

    @Test
    public void testDelete() {
        User user = UserBuilder.anUser().build();
        userService.save(user);
        userService.delete(user);
        assertEquals(0, userService.retriveAll().size());
    }

    /*
    @Test
    public void testUpdate() {
        User user = UserBuilder.anUser().withNameAndSurname("M","G").build();
        userService.save(user);
        userService.update();
        assertEquals(, );
    }
    */

    @After
    public void cleanup() {
        this.userService.deleteAll();
    }
}
