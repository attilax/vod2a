/**
 * @author attilax 老哇的爪子
	@since  o9n 4_9_i$
 */
package com.attilax.dsm;
import com.attilax.core;
import com.attilax.MDA.CantFindConditionalExcept;
import com.attilax.MDA.CantFindConverterExcept;
import com.attilax.MDA.IAdapter;
import com.attilax.MDA.MdaUtil;
import com.attilax.Stream.Mapx;
import com.attilax.anno.Conditional;
import com.attilax.anno.displayType;
import com.attilax.coll.ListX;
import com.attilax.count.CountRelt;
import com.attilax.count.GroupByDate;
import com.attilax.count.OrderByTimeType;
import com.attilax.count.reduce;
import com.attilax.db.BaseHibernateDAO;
import com.attilax.db.BaseHibernateDAO2;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.pagging.PagingUtil;
import com.attilax.persistence.Hbx;
import com.attilax.persistence.HbxX;
import com.attilax.persistence.NoColunnAnnoEx;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.focusx.elmt.GvMaterial;
import com.focusx.playRec.GvPlayRecord;
import com.focusx.pojo.Equipment;
import com.google.inject.Inject;
 import static  com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import  com.attilax.lang.ref.None;
import org.directwebremoting.annotations.RemoteMethod;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;
/**
 * baseCountSvs
 * @author  attilax 老哇的爪子
 *@since  o9n 4_9_i$
 */
public class BaseSqlSvs extends  BaseSvs{
	//  attilax 老哇的爪子 4_9_i   o9n   
	
	public static void main(String[] args)   {
		Field idFld = null;
//		try {
//			idFld = refx.getIdFld_EXO9o(Equipment.class);
//		} catch (cantFindMatchFieldException e) {
//			//  attilax 老哇的爪子 a_i_42   o9q   
//			e.printStackTrace();
//		}
		System.out.println(idFld);
		 Map<Object, Object> map = Mapx.$().add("materialId", 3).toMap();
		 map = Mapx.$().add("groupid", "117").toMap();
		  
		List li=ListX.$().add(map).toLi();
		BaseSqlSvs baseSqlSvs = IocX.getBean(BaseSqlSvs.class);
		li= baseSqlSvs.setSaveObjClass(GvPlayRecord.class) .findByPropertyss(map, GvPlayRecord.class);
		 core.print_wzFmt(li);
		 System.out.println("--");
		 
	}
	
	public BaseSqlSvs setSaveObjClass(Class<?> saveObjClass) {
		this.saveObjClass = saveObjClass;return this;
	}
	
	 public static final ThreadLocal<Integer> threadLocal_rowsCount = new ThreadLocal<Integer>();
	  /**with page spt..
	   *  @author attilax 老哇的爪子
		 * @since o7e m3l$
		 *  desc,cate,cate2,crtDt,plytim
		 * @param propertyName
		 * @param value
		 * @return */
	    @RemoteMethod @SuppressWarnings("all") 
		 
		public List findByPropertyss(Map QueryPropertyssMap,Class<?> cls) {
	    	String sql2 = getSql(QueryPropertyssMap, cls);
	    	// select CONVERT(varchar(10), play_time ,23 )  as playTime, sum(timLen)  as timLen,material_id as material_id,equipment_id as equipment_id from playcount_day group by CONVERT(varchar(10), play_time ,23 ) ,material_id,equipment_id
	    	filex.save_SF(sql2, "c:\\sql\\"+filex.getUUidName()+".txt");
	    	core.log("---sqlOa91:"+sql2);
			SQLQuery SQLQueryx = hbx.getSession().createSQLQuery(sql2);
			//SQLQueryx.addEntity(GvPlayRecord.class) ;		
			SQLQueryx.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List list=	SQLQueryx.list();
		
			int size = list.size();
			threadLocal_rowsCount.set(size);
			Object page = QueryPropertyssMap.get("page_page");
			List  list_sub = PagingUtil.getList(list,
					QueryPropertyssMap.get("pagesize"), page);
			
			setCountRelt(list_sub);
			
			return list_sub;

		}
	    /**
	@author attilax 老哇的爪子
		@since  o9q 10_0_53   
	
	 * @param list
	 */
	    @SuppressWarnings("all")
	private void setCountRelt( List<Map> list) {
		// attilax 老哇的爪子  10_0_53   o9q 
		List<String> CountReltFldsList=getCountReltFldsList();
		for (Map map : list) {
			for (String fldName : CountReltFldsList) {
				Class subObjType = getFldClass(fldName);
				Field subObjidFld = null;
				try {
					subObjidFld = refx.getIdFld_EXO9o(subObjType);
				} catch (cantFindMatchFieldException e) {
					//  attilax 老哇的爪子 10_58_h   o9q   
					//e.printStackTrace();
					core.warn(e);
				}
				String colName = null;
				 
					colName = Hbx.getColunmnName_RE(subObjidFld);
					Object idVal = map.get(colName);
					if(idVal!=null)
					{
						Object rltObj = getSession().get(subObjType,(Integer) idVal);
						map.put(fldName, rltObj );
					}
				 
			
			}
		}
		
		
	}
 
