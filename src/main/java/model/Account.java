package model;


public class Account {

    private double credit;

    public Account(){
        this.credit=0;
    }

    //Add credit parameter to the User.
    public double addCredit(double creditToAdd){
        return this.credit+=creditToAdd;
    }

    //Debit credit parameter to the User.
    public double debitCredit(double creditToDebit){
        return this.credit-=creditToDebit;
    }

    public double getCredit() {
        return this.credit;
    }
}
