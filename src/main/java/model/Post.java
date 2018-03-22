package model;

import java.util.ArrayList;

public class Post {

	Vehicle vehicle;
	User postUser;
	User rentUser;
	Coord pickUpCoord;
	ArrayList<Coord> returnCoords;
	//double costoPorDia;
	//double costoPorHora;
	//int phone
	//hora y dias disponibles para alquilar
	
	
	public Post(Vehicle vehicle, User user, Coord pickUpCoord, ArrayList<Coord> returnCoords){
		this.vehicle=vehicle;
		this.postUser=user;
		this.pickUpCoord=pickUpCoord;
		this.returnCoords=returnCoords;
		this.rentUser= null;  
	}
	
	
}
