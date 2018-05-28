package tests.aspect;

import builders.UserBuilder;
import model.exceptions.SystemOutPrintLnException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "*/META-INF/aspects-context.xml" })
public class AspectTestCase {


    @Test
            //(expected=SystemOutPrintLnException.class)
    public void shouldNotBeSystemOutPrintLn(){
        UserBuilder.anUser()
                .withNameAndSurname("Thanos", "Thanos")
                .build();
    }

}
