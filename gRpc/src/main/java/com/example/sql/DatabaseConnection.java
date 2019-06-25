package com.example.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseConnection {
	private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/mysql";
    private static String dbName = "root";
    private static String password = "zqq123!@#";
    private static Connection conn;
    private static Statement  stmt;
    private static PreparedStatement pstmt;
    
   
    public static Connection getConnection()throws Exception
    {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, dbName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void closeConn()
    {
        try {
            if(conn!=null)
            {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
