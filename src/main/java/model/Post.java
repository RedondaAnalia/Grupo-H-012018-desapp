package model;

import java.time.*;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.omg.DynamicAny.DynAnySeqHelper;

public class Post {

	Vehicle vehicle;
	User ownerUser;
	User tenantUser;
	Coord pickUpCoord;
	ArrayList<Coord> returnCoords;
	LocalDateTime available;
	LocalDateTime maximumReturnTime;
	//double costoPorDia;
	//double costoPorHora;
	//int phone
	//hora y dias disponibles para alquilar
	
	
	
//************************************************************************************************************************
//  ESTO NO ESTA TESTEADO, NI SIQUIERA SE SI FUNCIONA ASI. SOLO ESCRIBI EL CODIGO QUE ME PARECIO QUE IBA A FUNCIONARR!!!	
//************************************************************************************************************************  
	public Post(Vehicle vehicle, User user, Coord pickUpCoord, ArrayList<Coord> returnCoords){
		
		long hours= available.until(maximumReturnTime,ChronoUnit.HOURS);
		long days= available.until(maximumReturnTime, ChronoUnit.DAYS);
		
		if( hours<1 || days>=5 ){
			throw new RuntimeException("El tiempo minimo de alquiler es 1 hora y el maximo 5 dias.");
		}
		
		this.vehicle=vehicle;
		this.ownerUser=user;
		this.pickUpCoord=pickUpCoord;
		this.returnCoords=returnCoords;
		this.tenantUser= null;  
	}
	
	
}
