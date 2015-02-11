/**
 * @author attilax 老哇的爪子
	@since  o8o i_50_48$
 */
package com.attilax.concur;

import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.ref.cantFindIDFieldEx;
import com.focusx.publish.entity.GvPublish;
import static com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/** @author attilax 老哇的爪子
 * @since o8o i_50_48$ */
public interface IconcurTest<t2> {

	// 12.最大的数据id
	int maxID(Class<?> cls) throws cantFindIDFieldEx, dataISEmptyEx;
	int count();// 13.一次获得的数据数 （ 固定/随机）

	public <k, v> Object op(Map<k, v> params);// 操作

}

// attilax 老哇的爪子