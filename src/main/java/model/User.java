package model;

import java.util.ArrayList;


import model.interfaces.IUserState;

public class User {
	private String CUIL;
	private String name;
	private String surname;
	private String address;
	private String email;
	private double credit;
	private IUserState state;
	private ArrayList<Integer> scores;
	
	
	/*
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
		this.state= new UserEnabled();
		this.scores= new ArrayList<Integer>();
	}
	
	/*
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
	

	/*
	 * Functions
	 */
	
	//Add credit parameter to the User.
	public double addCredit(double creditToAdd){
		return credit+=creditToAdd;
	}
	
	//Debit credit parameter to the User.
	public double debitCredit(double creditToDebit){
		return credit-=creditToDebit;
	}	
	
	//Disable the user to make some transactions.
	private User disableUser(){
		this.state= new UserDisabled();
		return this;
	}
	
	//Try to make a post
	public void post(){
		state.post();
	}
	
	//Try to rent a vehicle
	public void rent(){
		state.rent();
	}
	
	//Save the score obtained in one transaction
	public void saveScore(Integer score){
		if(score>maxScore()){
			throw new RuntimeException("El puntaje es incorrecto"); 
			}else{
			scores.add(score);
			}
	}
	
	//AVG of the scores or new User default
	public double reputation(){
		if(isNewUser()){
			return 3.0;
		}else{
			return this.avgOfScores();
		}
	}
	
	
	/*
	 * Private Methods
	 */
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

	//Sets the maximum score that an user can receive
	private double maxScore(){
		return 5.0;
	}
}
