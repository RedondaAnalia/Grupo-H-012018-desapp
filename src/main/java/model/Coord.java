package model;

import java.util.NoSuchElementException;

/**
 * This class builds valid coordinates in the geographic system.
 * @author usuario
 *
 */
public class Coord extends Entity{

	private double lat;
	private double lng;

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
			throw new NoSuchElementException("Coordenada inexistente");
		}
	}

	public Coord(){}
	
	/*
	 * Getters and Setters.
	 */
	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
}
