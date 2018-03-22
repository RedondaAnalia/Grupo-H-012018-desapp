package builders;

import model.User;

public class UserBuilder {
	private String CUIL= "aCUIL";
	private String name= "aName";
	private String surname= "aSurname";
	private String adress= "anAdress";
	private String email= "aEmail";
	private double credit=0;
	
	public static UserBuilder anUser(){
		return new UserBuilder();
	}
	
	public User build(){
		User user= new User(CUIL,name,surname,adress,email);
		user.addCredit(credit);
		return user;
	}
	
	public UserBuilder withNameAndSurname(String name, String surname){
		this.name=name;
		this.surname=surname;
		return this;
	}
	
	public UserBuilder withEmail(String email){
		this.email=email;
		return this;
	}
	
	public UserBuilder withCredit(double credit){
		this.credit=credit;
		return this;
	}

}
