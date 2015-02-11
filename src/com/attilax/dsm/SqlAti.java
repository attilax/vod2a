/**
 * @author attilax 老哇的爪子
	@since  o9p 5_48_p$
 */
package com.attilax.dsm;

import com.attilax.ClosureNoExcpt;
import com.attilax.core;
import com.attilax.MDA.CantFindConditionalExcept;
import com.attilax.MDA.CantFindConverterExcept;
import com.attilax.MDA.IAdapter;
import com.attilax.MDA.MdaUtil;
import com.attilax.Stream.Mapx;
import com.attilax.anno.Conditional;
import com.attilax.coll.ListX;
import com.attilax.collection.CollX;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.listUtil;
import com.attilax.count.CountRelt;
import com.attilax.count.CountX;
import com.attilax.count.GroupBy;
import com.attilax.count.GroupByDate;
import com.attilax.count.GroupByFld;
import com.attilax.count.Table4count;
import com.attilax.count.TimeType;
import com.attilax.count.reduce;
import com.attilax.ioc.IocX;
import com.attilax.persistence.Hbx;
import com.attilax.persistence.NoColunnAnnoEx;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.attilax.text.strUtil;
import com.focusx.playRec.GvPlayRecord;
import static com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import javax.persistence.OrderBy;
import org.apache.ecs.xhtml.fieldset;
import  com.attilax.lang.ref.None;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
/** @author attilax 老哇的爪子
 * @since o9p 5_48_p$ */
public class SqlAti {

	/**
	//  attilax 老哇的爪子 6_e_55   o9q   
	 * @return the cls
	 */
	public Class<?> getCls() {
		return cls;
	}
	/**
	//  attilax 老哇的爪子 6_e_55   o9q   
	 * @param cls the cls to set
	 */
	public SqlAti setCls(Class<?> cls) {
		this.cls = cls;
		return this;
	}
	/**
	//  attilax 老哇的爪子 6_e_55   o9q   
	 * @return the reqMap
	 */
	public Map getReqMap() {
		return reqMap;
	}
	/**
	//  attilax 老哇的爪子 6_e_55   o9q   
	 * @param reqMap the reqMap to set
	 */
	public SqlAti setReqMap(Map reqMap) {
		this.reqMap = reqMap;return this;
	}
	/**
	//  attilax 老哇的爪子 6_e_55   o9q   
	 * @return the saveObjClass
	 */
	public Class<?> getSaveObjClass() {
		return saveObjClass;
	}
	/**
	//  attilax 老哇的爪子 6_e_55   o9q   
	 * @param saveObjClass the saveObjClass to set
	 */
	public void setSaveObjClass(Class<?> saveObjClass) {
		this.saveObjClass = saveObjClass;
	}
	private List SelectItemList = new ArrayList();
	String orderbyitemStr;
	public static ThreadLocal<Class<?>> cls_thrdLoc=new ThreadLocal<Class<?>>();
	public Class<?> cls;
	public Map reqMap;
	private List GroupByItemList = new ArrayList();
	private List WhereExpList = new ArrayList();
	public Class<?> saveObjClass;

