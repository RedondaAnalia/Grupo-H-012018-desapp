package builders;

import model.Coord;

public class CoordBuilder {
	
	private double lat= -34.60103330781445;
	private double lng= -58.38310718536377;
	
	public static CoordBuilder anCoord(){
		return new CoordBuilder();
	}
	
	public Coord build(){
		return new Coord(lat,lng);
	}
	
	public CoordBuilder withLat(double lat){
		this.lat=lat;
		return this;
	}
	
	public CoordBuilder withLng(double lng){
		this.lng=lng;
		return this;
	}

}
