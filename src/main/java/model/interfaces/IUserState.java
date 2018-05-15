package model.interfaces;

import model.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class IUserState extends Entity{
	
	
	public Post post(Vehicle vehicle, User user, Coord pickUpCoord,ArrayList<Coord>returnCoords,
			  LocalDateTime sinceDate, LocalDateTime untilDate, double costPerHour){
		return null;
	};

	public Reservation rent(Post post, LocalDateTime reservationSinceDate,
					 LocalDateTime reservationUntilDate, User tenantUser)
	{
		return null;
	}

	public boolean isEnabled() {
		return false;
	}
}
