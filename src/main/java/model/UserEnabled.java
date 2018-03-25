package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.interfaces.IUserState;

public class UserEnabled implements IUserState{

	public void rent() {
			
	}

	public Post post(Vehicle vehicle, User user, Coord pickUpCoord, ArrayList<Coord> returnCoords,
					 LocalDateTime sinceDate, LocalDateTime untilDate, double costPerHour) {
		return new Post(vehicle,user,pickUpCoord,returnCoords, sinceDate, untilDate,costPerHour);
	}

	public boolean isEnabled(){
		return true;
	}

}
