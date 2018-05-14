package builders;

import model.User;
import model.Vehicle;
import model.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class VehicleBuilder {

	private VehicleType type= VehicleType.AUTO;
	private int capacity=1;
	private String description= "A default description, a little bit longer because is not allowed a shorter description";
	private User owner= UserBuilder.anUser().build();
	private List<String> photos= new ArrayList<String>();
	
	public static VehicleBuilder aVehicle(){
		return new VehicleBuilder();
	}
	
	public Vehicle build(){
		Vehicle vehicle = new Vehicle(owner,type,capacity,description);
		vehicle.setPhotos(this.photos);
		return vehicle;
	}

	public VehicleBuilder withOwner(User owner){
		this.owner=owner;
		return this;
	}
	
	public VehicleBuilder withType(VehicleType type){
		this.type=type;
		return this;
	}
	
	public VehicleBuilder withCapacity(int capacity){
		this.capacity=capacity;
		return this;
	}
	
	public VehicleBuilder withDescription(String description){
		this.description=description;
		return this;
	}

	public VehicleBuilder withPhoto(String urlPhoto){
		this.photos.add(urlPhoto);
		return this;
	}
}
