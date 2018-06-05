package tests.Architecture;

import aspects.ArchitectureCheck;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArchitectureTestCase {

    @Test
    public void shouldNotBeSystemOutPrintLn(){
        assertFalse(ArchitectureCheck.existsSystemOutPrintLnInModel("/src/main/java/model"));
    }

}