	public static void main(String[] args) throws cantFindMatchFieldException {
		// Field fld=refx.getField("materialId", GvPlayRecord.class);
		// Conditional cdt=MdaUtil.getConditional(fld.getName(),
		// GvPlayRecord.class);
		// System.out.println(cdt);

		o9o();
	}
	private static void o9o() {
		//.
		// .add("playTime_end",
		// "2014-09-26")
		Map reqMap = Mapx.$().add("timeType", "d"). add("materialId", "1").add("groupid", "2").add("playTime_start", "2014-09-20").toMap();
		// SqlAti sql=new SqlAti();
		// IocX.getBean(SqlAti.class);
		BaseSqlSvs bss = new BaseSqlSvs();

		String sql = bss.getSql(reqMap, GvPlayRecord.class);

		System.out.println(sql);
	}
	/** @author attilax 老哇的爪子
	 * @since o9p 5_52_k
	 * 
	 * @param fld
	 * @return */
	public SqlAti addSelectItem(Field fld) {
		// attilax 老哇的爪子 5_52_k o9p
		String colName = CountX.getColumnName( fld.getName(),this.cls);
		if (colName.equals("playTime")) System.out.println("4dbg");
		GroupBy grpby = fld.getAnnotation(GroupBy.class);
		if (grpby != null) this.SelectItemList.add(colName + " as " + fld.getName());
		GroupByDate gbd = fld.getAnnotation(GroupByDate.class);
		orderbyitemStr = getGroupbyitemStr(colName);
		if (gbd != null) this.SelectItemList.add(orderbyitemStr + " as " + fld.getName());
		if (isReduceFld(fld)) {
			reduce rds = fld.getAnnotation(reduce.class);

			String reduceExps = rds.reduceExp();
			String[] a=reduceExps.split(",");
			for (String exp : a) {
				if(!exp.contains(" as "))
					this.SelectItemList.add(exp + " as " + fld.getName());
				else
					this.SelectItemList.add(exp  );
				
				
			}
			
		}
		
		//
	 
		CountRelt rlt = fld.getAnnotation(CountRelt.class);
		if(rlt!=null)
		{
			String reltSubobjIdColName;
			try {
				refx.class_4getAnno_inThrdLoc.set(this.cls);
				reltSubobjIdColName = getRreltSubobjIdColName(fld);
				this.SelectItemList.add(reltSubobjIdColName + " as " + reltSubobjIdColName);
			} catch (NoColunnAnnoEx e) {
				//  attilax 老哇的爪子 m_54_48   o9r   
				e.printStackTrace();
			}
			
		}
	

		return this;

	}
	/**
	@author attilax 老哇的爪子
		@since  o9r m_8_1   
	
	 * @param fld
	 * @return
	 * @throws NoColunnAnnoEx 
	 */
	private String getRreltSubobjIdColName(Field fld) throws NoColunnAnnoEx {
		// attilax 老哇的爪子  m_8_1   o9r 
		Class subObjType=fld.getType();
		Field subobjFld=refx.getIdFld_RE(subObjType);
	    
		return Hbx.getColunmnName_RE(subobjFld);
		
	}
	/** @author attilax 老哇的爪子
	 * @since o9p j_4_44
	 * 
	 * @param fld
	 * @return */
	private boolean isReduceFld(Field fld) {
		// attilax 老哇的爪子 j_4_44 o9p
		reduce rds = fld.getAnnotation(reduce.class);
		if (rds != null) return true;
		return false;

	}
	// attilax 老哇的爪子 5_48_p o9p

	/** @author attilax 老哇的爪子
	 * @since o9p 6_z_48
	 * 
	 * @param fld
	 * @return */
	private String getGroupbyitemStr(String  colName) {
		// attilax 老哇的爪子 6_z_48 o9p
		String timetype = getTimetype();
		if(timetype==null)
			timetype="d";
		if (timetype.equals("d")) return " CONVERT(varchar(10), " + colName + " ,23 ) ";
		if (timetype.equals("m")) return " CONVERT(varchar(7), " + colName + " ,23 ) ";
		if (timetype.equals("y")) return " CONVERT(varchar(4), " + colName + " ,23 ) ";
		return null;

	}

	/** @author attilax 老哇的爪子
	 * @since o9p 6_37_d
	 * 
	 * @return */
	private String getTimetype() {
		// attilax 老哇的爪子 6_37_d o9p
		Field TimetypeFld = null;
		Field[] flds = cls.getDeclaredFields();
		for (Field fld : flds) {
			TimeType grpby = fld.getAnnotation(TimeType.class);
			if (grpby != null) {
				TimetypeFld = fld;
				break;
			}
		}
		return (String) this.reqMap.get(TimetypeFld.getName());

	}

