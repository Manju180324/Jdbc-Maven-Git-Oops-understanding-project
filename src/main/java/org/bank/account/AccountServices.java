/*
Applies business rules
Validates input (amount > 0, account exists, sufficient balance)
Decides what should happen
Throws exceptions if rules fail
Calls DAO only after validation passes
 */
package org.bank.account;

import java.sql.SQLException;

public class AccountServices {
    private AccountDAO accountDAO;

    public AccountServices(){
        this.accountDAO = new AccountDAOImplimentation();
    }

    public void createAccount(String name, double balance){
        if (balance < 0){
            throw new RuntimeException("Balance cannot be negative");
        }

        // POJO object
        Account account = new Account(name, balance);

        accountDAO.createAccount(account); // this is implemented in AccountDAOImplimentation
    }

    public Account viewAccount(int user_id){
        Account account = accountDAO.findById(user_id);
        if (account == null){
            throw new RuntimeException("Account not found");
        }
        return account;
    }

    public void deposit(int user_id, double amount) {
        try {
            if (amount <= 0) {
                throw new RuntimeException("Invalid amount");
            }

            Account account = viewAccount(user_id);
            double newBalance = account.getBalance() + amount;

            accountDAO.updateBalance(user_id, newBalance);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void withdraw(int user_id, double amount) {
        try {
            if (amount <= 0) {
                throw new RuntimeException("Invalid amount");
            }

            Account account = viewAccount(user_id);
            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            double newBalance = account.getBalance() - amount;
            accountDAO.updateBalance(user_id, newBalance);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
