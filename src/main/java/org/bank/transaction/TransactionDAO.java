package org.bank.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionDAO {
    void save(Connection conn, Transaction transaction) throws SQLException;
}