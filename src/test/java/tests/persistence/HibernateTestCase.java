package tests.persistence;

import builders.UserBuilder;
import static org.junit.Assert.*;

import model.User;
import model.minis.MiniPost;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import persistence.services.PostService;
import persistence.services.UserService;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml",
        "/META-INF/spring-services-context.xml" })
public class HibernateTestCase {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;
/*
    @Test
    public void testSave() {
        this.userService.save(UserBuilder.anUser().build());
        assertEquals(1, userService.retriveAll().size());
    }

    @Test
    public void testDelete() {
        User user = UserBuilder.anUser().build();
        userService.save(user);
        userService.deleteAll();
        assertEquals(0, userService.retriveAll().size());
    }
*/
    @Test
    public void testFilterUser() {
        User brunoG = UserBuilder.anUser().withCUIL("200").withEmail("brunito@gmail.com").withNameAndSurname("BrunoBruno","G").build();
        User FelipeG = UserBuilder.anUser().withCUIL("300").withEmail("felipe@gmail.com").withNameAndSurname("FelipeFelipe","G").build();
        User teoC = UserBuilder.anUser().withCUIL("400").withEmail("teo@gmail.com").withNameAndSurname("TeoTeo","C").build();
        userService.save(brunoG);
        userService.save(FelipeG);
        userService.save(teoC);
        assertNotNull(userService.filterUser("felipe@gmail.com"));
    }

    @After
    public void cleanup() {
        this.userService.deleteAll();
    }
}
