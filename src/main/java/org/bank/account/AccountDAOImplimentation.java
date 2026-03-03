/*
implementation of DAO
 */
package org.bank.account;

import org.bank.util.DBUtil;
import java.sql.*;

public class AccountDAOImplimentation implements AccountDAO{

    @Override
    public void createAccount(Account account){
        String sql = "insert into customers (name, balance) values (?, ?)";

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account.getName());
            ps.setDouble(2, account.getBalance());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Account findById(int user_id){
        String sql = "select * from customers where user_id = ?";
        Account account = null;
        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                account = new Account(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getDouble("balance")
                );  
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return account;
    }


    @Override
    public void updateBalance(int user_id, double newBalance){
        String sql = "update customers set balance = ? where user_id = ?";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDouble(1, newBalance);
            ps.setInt(2, user_id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(Connection conn, int accountId, double amount) throws SQLException{
        String sql = "update customers set balance = balance - ? where user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, amount);
        ps.setInt(2, accountId);
        ps.executeUpdate();
    }

    @Override
    public void deposit(Connection conn, int accountId, double amount) throws SQLException {
        String sql = "UPDATE customers SET balance = balance + ? WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, amount);
        ps.setInt(2, accountId);
        ps.executeUpdate();
    }

    @Override
    public Account getAccountById(Connection conn, int accountId) throws SQLException { // used for transaction purpose

        String sql = "SELECT * FROM customers WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getDouble("balance")
                );
            }
        }

        return null;
    }
}
