package model;

import java.io.File;
import java.util.ArrayList;

import model.enums.VehicleType;

public class Vehicle {
	
	private User owner;
	private VehicleType type;
	private int capacity;
	private String description;
//	private ArrayList<File> photos; TODO: Ver si es el tipo correcto, y OJO que es OPCIONAL!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	/*
	 * Constructors
	 */
	
	public Vehicle(User owner, VehicleType type, int capacity, String description){
//		this.photos=new ArrayList<File>();
		this.type= type;
		this.owner=owner;
		this.capacity=capacity;
		this.description=description;
	}

	/*
	 * Getters y Setters
	 */
	
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

//	public ArrayList<File> getPhotos() {
//		return photos;
//	}
	
	
	/*
	 * Public Methods
	 */
//	public ArrayList<File> addPhoto(File newPhoto){
//		photos.add(newPhoto);
//		return photos;
//	}
}
