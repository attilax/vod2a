/**
 * @author attilax 老哇的爪子
	@since  o92 j_g_1$
 */
package com.attilax.ioc;

import com.attilax.core;
import com.attilax.db.DBX;
import com.attilax.db.DbxMybatis;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import static com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/** @author attilax 老哇的爪子
 * @since o92 j_g_1$ */
public class guiceT {
	@Inject DBX dbx;
	/** @author attilax 老哇的爪子
	 * @since o92 j_g_2
	 * 
	 * @param args */

	public static void main(String[] args) {
		// attilax 老哇的爪子 j_g_2 o92

		guiceT c =  IocX.getBean(guiceT.class);
		List li = c.dbx.execSql("select 1 as t");
		System.out.println(core.toJsonStrO88(li));
		// dbx

	}
	// attilax 老哇的爪子 j_g_1 o92
}

// attilax 老哇的爪子