package builders;

import model.User;
import model.Vehicle;
import model.enums.VehicleType;

public class VehicleBuilder {

	VehicleType type= VehicleType.AUTO;
	int capacity=0;
	String description= "A default description";
	User owner= UserBuilder.anUser().build();
	
	public static VehicleBuilder aVehicle(){
		return new VehicleBuilder();
	}
	
	public Vehicle build(){
		return new Vehicle(owner,type,capacity,description);
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
}
