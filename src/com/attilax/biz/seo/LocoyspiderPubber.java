package com.attilax.biz.seo;
import java.net.URLEncoder;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.SQLException;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import junit.framework.TestCase;  
import org.apache.commons.dbutils.DbUtils;  
import org.apache.commons.dbutils.QueryRunner;  
import org.apache.commons.dbutils.handlers.ArrayHandler;  
import org.apache.commons.dbutils.handlers.ArrayListHandler;  
import org.apache.commons.dbutils.handlers.BeanHandler;  
import org.apache.commons.dbutils.handlers.ColumnListHandler;  
import org.apache.commons.dbutils.handlers.MapHandler;  
import org.apache.commons.dbutils.handlers.MapListHandler;  
import org.apache.commons.dbutils.handlers.ScalarHandler; 

import com.attilax.core;
import com.attilax.io.filex;
public class LocoyspiderPubber {

	private String db;
	private String contextAppend="</html>";

	public LocoyspiderPubber(String db) {
		this.db=db;
		//org.sqlite.j
	}

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws getConnEx 
	 */
	public static void main(String[] args) throws getConnEx, SQLException {
		String db="c:\\SpiderResult.db3";
	//	String driver 
	 // new LocoyspiderPubber(db).pub();
		 System.out.println(URLEncoder.encode("."));
	 System.out.println(filex.fileNameEncode( "a|b") );
		
			System.out.println("--f");
	}

	private void pub() throws getConnEx, SQLException {
		Connection conn = getConnection();
		QueryRunner queryRunner = new QueryRunner(true);
		// queryRunner.query()
		// Map<String, Object> map = queryRunner.query(conn,
		// "select * from content ", new MapHandler());
		List<Map<String, Object>> list = queryRunner.query(conn,
				"select * from content", new MapListHandler());
		for (Map<String, Object> map : list) {
			String target2="xxx";
			try {
				if(map.get("标题")!=null)
				{
				  String Filex = map.get("标题").toString()+".htm";
				  Filex=filex.fileNameEncode(Filex);
				target2 = "c:\\locoyPubDir\\" + Filex;
				
				filex.save_SF(map.get("内容").toString()+this.contextAppend, target2);
				}
			}
			  catch (Exception e) {
				core.log(e);
				 core.log("errfile:"+target2);
			}
			
		}

	}

	private Connection getConnection() throws getConnEx  {
		 try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new getConnEx("getconnex"+core.getTrace(e));
		}
		    Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:"+this.db);
			} catch (SQLException e) {
				throw new getConnEx("getconnex"+core.getTrace(e));
			}
		return conn;
	}

}
