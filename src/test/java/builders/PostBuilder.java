package builders;

import model.Coord;
import model.Post;
import model.User;
import model.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostBuilder {

    private Vehicle vehicle = VehicleBuilder.aVehicle().build();
    private User ownerUser = UserBuilder.anUser().build();
    private Coord pickUpCoord = null;
    private ArrayList<Coord> returnCoords = new ArrayList<Coord>();
    private LocalDateTime sinceDate;
    private LocalDateTime UntilDate;
    private double costPerHour;

    public static PostBuilder aPost(){
        return new PostBuilder();
    }

    public Post build(){
        return new Post(this.vehicle, this.ownerUser, this.pickUpCoord,
                this.returnCoords, this.sinceDate, this.UntilDate, this.costPerHour);
    }

    public PostBuilder withUntilDate(LocalDateTime UntilDate){
        this.UntilDate = UntilDate;
        return this;
    }

    public PostBuilder withSinceDate(LocalDateTime sinceDate){
        this.sinceDate = sinceDate;
        return this;
    }

    public PostBuilder withUserDisabled(){
        this.ownerUser = UserBuilder.anUser().buildUserDisabled();
        return this;
    }

    public PostBuilder whitCostPerHour(double cost){
        this.costPerHour = cost;
        return this;
    }
}
