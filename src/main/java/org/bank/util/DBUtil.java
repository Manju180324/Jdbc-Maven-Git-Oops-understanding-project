/*
App.java (Controller Layer)
Takes user input
Displays output
Calls service methods
Does NOT contain business rules
Does NOT contain SQL
 */
package org.bank.util;

import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String url = "jdbc:postgresql://localhost:5432/banking-system-pdb-v1";
    private static final String user = "postgres";
    private static final String password = "your_postgres_password";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
