package com.attilax.db;

import static com.attilax.core.obj2json;
import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sourceforge.jtds.jdbc.ColInfo;

import org.apache.commons.collections.Closure;
import org.hibernate.dialect.Dialect;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.sql.Insert;

import com.attilax.core;

public class SqlGener {

	public enum dialect {
		MSSQL("org.hibernate.dialect.SQLServerDialect");
		private String name;

		dialect(String name) {

			this.name = name;
		}

		@Override
		public String toString() {

			return this.name;

		}
	}

	public static final String dialect_MSSQL = "org.hibernate.dialect.SQLServerDialect";

	private String dialectName;
	private SqlParseO7 sqlParseO7;

	public SqlGener(String dialectName2, SqlParseO7 sqlParseO7) {
		this.dialectName = dialectName2.toString();
		this.sqlParseO7 = sqlParseO7;
	}

	public static void main(String[] args) {

		dialect mssql = dialect.MSSQL;
		System.out.println(mssql);
	}
	List colList;
	public String exec(  final int n)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, JSQLParserException {
		
		fetchColuname();
	
		Dialect dialect = (Dialect) ReflectHelper.classForName(dialectName)
				.newInstance();
		// Dialect.getDialect();
		final Insert ist = new Insert(dialect);

		this.sqlParseO7.parse(new Closure() {

			@Override
			public void execute(Object arg0) {
				Object[] oa = (Object[]) arg0;
				String ColumnName = (String) oa[0];
				ColumnName=clrCol(ColumnName);
				String ColDataType = (String) oa[1];

				String defltObj = defltObj(ColumnName, ColDataType, n);
				try {
					ist.addColumn(ColumnName, defltObj);
				} catch (Exception e) {
					core.log(e);
				}
				ist.setTableName((String) oa[2]);
				colList.add(ColumnName);
			}

		});
		return ist.toStatementString();
	}

	/**
	@author attilax 老哇的爪子
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws JSQLParserException 
		@since  o7d jcd$
	
	 */
	private void fetchColuname() throws InstantiationException, IllegalAccessException, ClassNotFoundException, JSQLParserException {
		// attilax 老哇的爪子  jcd   o7d 
		colList=null;
		colList=new ArrayList<String>();
		Dialect dialect = (Dialect) ReflectHelper.classForName(dialectName)
				.newInstance();
		// Dialect.getDialect();
		final Insert ist = new Insert(dialect);

		this.sqlParseO7.parse(new Closure() {

			@Override
			public void execute(Object arg0) {
				Object[] oa = (Object[]) arg0;
				String ColumnName = (String) oa[0];
				String ColDataType = (String) oa[1];
 
				colList.add(clrCol( ColumnName));
			}

		});
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o7d jjo$
	
	 * @param columnName
	 * @return
	 */
 

	protected   String defltObj(String columnName, String dataType, int idx) {
		if(isID(columnName))
			return String.valueOf( idx+1);
		if (dataType.startsWith("varchar"))
		{
			columnName=clrCol(columnName);
			return "'" + columnName + String.valueOf(idx) + "'";
		}
		if (dataType.startsWith("int"))
			return "0";
		if (dataType.startsWith("date"))
			return "'1920-2-2 00:01:01'";
		return null;
	}

	private String clrCol(String columnName) {
		return columnName.replaceAll("\"", "");
	}

	/**
	@author attilax 老哇的爪子
		@since  o7d j67$
	
	 * @param columnName
	 * @return
	 */
	private   boolean isID(String columnName) {
		// attilax 老哇的爪子  j67   o7d 
		String id=findID(this.colList);
		if(columnName.toLowerCase().equals(id.toLowerCase()))return true;
		return false;
	 
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o7d j655$
	
	 * @param colList2
	 * @return
	 */
	private String findID(List colList2) {
		// attilax 老哇的爪子  j655   o7d 
	for (Object object : colList2) {
		String col=(String) object;
		col=col.toLowerCase();
		if(col.equals("id")) return col;
		if(col.endsWith("_id"))
			return col;
	}
	return "...";
		
	}

}