		/**
		@author attilax 老哇的爪子
			@since  o9q 10_q_b   
		
		 * @param fldName
		 * @return
		 */
		private Class getFldClass(String fldName) {
			// attilax 老哇的爪子  10_q_b   o9q 
		Field fld = null;
		try {
			fld = refx.getField(fldName, this.saveObjClass);
		} catch (cantFindMatchFieldException e) {
			//  attilax 老哇的爪子 10_45_47   o9q   
			e.printStackTrace();
		}
			return fld.getType();
			
		}
		/**
		@author attilax 老哇的爪子
			@since  o9q 10_o_h   
		
		 * @return
		 */
		private List getCountReltFldsList() {
			// attilax 老哇的爪子  10_o_h   o9q 
			
			return refx.getFlds( this.saveObjClass, CountRelt.class);
			
		}
		@SuppressWarnings("all")
	public String getSql(Map QueryPropertyssMap, Class<?> cls) {
	    //	java.
		this.saveObjClass=cls;
		// log.debug("finding GvMaterial instance with property: " +
		// propertyName + ", value: " + value);
		 core. log("---o720--");
		core.logMap(QueryPropertyssMap);
		filex.save_safe( core.toJsonStr(QueryPropertyssMap) , "c:\\json2.txt");
		String propertyName;
		Object value;
	 
		SqlAti sql=new SqlAti();
		sql.reqMap=QueryPropertyssMap;
		sql.cls=cls;
		sql.saveObjClass=sql.cls;
		Field[] flds =cls.getDeclaredFields();
		for (Field fld : flds) {
			try {

				String fldName = fld.getName();
				if (fldName.startsWith("playTime")) {
					 String s = "";
				}
				if (fldName.startsWith("playTime")) {
					 String s = "";
				}
			 
				////def except condit   k_y_q o8h  老哇的爪子  Attilax  
				if(filtProcess(fld,QueryPropertyssMap))
					continue;
				
				sql.addSelectItem(fld).addGroupByItem(fld).addWhereExp(fld);
				
			
					 
				 
				 

			} catch (Exception e) {
				core.log(e);
			}

			 
		}
//	c.addOrder(Order.desc("materialId"));

		String sql2 = sql.toSql();
		return sql2;
	}
	    

@Inject
Hbx hbx=new HbxX(); 
		
	    
	    /**
		 * def filtr  todox o8h should fld>>MapToUiFld>> then judge..
		@author attilax 老哇的爪子
			@since  o8h k_z_n   
		
		 * @param fld
		 * @param queryPropertyssMap 5
		 * @return
		 */
	@SuppressWarnings("all") private boolean filtProcess(Field fld, Map queryPropertyssMap) {
		// attilax 老哇的爪子 k_z_n o8h
		if (fld.getName().equals("playTime"))
			System.out.println("4dbg");
		//if condition fld 
		List<String> uiFldList = getUiFldList(fld);
		for (String uiFld : uiFldList) {
			if (isUiFldHasVal(uiFld, queryPropertyssMap, fld)) { return false; }
		}
		reduce rds =	fld.getAnnotation(reduce.class);
		if(rds!=null)
			return false;
		if(isOrderbyTimetypeFld(fld))
			return false;
		if(isGroupByDate(fld)) return false;
		if(isCountReltFld(fld)) return false;
		return true; // jump/ filted / not to nextSetp process..

	}
		
