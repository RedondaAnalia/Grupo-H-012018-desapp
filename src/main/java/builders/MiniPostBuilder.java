package builders;

import model.minis.MiniPost;
import model.minis.MiniVehicle;
import java.time.LocalDateTime;

public class MiniPostBuilder {

    private MiniVehicle vehicle = MiniVehicleBuilder.aVehicle().build();
    private LocalDateTime sinceDate= LocalDateTime.now();
    private LocalDateTime untilDate=(LocalDateTime.now().plusDays(3L));
    private double costPerDay;


    public static MiniPostBuilder aMiniPost(){
        return new MiniPostBuilder();
    }

    public MiniPost build(){
        return new MiniPost(this.vehicle, this.sinceDate, this.untilDate, this.costPerDay);
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

    public MiniPostBuilder withCostPerDay(double cost){
        this.costPerDay = cost;
        return this;
    }

}