	/** @author attilax 老哇的爪子
	 * @since o9p 5_52_49
	 * 
	 * @param fld
	 * @return */
	public SqlAti addGroupByItem(Field fld) {
		// attilax 老哇的爪子 5_52_49 o9p
		String colName = CountX.getColumnName( fld.getName(),this.cls);
		GroupBy grpby = fld.getAnnotation(GroupBy.class);
		if (grpby != null) this.GroupByItemList.add(colName);
	//	if (orderbyitemStr == null) orderbyitemStr = getOorderbyitemStr(fld);
		GroupByDate gbd = fld.getAnnotation(GroupByDate.class);

		if (gbd != null) this.GroupByItemList.add(orderbyitemStr);
		
		CountRelt rlt = fld.getAnnotation(CountRelt.class);
		if(rlt!=null)
		{
			String reltSubobjIdColName;
			try {
				reltSubobjIdColName = getRreltSubobjIdColName(fld);
				this.GroupByItemList.add(reltSubobjIdColName  );
			} catch (NoColunnAnnoEx e) {
				//  attilax 老哇的爪子 m_54_59   o9r   
				e.printStackTrace();
			}
			
		}
		return this;

	}

	/** @author attilax 老哇的爪子
	 * @since o9p 5_53_0
	 * 
	 * @param fld */
	@SuppressWarnings("all") public SqlAti addWhereExp(Field fld) {
		// attilax 老哇的爪子 5_53_0 o9p
		String colName = CountX.getColumnName( fld.getName(),this.cls);
		if (fld.getName().equals("playTime")) System.out.println("4dbg");
		Conditional cdt = fld.getAnnotation(Conditional.class);
		if (cdt == null) return this;

		if (cdt.adptr() != None.class) // cstm mode

		{
			List exprsLi = fld2critAdapt(reqMap, fld, cdt);
			WhereExpList.addAll(exprsLi);
			return this;
		}  
//		if(refx.isIdsType(fld, reqMap))
//		{
//			
//			return this;
//		}
		
		// def mode common mode ::: fld>=value fld_op_val

			List exprsLi = getExprs(fld, cdt.op(), this.reqMap);
			// core.log(String.format("--o7f1: dbg fldinfo: %s---%s ", fldName,
			// QueryPropertyssMap.get(fldName)));
			WhereExpList.addAll(exprsLi);
		 
		return this;
	}

	/** @author attilax 老哇的爪子 \t@since Jul 18, 2014 11:45:58 PM$
	 * 
	 * @param queryPropertyssMap
	 * @param c
	 * @param fld
	 * @param fldName
	 * @param cdt
	 * @return */
	private List fld2critAdapt(Map queryPropertyssMap, Field fld, Conditional cdt) {
		cls_thrdLoc.set(this.cls);
		IAdapter adpt = (IAdapter) core.newx(cdt.adptr());

		// return adpt.convert(fld.getName(),
		// queryPropertyssMap.get(fld.getName()).toString());
		return adpt.convert(fld, queryPropertyssMap);

	}

	/** @author attilax 老哇的爪子 \t@since Jul 20, 2014 7:19:04 AM$
	 * 
	 * @param fldName
	 * @param op
	 * @param object
	 * @return
	 * @throws cantFindMatchFieldException
	 * @throws CantFindConverterExcept */
	@SuppressWarnings("unchecked")
	public List getExprs(Field fld, String op, Map valMap) {
		String colName = CountX.getColumnName( fld.getName(),this.cls);
		final String fldName=fld.getName();
		
		if (fldName.equals("materialType")) System.out.println("");
		if (fldName.equals("playtime")) System.out.println("");

		// attilax 老哇的爪子 7:19:04 AM Jul 20, 2014
		List li = new ArrayList();
		Object oriVal = valMap.get(fldName);
		Object cvtEdVal = oriVal;

		// ---if rang processed.. o9o jyad anno
		try {
			if (MdaUtil.isDsplyRange(fldName, this.saveObjClass)) {
				if (valMap.get(fldName + "_start").toString().equals(valMap.get(fldName + "_end"))) return new ArrayList();
				List fstExprs = fstExprs(fldName, valMap);

				List sekdExprs = sekdExprs(fldName, op, valMap);
				li.addAll(fstExprs);
				li.addAll(sekdExprs);
				return li;
			}
		} catch (cantFindMatchFieldException e2) {
			// attilax 老哇的爪子 10:28:01 AM Jul 20, 2014
			e2.printStackTrace();
		} catch (CantFindConditionalExcept e) {
			// attilax 老哇的爪子 10:29:59 AM Jul 20, 2014
			e.printStackTrace();
		}

		// def single mode process o9o
		// --------------------except is fld null/empty ret null
		try {
		//	Field fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {

				if (oriVal == null || oriVal.toString().equals("") || oriVal.toString().equals("0")) return li; // jump
			}
			if (refx.isStrType(fld)) {
				if (oriVal == null || oriVal.toString().equals("")) return li;// jump
			}
		} catch (Exception e1) {
			// attilax 老哇的爪子 8:00:02 AM Jul 20, 2014
			e1.printStackTrace();
			return li;// jump
		}