	/**
		@author attilax 老哇的爪子
			@since  o9r m_44_f   
		
		 * @param fld
		 * @return
		 */
		private boolean isCountReltFld(Field fld) {
			// attilax 老哇的爪子  m_44_f   o9r 
			CountRelt a=fld.getAnnotation(CountRelt.class);
			return (a!=null);
			
		}

	/**
		@author attilax 老哇的爪子
			@since  o9p j_z_v   
		
		 * @param fld
		 * @return
		 */
		private boolean isGroupByDate(Field fld) {
			// attilax 老哇的爪子  j_z_v   o9p 
			GroupByDate anno=fld.getAnnotation(GroupByDate.class);
			if(anno!=null) return true;
			return false;
			
		}
	/**
		@author attilax 老哇的爪子
			@since  o9p j_m_0   
		
		 * @param fld
		 * @return
		 */
		private boolean isOrderbyTimetypeFld(Field fld) {
			// attilax 老哇的爪子  j_m_0   o9p 
			OrderByTimeType obtt=fld.getAnnotation(OrderByTimeType.class);
			if(obtt!=null) return true;
			return false;
			
		}
	/** @author attilax 老哇的爪子
	 * @since o9p 8_40_8	 * 
	 * @param uiFld
	 * @param queryPropertyssMap
	 * @return */
		private boolean isUiFldHasVal(String uiFld, Map queryPropertyssMap,Field fld) {
			// attilax 老哇的爪子  8_40_8   o9p 
			String uiFldName =uiFld;
//			if(uiFldName.equals("playtime"))
//				return false;
			if(uiFldName.equals("createTime"))
				System.out.println("xx");
		Object oriVal=queryPropertyssMap.get(uiFldName);
			//	Field fld=refx.getField(fldName, this.saveObjClass);
			if(refx.isIntType(fld))
			{
				
				if(oriVal==null || oriVal.toString().equals("") || oriVal.toString().equals("0") )
					return false;  //jump
			}
			if(refx.isStrType(fld))
			{
				if(oriVal==null || oriVal.toString().equals("") )
					return false;//jump
			}
			if(refx.isTimeType(fld))
			{
				if(oriVal==null || oriVal.toString().equals("")||  oriVal.toString().equals("0") )
					return false;//jump
			}
			return true;
			
		}
		/**
		@author attilax 老哇的爪子
			@since  o9p 8_v_m   
		
		 * @param fld
		 * @return
		 */
		private List getUiFldList(Field fld) {
			// attilax 老哇的爪子  8_v_m   o9p 
			List UiFldList=new ArrayList();
			try {
				Conditional cdt=	MdaUtil.getConditional(fld.getName(), this.saveObjClass);
				if(cdt==null)  return UiFldList;
				if(	cdt.displayType().equals(displayType.rang))
				{
					UiFldList.add(fld.getName()+"_"+cdt.rangStart() );
					UiFldList.add(fld.getName()+"_"+cdt.rangEnd() );
				}
				if(	cdt.displayType().equals(displayType.single))
				{
					UiFldList.add(fld.getName() );
				}
				return UiFldList;
			} catch (cantFindMatchFieldException e) {
				//  attilax 老哇的爪子 8_x_39   o9p   
				return  new ArrayList();
			}
		//	return  new ArrayList();
			
		}
		/**
		@author attilax 老哇的爪子
			@since  o7g Y6p$
		
		 * @param queryPropertyssMap
		 * @param fldName
		 * @return
		 */
		private boolean includeFldInfoPostMap(Map QueryPropertyssMap, String fldName) {
			// attilax 老哇的爪子  Y6p   o7g 
			if(QueryPropertyssMap.get(fldName) == null)
				if(QueryPropertyssMap.get(fldName+"_start")==null)
					if(QueryPropertyssMap.get(fldName+"_end")==null)
						return false;
		return true;
			
		}
		/**
		@author attilax 老哇的爪子
		\t@since  Jul 18, 2014 11:46:49 PM$
		
		 * @param c
		 * @param li
		 */
		private void addExpresss(Criteria c, List li) {
			// attilax 老哇的爪子  11:46:49 PM   Jul 18, 2014 
			 for (Object object : li) {
				SimpleExpression se=(SimpleExpression) object;
				c.add(se);
			}
			 
			
		}
		Class<?> saveObjClass ;
	
	
		
	
}

//  attilax 老哇的爪子