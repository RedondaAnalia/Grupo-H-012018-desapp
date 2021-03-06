package model;

import model.exceptions.NoCreditException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="account")
public class Account extends Entity{

    private double credit;

    public Account(){
        this.credit=0;
    }

    //Add credit parameter to the User.
    public double addCredit(double creditToAdd){
        return this.credit+=creditToAdd;
    }

    //Try to debit credit
    public double debitCredit(double creditToDebit){
    	if (credit<creditToDebit){
    		throw new NoCreditException();
    	} 
        return this.credit-=creditToDebit;
    }

    public double getCredit() {
        return this.credit;
    }
}
