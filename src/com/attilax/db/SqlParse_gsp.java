package com.attilax.db;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TColumnDefinition;
import gudusoft.gsqlparser.nodes.TColumnDefinitionList;
import gudusoft.gsqlparser.stmt.TCreateDatabaseSqlStatement;
import gudusoft.gsqlparser.stmt.TCreateTableSqlStatement;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.commons.collections.Closure;
import com.attilax.io.filex;

public class SqlParse_gsp extends SqlParseO7 {

	private String sql;

	public SqlParse_gsp(String sql) {
		this.sql = sql;
	}

	/**
	 * "material_id"---int
"material_description"---varchar(256)
"material_type"---int
"create_user"---int
"create_time"---datetime
"effectie_time"---datetime
"failure_time"---datetime
"size"---varchar(10)
"file_path"---varchar(256)
"can_down_org"---varchar(128)
"update_user"---int
"update_time"---datetime
"application_type"---int
	@author attilax 老哇的爪子
		@since  o7d i428$
	
	 * @param args
	 * @throws JSQLParserException
	 */
	public static void main(String[] args) {
		String sql = filex.read("D:\\workspace\\vodx\\sqlx\\elmt.sql");

		SqlParse_gsp c = new SqlParse_gsp(sql);
		try {
			c.parse(new Closure() {

				@Override public void execute(Object arg0) {
					// attilax 老哇的爪子 ikk o7d
					Object[] oa = (Object[]) arg0;
					System.out.println(oa[0].toString() + "---" + oa[1].toString());

				}
			}

			);
		} catch (JSQLParserException e) {
			//  attilax 老哇的爪子 i5037   o7d   
			e.printStackTrace();
		}

	}

	public void parse(Closure c) throws JSQLParserException {

		EDbVendor dbVendor = EDbVendor.dbvoracle;
		dbVendor = EDbVendor.dbvmssql;
		TGSqlParser sqlparser = new TGSqlParser(dbVendor);
		sqlparser.sqltext = this.sql;
		int ret = sqlparser.parse();
		TCustomSqlStatement sttmt = sqlparser.sqlstatements.get(0);

		// if(sttmt.sqlstatementtype sttmt.sqlstatementtype.sstcreatetable )
		TCreateTableSqlStatement tCrtTabStmt = (TCreateTableSqlStatement) sttmt;
		TColumnDefinitionList columnDefinitions = tCrtTabStmt.getColumnList();
		for (int i = 0; i < columnDefinitions.size(); i++) {
			TColumnDefinition col = columnDefinitions.getColumn(i);
			Object[] oa = { col.getColumnName().toString(), col.getDatatype().toString(), tCrtTabStmt.getTableName().toString() };
			c.execute(oa);
		}

		CCJSqlParserManager parserManager = new CCJSqlParserManager();

		// String statement =
		// "CREATE TABLE mytab (mycol a (10, 20) c nm g, mycol2 mypar1 mypar2 (23,323,3) asdf ('23','123') dasd, "
		// + "PRIMARY KEY (mycol2, mycol)) type = myisam";
		// CreateTable createTable = (CreateTable) parserManager
		// .parse(new StringReader(this.sql));
		//
		// List columnDefinitions = createTable.getColumnDefinitions();
		// String tabName = createTable.getTable().getName();
		//
		// // System.out.println(columnDefinitions.size());// 获得字段总数.
		// for (Object object : columnDefinitions) {
		// ColumnDefinition col = (ColumnDefinition) object;
		//
		// Object[] oa = { col.getColumnName(),
		// col.getColDataType().getDataType(), tabName };
		// c.execute(oa);
		//
		// }
	}

}
