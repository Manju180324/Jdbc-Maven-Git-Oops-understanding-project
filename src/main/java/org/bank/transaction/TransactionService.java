package org.bank.transaction;

import java.sql.*;
import java.time.LocalDateTime;

import org.bank.account.Account;
import org.bank.account.AccountDAO;
import org.bank.util.DBUtil;

public class TransactionService {

    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    public TransactionService(AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }

    public void transfer(int fromId, int ToId, double amount) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // sql changes are saved only when commit() is executed

            Account sender = accountDAO.getAccountById(conn, fromId);
            Account receiver = accountDAO.getAccountById(conn, ToId);

            if (sender == null || receiver == null) {
                throw new RuntimeException("Account not found");
            }

            if (sender.getBalance() < amount) {
                throw new RuntimeException("Insufficient Balance");
            }

            accountDAO.withdraw(conn, fromId, amount);
            accountDAO.deposit(conn, ToId, amount);

            Transaction transaction = new Transaction(
                    fromId,
                    ToId,
                    amount,
                    LocalDateTime.now()
            );
            transactionDAO.save(conn, transaction);

            conn.commit();
            System.out.println("Transfer Successful");

        } catch (Exception e) {

            // Rollback if anything fails
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction Rolled Back!");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }

            // Rethrow the original exception so caller sees it
            throw new RuntimeException(e.getMessage());

        } finally {

            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset default
                    conn.close();             // Always close
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
}