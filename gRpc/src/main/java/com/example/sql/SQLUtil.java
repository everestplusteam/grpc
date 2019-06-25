package com.example.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SQLUtil {
	private   String driver;
    private  String url;
    private  String dbName;
    private  String password;
    Connection conn;
    Statement  sta;
    PreparedStatement prepare;
    
    public SQLUtil()
    {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/mysql";
        this.dbName = "root";
        this.password = "zqq123!@#";
    }
    
    public Connection getConnection()throws Exception
    {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, dbName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public void closeConn()
    {
        try {
            if(this.conn!=null)
            {
                this.conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
