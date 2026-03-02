/*
Executes SQL queries
Talks to database
Converts ResultSet → Account object
Updates balance
Does NOT decide business logic
 */
package org.bank.account;

import java.sql.Connection;
import java.sql.SQLException;

public interface AccountDAO {
    void createAccount(Account account);
    Account findById(int user_id);
    void updateBalance(int user_id, double newBalance) throws SQLException;
    void withdraw(Connection conn, int accountId, double amount) throws SQLException;
    void deposit(Connection conn, int accountId, double amount) throws SQLException;
    Account getAccountById(Connection conn, int accountId) throws SQLException;
}
