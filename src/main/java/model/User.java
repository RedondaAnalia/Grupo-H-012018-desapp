package model;

public class User {
	private String CUIL;
	private String name;
	private String surname;
	private String address;
	private String email;
	private double credit;
	
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
	
	public double addCredit(double creditToAdd){
		return credit+=creditToAdd;
	}
	
	public double debitCredit(double creditToDebit){
		return credit-=creditToDebit;
	}	

}
