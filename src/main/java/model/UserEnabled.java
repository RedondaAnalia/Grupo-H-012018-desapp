package model;

import java.util.ArrayList;

import model.interfaces.IUserState;

public class UserEnabled implements IUserState{

	public void rent() {
			
	}

	public Post post(Vehicle vehicle, User user, Coord pickUpCoord, ArrayList<Coord> returnCoords) {
		return new Post(vehicle,user,pickUpCoord,returnCoords);
	}

}
