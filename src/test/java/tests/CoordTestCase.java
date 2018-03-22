package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Coord;

public class CoordTestCase {

	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLatIsWrong() {
		Coord coord= new Coord(-190,-100); 
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLngIsWrong() {
		Coord coord= new Coord(-89,-190); 
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenLatAndLngIsWrong() {
		Coord coord= new Coord(-190,-190); 
	}
	
	@Test
	public void shouldBuildAnCoodWhenLatAndLngAreTheMaximumValues(){
		Coord coord= new Coord(90,180);
	}
	
	@Test
	public void shouldBuildAnCoordWhenLatAndLngAreTheMinimumValues(){
		Coord coord= new Coord(-90,-180);
	}

}
