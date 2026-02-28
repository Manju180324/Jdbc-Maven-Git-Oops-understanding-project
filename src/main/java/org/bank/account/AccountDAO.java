/*
Executes SQL queries
Talks to database
Converts ResultSet → Account object
Updates balance
Does NOT decide business logic
 */
package org.bank.account;

public interface AccountDAO {
    void createAccount(Account account);
    Account findById(int user_id);
    void updateBalance(int user_id, double newBalance);
}
