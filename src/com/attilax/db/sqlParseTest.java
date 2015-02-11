package com.attilax.db;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Closure;
import org.hibernate.dialect.Dialect;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.sql.Insert;
import org.hibernate.type.LiteralType;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

public class sqlParseTest {

	public static void main(String[] args) throws Exception {
		final String sql = filex.read("D:\\workspace\\vodx\\sqlx\\elmt.sql", "gbk");
		 

		// ---------------------------
		final List li = new ArrayList();
		String s = new ix(10).times(new Closure() {

			@Override
			public void execute(Object arg0) {
				 
				SqlGener sg = new SqlGener(SqlGener.dialect_MSSQL, new SqlParseO7(sql));
				int idx = Integer.parseInt(arg0.toString());
				try {

					String sql = sg.exec( idx);
					li.add(sql);
				} catch (Exception e) {
					core.log(e);
				}

			}

		});

		// final Insert ist = exec(dialectName, n);

		// parse(sql, c);

		Map m = new HashMap();
		m.put("dt", new Date());
		// core.print(m);

		// ist.addColumn("col","valexp");
		// ist.addColumn("col2","valexp2" ,
		// );
		// insert into tabname (col) values (valexp)
		System.out.println(listUtil.toString(li));

	}

}
