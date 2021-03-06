package tests.model;

import static org.junit.Assert.*;
import org.junit.Test;

import builders.CoordBuilder;
import model.Coord;

public class CoordTestCase {

	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLatIsWrong() {
		CoordBuilder.anCoord().withLat(-190).build();
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLngIsWrong() {
		CoordBuilder.anCoord().withLng(-190).build();
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLatAndLngIsWrong() {
		CoordBuilder.anCoord().withLat(-190).withLng(-190).build();
	}
	
	@Test
	public void shouldBuildAnCoodWhenLatIsTheMaximumValue(){
		Coord coord= CoordBuilder.anCoord().withLat(90).withLng(180).build();
		assertTrue(90.0==coord.getLat());
	}

	@Test
	public void shouldBuildAnCoodWhenLngIsTheMaximumValue(){
		Coord coord= CoordBuilder.anCoord().withLat(90).withLng(180).build();
		assertTrue(180.0==coord.getLng());
	}

	@Test
	public void shouldBuildAnCoordWhenLatIsTheMinimumValue(){
		Coord coord= CoordBuilder.anCoord().withLat(-90).withLng(-180).build();
		assertTrue(-90.0==coord.getLat());
	}

	@Test
	public void shouldBuildAnCoordWhenLngAreTheMinimumValue(){
		Coord coord= CoordBuilder.anCoord().withLat(-90).withLng(-180).build();
		assertTrue(-180.0==coord.getLng());
	}

}
