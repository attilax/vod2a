/**
 * @author attilax 老哇的爪子
	@since  2014-9-2 上午12:19:27$
 */
package com.attilax.db;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * @author  attilax 老哇的爪子
 *@since  2014-9-2 上午12:19:27$
 */
public class mybatisTO91 {
	//  attilax 老哇的爪子 上午12:19:27   2014-9-2   
	
	 @SuppressWarnings("all")
	public static void main(String[] args) throws IOException {
	       // TODO Auto-generated method stub
	     
	           String resource = "com/attilax/db/ibatiascfg.xml";
	           Reader reader;


	           reader = Resources.getResourceAsReader(resource);


	           SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
	                   .build(reader);
	           SqlSession sqlSession = sqlSessionFactory.openSession();
	       List li=   sqlSession.selectList("getLockTables", 9999999);
//	            StudentDao studentDao =sqlSession.getMapper(StudentDao.class);
//	            Student st = studentDao.getstudent(1);
            System.out.println( li.size());
	           
	           List li2=    sqlSession.selectList("findRecords"," select 1 as tit ");
	           System.out.println(core.toJsonStrO88(li2));
	           
	       }
}

//  attilax 老哇的爪子