package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.exceptions.UserBlockedException;
import model.interfaces.IUserState;

public class UserDisabled implements IUserState{

	public Reservation rent(Post post, LocalDateTime reservationSinceDate,
					 LocalDateTime reservationUntilDate, User tenantUser) {
			throw new UserBlockedException();
	}

	public Post post(Vehicle vehicle, User user, Coord pickUpCoord, ArrayList<Coord> returnCoords,
					 LocalDateTime sinceDate, LocalDateTime untilDate, double costPerHour) {
		return null;
	}

	public boolean isEnabled(){
		return false;
	}

}
