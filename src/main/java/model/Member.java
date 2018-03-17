package model;

public class Member {
	private double credit;
	
	public Member(){
		credit=40.0;
	}
	
	public double addCredit(double toCredit){
		credit+=toCredit;
		return credit;
	}
	
	public double getCredit(){
		return credit;
	}

}
