package model.interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Coord;
import model.Post;
import model.User;
import model.Vehicle;

public interface IUserState {
	
	Post post(Vehicle vehicle, User user, Coord pickUpCoord,ArrayList<Coord>returnCoords,
			  LocalDateTime sinceDate, LocalDateTime untilDate, double costPerHour);
	void rent();//ver cual va a ser la penalizacion
	boolean isEnabled();
}
