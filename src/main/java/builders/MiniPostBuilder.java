package builders;

import model.minis.MiniPost;
import model.minis.MiniVehicle;
import java.time.LocalDateTime;

public class MiniPostBuilder {

    private MiniVehicle vehicle = MiniVehicleBuilder.aVehicle().build();
    private LocalDateTime sinceDate= LocalDateTime.now();
    private LocalDateTime untilDate=(LocalDateTime.now().plusDays(3L));


    public static MiniPostBuilder aMiniPost(){
        return new MiniPostBuilder();
    }

    public MiniPost build(){
        return new MiniPost(this.vehicle, this.sinceDate, this.untilDate);
    }

    public MiniPostBuilder withUntilDate(LocalDateTime UntilDate){
        this.untilDate = UntilDate;
        return this;
    }

    public MiniPostBuilder withSinceDate(LocalDateTime sinceDate){
        this.sinceDate = sinceDate;
        return this;
    }

    public MiniPostBuilder withVehicle(MiniVehicle vehicle){
        this.vehicle = vehicle;
        return this;
    }

}