		// ------------covert default

	//	Field fld = null;
		try {
			fld = refx.getField(fldName, this.saveObjClass);
			// if(refx.isIntType(fld))
			// {
			// cvtEdVal=core.toInt(oriVal);
			// }
			// if(refx.isStrType(fld))
			// {
			// cvtEdVal=core.toInt(oriVal);
			// }
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 9:59:29 AM Jul 20, 2014
			e1.printStackTrace();
		}

		// --------------covert
		{

			org.apache.commons.beanutils.Converter cvt;
			try {
				cvt = getConvert(fldName);
				 
				cvtEdVal = cvt.convert(null, oriVal);
			} catch (CantFindConverterExcept e) {
				// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
				// e.printStackTrace();
				core.log(e);
				// cvtEdVal=oriVal;
			} catch (cantFindMatchFieldException e) {
				// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
				// e.printStackTrace();
				// cvtEdVal=oriVal;
				core.log(e);
			}

			if (op.equals(com.attilax.anno.op.like)) li.add(Restrictions.like(colName, "%" + cvtEdVal.toString() + "%"));
			else if (op.equals(com.attilax.anno.op.range)) {
				String ExpRitVal = "";
				if (refx.isStrType(fld)) ExpRitVal = "'" + cvtEdVal + "'";
				li.add((colName + op + ExpRitVal));
				li.add((colName + op + ExpRitVal));
			} else 	if( refx.isIntType(fld) &&  cvtEdVal.toString().contains(","))
			{//oac
				//	crSub.add( Restrictions.in(subobjFldName, covertToIntList(object) ));
					String ids = strUtil.trimx(",", cvtEdVal.toString());
					li.add((colName + " in( " + ids +" ) "));
				
			}
			else	// def
			{
				li.add((colName + op + cvtEdVal));
			}
			return li;
		}

	}

	/** @author attilax 老哇的爪子 \t@since Jul 20, 2014 10:23:16 AM$
	 * 
	 * @param fldName
	 * @param op
	 * @param valMap
	 * @return */
	private List fstExprs(String fldName, Map valMap) {
	 
		// attilax 老哇的爪子 10:23:16 AM Jul 20, 2014

		Object oriVal = valMap.get(fldName + "_start");
		List li = new ArrayList();
		Object cvtEdVal = oriVal;

		// --------------------except
		try {
			Field fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {

				if (oriVal == null || oriVal.toString().equals("") || oriVal.toString().equals("0")) return li; // jump
			}
			if (refx.isStrType(fld)) {
				if (oriVal == null || oriVal.toString().equals("")) return li;// jump
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 8:00:02 AM Jul 20, 2014
			e1.printStackTrace();
			return li;// jump
		}

		// ------------covert default
		// yaos time cant err magwesyi..
		Field fld;

		try {
			fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {
				cvtEdVal = core.toInt(oriVal);
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 9:59:29 AM Jul 20, 2014
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// --------------covert

		org.apache.commons.beanutils.Converter cvt;
		try {
			cvt = getConvert(fldName);
			cvtEdVal = cvt.convert(null, oriVal);
		} catch (CantFindConverterExcept e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		} catch (cantFindMatchFieldException e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		}

		// if(op.equals(com.attilax.anno.op.like))
		// li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%"));
		// else if(op.equals(com.attilax.anno.op.range))
		// {
		li.add(fldName + ">=" + cvtEdVal);
		// li.add(Restrictions.lt(fldName, cvtEdVal ));
		// }
		// else
		// {
		// li.add( Restrictions.eq(fldName, cvtEdVal));
		// }
		return li;

	}

	/** @author attilax 老哇的爪子 \t@since Jul 20, 2014 10:23:20 AM$
	 * 
	 * @param fldName
	 * @param op
	 * @param valMap
	 * @return */
	private List sekdExprs(String fldName, String op, Map valMap) {
		// attilax 老哇的爪子 10:23:20 AM Jul 20, 2014

		Object oriVal = valMap.get(fldName + "_end");
		List li = new ArrayList();
		Object cvtEdVal = oriVal;

		// --------------------except
		try {
			Field fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {

				if (oriVal == null || oriVal.toString().equals("") || oriVal.toString().equals("0")) return li; // jump
			}
			if (refx.isStrType(fld)) {
				if (oriVal == null || oriVal.toString().equals("")) return li;// jump
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 8:00:02 AM Jul 20, 2014
			e1.printStackTrace();
			return li;// jump
		}

		// ------------covert default

		Field fld;

		try {
			fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {
				cvtEdVal = core.toInt(oriVal);
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 9:59:29 AM Jul 20, 2014
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// --------------covert

		org.apache.commons.beanutils.Converter cvt;
		try {
			cvt = getConvert(fldName);
			cvtEdVal = cvt.convert(null, oriVal);
		} catch (CantFindConverterExcept e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		} catch (cantFindMatchFieldException e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		}

		// if(op.equals(com.attilax.anno.op.like))
		// li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%"));
		// else if(op.equals(com.attilax.anno.op.range))
		// {
		// li.add(Restrictions.gt(fldName, cvtEdVal ));
		li.add((fldName + "<=" + cvtEdVal));
		// }
		// else
		// {
		// li.add( Restrictions.eq(fldName, cvtEdVal));
		// }
		return li;

	}

	/** @author attilax 老哇的爪子 \t@since Jul 20, 2014 7:31:58 AM$
	 * 
	 * @param fldName
	 * @return
	 * @throws CantFindConverterExcept
	 * @throws cantFindMatchFieldException */
	public org.apache.commons.beanutils.Converter getConvert(String fldName) throws CantFindConverterExcept, cantFindMatchFieldException {
		// attilax 老哇的爪子 7:31:58 AM Jul 20, 2014

		Field fld = refx.getField(fldName, this.saveObjClass);
		// this.saveObjClass.getDeclaredField(fldName);

		return core.getConverter(fld);
		// com.attilax.anno.Converter
		// cvtAnno=fld.getAnnotation(com.attilax.anno.Converter.class);
		// if(cvtAnno==null)throw new CantFindConverterExcept();
		// Class<?> cvtCls= cvtAnno.value();
		// Object cvtClsObj=core.newx(cvtCls);
		// return (org.apache.commons.beanutils.Converter) cvtClsObj;

	}
	/** @author attilax 老哇的爪子
	 * @since o9p 5_53_u
	 * 
	 * @return */
	@SuppressWarnings("all")
	public String toSql() {
		// attilax 老哇的爪子 5_53_u o9p
		Table4count Table4count2 = this.cls.getAnnotation(Table4count.class);
		GroupByFld gbf=this.cls.getAnnotation(GroupByFld.class);
		if(gbf!=null)
		{
			this.SelectItemList.add(  gbf.value()  );
			this.GroupByItemList.add(   gbf.value()  );
		}
		String s = " select " + CollX.join(this.SelectItemList) + " from " + Table4count2.name() + 	  new ClosureNoExcpt() {

			@Override public Object execute(Object arg0) {
				// attilax 老哇的爪子 j_45_s o9p

				if (WhereExpList.size() > 0) return " where " + CollX.join(WhereExpList, " and ");
				return "";

			}
		}.execute(null)+ " group by " + CollX.join(this.GroupByItemList)
			;

		return s;

	}
}

// attilax 老哇的爪子