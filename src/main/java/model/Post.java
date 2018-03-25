package model;

import model.exceptions.TimeOutOfRangeException;
import model.exceptions.UserBlockedException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Post {

	private Vehicle vehicle;
	private User ownerUser;
	private User tenantUser;
	private Coord pickUpCoord;
	private ArrayList<Coord> returnCoords;
	private LocalDateTime available;
	private LocalDateTime maximumReturnTime;
	//double costoPorDia;
	//double costoPorHora;
	//int phone
	//hora y dias disponibles para alquilar
	
	
	public Post(){};

	public Post(Vehicle vehicle, User user, Coord pickUpCoord, ArrayList<Coord> returnCoords){
		
		long hours= available.until(maximumReturnTime,ChronoUnit.HOURS);
		long days= available.until(maximumReturnTime, ChronoUnit.DAYS);
		
		if( hours<1 || days>=5 ){
			throw new TimeOutOfRangeException();
		}

		if(!user.isEnabled()){
		    throw new UserBlockedException();
        }
		
		this.vehicle=vehicle;
		this.ownerUser=user;
		this.pickUpCoord=pickUpCoord;
		this.returnCoords=returnCoords;
		this.tenantUser= null;  
	}
	
	
}
