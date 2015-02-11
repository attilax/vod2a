package com.attilax.seo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.attilax.core;
import com.attilax.biz.seo.getConnEx;

public class Dbx4Mysql {

	public Dbx4Mysql(String driver, String url, String user, String pwd) throws getConnEx {
		 getConnection(driver,url,user,pwd);
	}
	
	public Connection conn;
	private Connection getConnection(String driver, String url, String user, String pwd) throws getConnEx  {
		 try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new getConnEx("getconnex"+core.getTrace(e));
		}
		    
			try {
				conn = DriverManager.getConnection(url);
			} catch (SQLException e) {
				throw new getConnEx("getconnex"+core.getTrace(e));
			}
		return conn;
	}
	public ResultSet executeQuery(String sql) throws SQLException {
		Statement  stmt = conn.createStatement();
      ResultSet   rs = stmt.executeQuery(sql);
      return rs;
		
	}
	
	public int executeUpdate(String sql) throws SQLException {
		Statement  stmt = conn.createStatement();
     return  stmt.executeUpdate(sql);
    //  return rs;
		
	}
	
	

}
