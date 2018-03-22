package model;

import java.util.ArrayList;

import model.interfaces.IUserState;

	/***
	 **This class sets an User in the system. At the moment this can:
	 *  - Build an user. This requires: CUIL, name, surname, address and email
	 *  - Getters y setters.
	 *  - Add/Debit Credit.
	 *  - Delegate the ability to post or rent to the state .
	 *  - Save scores, check the current status. 
	 *  - Calculate his own reputation.
	 */


	//TODO: Ver en que caso se vuelve a habilitar el usuario.

public class User {
	
	private String CUIL;
	private String name;
	private String surname;
	private String address;
	private String email;
	private double credit;
	private IUserState status;
	private ArrayList<Integer> scores;
	
	
	/**
	 * Constructors
	 */
	
	public User(){};
	
	public User(String CUIL, String name, String surname, String address, String email){
		this.CUIL=CUIL;
		this.name=name;
		this.surname=surname;
		this.address=address;
		this.email=email;
		this.credit= 0;
		this.status= new UserEnabled();
		this.scores= new ArrayList<Integer>();
	}
	
	/**
	 * Getters y setters
	 */

	public String getCUIL() {
		return CUIL;
	}

	public void setCUIL(String cUIL) {
		CUIL = cUIL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getEmail() {
		return email;
	}
	
	public double getCredit(double credit) {
		return credit;
	}
	

	/**
	 * Public Methods.
	 */
	
	//Add credit parameter to the User.
	public double addCredit(double creditToAdd){
		return credit+=creditToAdd;
	}
	
	//Debit credit parameter to the User.
	public double debitCredit(double creditToDebit){
		return credit-=creditToDebit;
	}	
	
	//Try to make a post
	public void post(Vehicle vehicle, Coord pickUpCoord,ArrayList<Coord> returnCoords){
		status.post(vehicle,this, pickUpCoord,returnCoords);
	}
	
	//Try to rent a vehicle
	public void rent(){
		status.rent();
	}
	
	//Save the score obtained in one transaction and check the current status.
	public void processScore(Integer score){
		saveScore(score);
		checkUserStatus();
	}
	
	//Return the AVG of the scores or new User default
	public double reputation(){
		if(isNewUser()){
			return 3.0;
		}else{
			return this.avgOfScores();
		}
	}
	
	//Return true if the user is enabled.
	public boolean isEnabled(){
		return status.getClass()==UserEnabled.class;
	}
	
	

	/**
	 * Private Methods
	 */
	
	//Return true if the score is between the established limits.
	private boolean isCorrectScore(Integer score){
		return 0<=score && score<=maxScore();
	}
	
	//Disable the user if the reputation is lower than the minimum.
	private void checkUserStatus(){
		if (reputation()<minScoreEnabling()){
			disableUser();
		}
	}
	
	//Disable the user to make some transactions.
	private void disableUser(){
		this.status= new UserDisabled();
	}
	
	//Return the minimum score allowed
	private double minScoreEnabling(){
		return 3.0;
	}
	
	//Sets the maximum score that an user can receive
	private double maxScore(){
		return 5.0;
	}	
	
	private boolean isNewUser() {
		return scores.isEmpty();
	}

	private double avgOfScores(){
		int sum=0;
		for(Integer i:scores){
			sum+=i;
		}
		return (sum/scores.size());
	}
	
	//Save the score obtained in one transaction if it is within the correct range
	private void saveScore(Integer score){
		if(!isCorrectScore(score)){
			throw new RuntimeException("El puntaje es incorrecto"); 
		 }else{
			scores.add(score);
		  }
	}
}
