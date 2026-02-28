/*
 POJO - Plain Old Java Object
 POJO is a simple java class used to store data
 No special rules
 No framework dependency
 Just variables + getters/setters
 its like a Database row
*/
package org.bank.account;
/*
here Account is a POJO class
objects created for Account are called as POJO objects.
e.g., Account acc  = new Account() here acc is the reference to the POJO object created
*/
public class Account {

    private int user_id;
    private String name;
    private double balance;

    public Account(){}

    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    public Account(int user_id, String name, double balance){
        this.user_id = user_id;
        this.name = name;
        this.balance = balance;
    }

    public int getId(){
        return user_id;
    }
    public void setId(int user_id){
        this.user_id = user_id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

}
