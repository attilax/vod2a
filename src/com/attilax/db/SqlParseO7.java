package com.attilax.db;

import java.io.StringReader;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import org.apache.commons.collections.Closure;

public class SqlParseO7 {

	private String sql;

	public SqlParseO7(String sql) {
		this.sql = sql;
	}

	public SqlParseO7( ) {
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void parse(Closure c) throws JSQLParserException {
		CCJSqlParserManager parserManager = new CCJSqlParserManager();
		

		// String statement =
		// "CREATE TABLE mytab (mycol a (10, 20) c nm g, mycol2 mypar1 mypar2 (23,323,3) asdf ('23','123') dasd, "
		// + "PRIMARY KEY (mycol2, mycol)) type = myisam";
		CreateTable createTable = (CreateTable) parserManager
				.parse(new StringReader(this.sql));

		List columnDefinitions = createTable.getColumnDefinitions();
		String tabName = createTable.getTable().getName();

		// System.out.println(columnDefinitions.size());// 获得字段总数.
		for (Object object : columnDefinitions) {
			ColumnDefinition col = (ColumnDefinition) object;

			Object[] oa = { col.getColumnName(),
					col.getColDataType().getDataType(), tabName };
			c.execute(oa);

		}
	}

}
