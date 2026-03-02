package org.bank.transaction;

import java.sql.*;

public class TransactionDAOImplimentation implements TransactionDAO{
    @Override
    public void save(Connection conn, Transaction transaction){
        String sql = "insert into transactions (from_account, to_account, amount, transac_date) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, transaction.getFromAccount());
            ps.setInt(2, transaction.getToAccount());
            ps.setDouble(3, transaction.getAmount());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(transaction.getTransactionDate()));

            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
