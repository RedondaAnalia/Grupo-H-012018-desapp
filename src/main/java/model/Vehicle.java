package model;

import model.enums.VehicleType;
import model.exceptions.DescriptionTooLongException;
import model.exceptions.DescriptionTooShortException;
import model.exceptions.InvalidCapacityException;
import model.exceptions.NoVehicleTypeException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="vehicle")
public class Vehicle extends Entity{
	
	private User owner;
	private VehicleType type;
	private int capacity;
	private String description;
	//private List<String> photos;
	
	/*
	 * Constructors
	 */

	public Vehicle(){}

	public Vehicle(User owner, VehicleType type, int capacity, String description){
		//this.photos=new ArrayList<String>();
		if(type==null){
			throw new NoVehicleTypeException();
		}
		if(capacity==0){
			throw new InvalidCapacityException();
		}
		if(description.length()<=30){
			throw new DescriptionTooShortException();
		}
		if(description.length()>=200){
			throw new DescriptionTooLongException();
		}
		this.type= type;
		this.owner=owner;
		this.capacity=capacity;
		this.description=description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
/*
	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
*/
}
