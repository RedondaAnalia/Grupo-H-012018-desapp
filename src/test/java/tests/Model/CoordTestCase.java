package tests.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import builders.CoordBuilder;
import model.Coord;

public class CoordTestCase {

	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLatIsWrong() {
		Coord coord= CoordBuilder.anCoord().withLat(-190).build(); 
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLngIsWrong() {
		Coord coord= CoordBuilder.anCoord().withLng(-190).build(); 
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLatAndLngIsWrong() {
		Coord coord= CoordBuilder.anCoord().withLat(-190).withLng(-190).build(); 
	}
	
	@Test
	public void shouldBuildAnCoodWhenLatAndLngAreTheMaximumValues(){
		Coord coord= CoordBuilder.anCoord().withLat(90).withLng(180).build();
	}
	
	@Test
	public void shouldBuildAnCoordWhenLatAndLngAreTheMinimumValues(){
		Coord coord= CoordBuilder.anCoord().withLat(-90).withLng(-180).build();
	}

}
