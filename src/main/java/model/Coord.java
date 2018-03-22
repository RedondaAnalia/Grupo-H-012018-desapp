package model;

/**
 * This class builds valid coordinates in the geographic system.
 * @author usuario
 *
 */
public class Coord {

	double lat;
	double lng;

	/*
	 * Constructors.
	 */
	/**
	 * 
	 * @param lat= latitude
	 * @param lng=longitude
	 */
	public Coord(double lat, double lng){
		if (lat>=-90&& 90>=lat && lng>=-180 && 180>=lng){
			this.lat=lat;
			this.lng=lng;
		} else {
			throw new RuntimeException("Inexistent coord");
		}
	}
	
	/*
	 * Getters and Setters.
	 */
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}
