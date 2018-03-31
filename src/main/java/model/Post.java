package model;

import model.exceptions.TimeOutOfRangeException;
import model.exceptions.UserBlockedException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Post {

	private Vehicle vehicle;
	private User ownerUser;
	private Coord pickUpCoord;
	private ArrayList<Coord> returnCoords;
	private LocalDateTime sinceDate;
	private LocalDateTime UntilDate;
	private double costPerDay;
	private int phone;

	
	public Post(){}

	public Post(Vehicle vehicle, User user, Coord pickUpCoord, ArrayList<Coord> returnCoords,
                LocalDateTime sinceDate, LocalDateTime UntilDate, double costPerDay){

		long days= sinceDate.until(UntilDate, ChronoUnit.DAYS);

		if( days<1 || days>5 ){
			throw new TimeOutOfRangeException();
		}
		if(!user.isEnabled()){
		    throw new UserBlockedException();
        }
//		if(pickUpCoord==null || returnCoords.isEmpty()){
//			throw new NoCoordsEnoughException();
//		}
		
		this.vehicle=vehicle;
		this.ownerUser=user;
		this.pickUpCoord=pickUpCoord;
		this.returnCoords=returnCoords;
        this.sinceDate = sinceDate;
		this.UntilDate = UntilDate;
		//this.phone = phone;
        this.costPerDay = costPerDay;
	}
	
	public double getCostPerHour(){
		return this.costPerDay;
	}

	public User getUser() {
		return this.ownerUser;
	}

	public Coord getPickUpCoord() {
		return this.pickUpCoord;
	}

	public ArrayList<Coord> getReturnCoords() {
		return this.returnCoords;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}
}
