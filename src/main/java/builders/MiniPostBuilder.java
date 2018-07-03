package builders;

import model.Coord;
import model.Post;
import model.User;
import model.Vehicle;
import java.time.LocalDateTime;

public class MiniPostBuilder {

    private Vehicle vehicle = MiniVehicleBuilder.aVehicle().build();
    private LocalDateTime sinceDate= LocalDateTime.now();
    private LocalDateTime untilDate=(LocalDateTime.now().plusDays(3L));
    private double costPerDay;
    private String userName;
    private String location;


    public static MiniPostBuilder aMiniPost(){
        return new MiniPostBuilder();
    }

    public Post build(){
        Coord lc = new Coord(1,1);
        User u = new UserBuilder().withEmail(this.userName).build();
        return new Post(this.vehicle, u, new Coord(1,1),lc,
                this.sinceDate, this.untilDate, this.costPerDay, null);
    }
    public MiniPostBuilder withUserName(String name){
        this.userName = name;
        return this;
    }

    public MiniPostBuilder withUntilDate(LocalDateTime UntilDate){
        this.untilDate = UntilDate;
        return this;
    }

    public MiniPostBuilder withSinceDate(LocalDateTime sinceDate){
        this.sinceDate = sinceDate;
        return this;
    }

    public MiniPostBuilder withVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        return this;
    }

    public MiniPostBuilder withCostPerDay(double cost){
        this.costPerDay = cost;
        return this;
    }

    public MiniPostBuilder withLocation(String location){
        this.location=location;
        return this;
    }

}
