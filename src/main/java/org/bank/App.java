/*
DAO = Cashier
Service = Bank Manager
App = Customer interaction desk

Customer says:
I want to withdraw $500.

Manager (Service) checks:
Do you have enough money?
Is amount valid?

Then tells cashier (DAO):
Proceed.
Cashier does NOT decide.
 */
package org.bank;

import org.bank.account.Account;
import org.bank.account.AccountServices;

import java.sql.SQLOutput;
import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        AccountServices service = new AccountServices();

        while(true){
            System.out.println("\n==== BANK MENU ====");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.next();

                    System.out.print("Enter initial balance: ");
                    double balance = sc.nextDouble();

                    service.createAccount(name, balance);
                    System.out.println("Account created successfully!");

                    break;

                case 2:
                    System.out.print("Enter account ID :");
                    int user_id = sc.nextInt();

                    Account account = service.viewAccount(user_id);

                    System.out.println("User Id"+account.getId());
                    System.out.println("Name"+account.getName());
                    System.out.println("Balance"+account.getBalance());

                    break;

                case 3:
                    System.out.print("Enter account ID: ");
                    user_id = sc.nextInt();

                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();

                    service.deposit(user_id, amount);
                    System.out.println("Deposit successful");

                    break;

                case 4:
                    System.out.print("Enter account ID: ");
                    user_id = sc.nextInt();

                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();

                    service.withdraw(user_id, amount);
                    System.out.println("Withdraw successful!");

                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
