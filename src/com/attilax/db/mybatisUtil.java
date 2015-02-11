package com.attilax.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.ibatis.datasource.DataSourceException;
import org.apache.ibatis.session.SqlSession;
import com.attilax.core;
import com.attilax.retry;
import com.attilax.retryO7;
import com.foksda.mass.retryRzt;

import cn.freeteam.util.MybatisSessionFactory;


/**
 * Service的父类，提供通用方法
 * @author freeteam
 * 2011-6-14
 */
public class mybatisUtil{
	/**
	 * 初始化指定变量
	 * @param objs 需要初始化变量列表
	 */
	protected  void initMapper(String...objs){
//		try {
//			for(String obj:objs){
//				if(obj!=null && obj.trim().length()>0){
//					//获取变量的get方法
//					Method method=getClass().getMethod("get"+varMethodName(obj), null);
//					//调用get方法，判断对象是否已初始化
//					if (method.invoke(this, null)==null) {
//						//获取get方法返回类型，即变量类型,然后动态创建对象
//						initMapperObj(method.getReturnType().toString(), obj);
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	/**
	 * 初始化指定变量initMapperObj
	 * @param className 要创建对象的类名
	 * @param varName 变量名
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("all")
	protected  void initMapperObj(String className,String varName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		//获取名全名
		Class clazz=Class.forName(className.replaceFirst("class ", "").replaceFirst("interface ", ""));
		//创建对象
		SqlSession session =getSessionO7();//;
		Object obj=session.getMapper(clazz);
		//获取变量的set方法
//		Method method=getClass().getMethod("set"+varMethodName(varName), clazz);
//		//调用set方法
//		method.invoke(this, obj);
	}
	/**
	@author attilax 老哇的爪子
		@since  o78 i4148$
	
	 * @return
	 */
	private SqlSession getSessionO7() {
		// attilax 老哇的爪子 i4148 o78
		retryRzt rzt = new retryRzt();
		return new retryO7<SqlSession>(5, rzt) {

			@Override public Boolean item(Object t) throws Exception {
				// attilax 老哇的爪子 下午11:49:37 2014年6月9日
				final SqlSession sess = MybatisSessionFactory.getSession();
				this.setResetObj(sess);
			 
					List li = sess.selectList("test4conn");
 				 //	core.ex4test();

				 	if (li.size() > 0) {
						this.setResult(sess);
						return true;
					 }
					 
//				  
//				} catch (DataSourceException e) {
//					reset(sess);
//				
//				}
				return false;

			}
			// return null;

			

			@Override public void reset(final Object sessObj) {
				 
					core.log("---o79: conn is close ,now startclose session..");
					SqlSession sess=(SqlSession)sessObj;
					 sess.close();
					 
			}



		 

			

		}.$O69();
	}
	
}
