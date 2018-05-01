package model.minis;

import model.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name="miniPost")
public class MiniPost extends Entity {

    private MiniVehicle vehicle;
    private LocalDateTime sinceDate;
    private LocalDateTime UntilDate;

    public MiniPost(){
        super();
    }
}
