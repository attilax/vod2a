package com.attilax.collection;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.Closure;
import org.apache.log4j.Logger;
import org.apache.tools.ant.types.selectors.DateSelector;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.experimental.categories.Category;
import cn.jpush.api.common.APIRequestException;
import com.attilax.core;
import com.attilax.api.HandlerChain;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.listUtil;
import com.attilax.db.DBX;
import com.attilax.db.ix;
import com.attilax.io.filex;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.downtask.GvDownloadTaskSvs;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.playRec.GvPlayRecordDAO;
import com.focusx.playRec.baseDAO;
import com.focusx.programme.dao.impl.ProgrammeDaoImpl;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.publish.dao.Impl.PublishDaoImpl;
import com.focusx.publish.entity.GvPublish;
import com.focusx.push.pushX;
import com.focusx.util.HbX4vod;

/**
 * 
 * @author  attilax 老哇的爪子
 *@since  o7t 1_p_e$
 */
public class GvCycleQueueSvs extends GvCycleQueueDAO implements CycleQueue
{
	public static Logger logger = Logger.getLogger("TaskNoticer");
 public static void main(String[] args) {
	//	tt();
	//	new GvCycleQueueSvs().	 pushTask();
//		new ix(9999).times(new Closure() {
//			
//			@Override public void execute(Object arg0) {
//				// attilax 老哇的爪子  m_6_v   o7s 
//		
//			 core.sleep(5*3600*1000);
//			}
//		});
	}

	private static void tt() {
		Session sess = HbX4vod.getSession();
		PublishDaoImpl c = new PublishDaoImpl();
		c.setSessionFactory(HbX4vod.getSessionFactory());
		sess = c.getSession();
		sess.beginTransaction();
		GvPublish o = c.getPublish(1090);
		o.setPrgrm(o.getPrgrm());
		// String describe = o.getPrgrm().getDescribe();
		// System.out.println(describe);
		// core.print(o);
		System.out.println(CycleDetectionStrategy.LENIENT);

		// 先过滤对set集合的拆解
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equals("prgrm22")) {
					return true;
				} else {
					return false;
				}
			}

		});
		// 将数据转换成Json数据

		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件

		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略

		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); // 此处是亮点，不过经过测试

		// config.registerJsonValueProcessor(Date.class, new
		// JsonValueProcessor() {
		// // 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
		// public Object processObjectValue(String arg0, Object arg1, JsonConfig
		// arg2) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date d = (Date) arg1;
		// return sdf.format(d);
		// }
		// public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// return null;
		// }
		// });

		config.registerJsonValueProcessor(Timestamp.class, new JsonValueProcessor() {
			// 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Timestamp stp = (Timestamp) arg1;
					Date d = (Date) stp;
					return sdf.format(d);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return "..";
			}
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}
		});

//		config.registerJsonValueProcessor(List.class, new JsonValueProcessor() {
//			// 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
//			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
//
//				return "..list..";
//			}
//			public Object processArrayValue(Object arg0, JsonConfig arg1) {
//				return null;
//			}
//		});

		config.registerJsonValueProcessor(GvPlayRecordDAO.class, new JsonValueProcessor() {
			// 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					GvProgramme stp = (GvProgramme) arg1;
					GvProgramme o = new GvProgramme();
					BeanUtils.copyProperties(o, stp);
					o.setProgarmmeId(stp.getProgarmmeId());
					o.setDescribe(stp.getDescribe());

					JsonConfig config2 = new JsonConfig();
					config2.setJsonPropertyFilter(new PropertyFilter() {
						@Override public boolean apply(Object arg0, String arg1, Object arg2) {
							if (arg1.equals("list")) {
								return true;
							} else {
								return false;
							}
						}

					});
					// JSONObject jsonObject = JSONObject.fromObject(stp);
					// return jsonObject.toString();
					return core.toJsonStrO7(o);
				} catch (Exception e) {
					core.log(e);
				}
				return null;
			}
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}
		});

		JSONObject jsonObject = JSONObject.fromObject(o, config);
		System.out.println(jsonObject.toString(2));
		// genejson();
	}
	private static void genejson() {
		Session sess = HbX4vod.getSession();
		ProgrammeDaoImpl c = new ProgrammeDaoImpl();
		c.setSessionFactory(HbX4vod.getSessionFactory());
		sess = c.getSession();
		sess.beginTransaction();
		System.out.println(c.getProgrammeList(null, new HashMap()).size());
		;
	}

	/* (non-Javadoc)
	 * @see java.util.Queue#add(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean add(Object e) {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Queue#offer(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean offer(Object e) {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Queue#remove()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public Object remove() {
		// attilax 老哇的爪子  i_57_g   o85 
		return null;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Queue#poll()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public Object poll() {
		// attilax 老哇的爪子  i_57_g   o85 
		return null;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Queue#element()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public Object element() {
		// attilax 老哇的爪子  i_57_g   o85 
		return null;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Queue#peek()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public Object peek() {
		// attilax 老哇的爪子  i_57_g   o85 
		return null;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#size()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public int size() {
		// attilax 老哇的爪子  i_57_g   o85 
		return 0;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean isEmpty() {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean contains(Object o) {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#iterator()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public Iterator iterator() {
		// attilax 老哇的爪子  i_57_g   o85 
		return null;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public Object[] toArray() {
		// attilax 老哇的爪子  i_57_g   o85 
		return null;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray(T[])
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public Object[] toArray(Object[] a) {
		// attilax 老哇的爪子  i_57_g   o85 
		return null;
		
		
		
		
	}

	public DBX dbx;
	/* (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean remove(Object o) {
		// attilax 老哇的爪子  i_57_g   o85 
		 
 
		
		GvCycleQueue t;
		try {
			t = (GvCycleQueue) this.findByProperty_SingleObj("rltRecId",  o.toString());
			try {
				if(t.getSendRetMsg()==null)
				{
					filex.save_SF("wanring::get ret msg is null ,but feedback ,pubid:"+o.toString()  , "c:\\prgrmFeedbackWarn\\"+filex.getUUidName()+".txt");
				}
			} catch (Exception e) {
				core.err(e);
			}
			
			if(t.getSendRetMsg()!=null)
			{
				t.setDel(1);
				dbx.merge_syn(t);
			}
			return true;
		} catch (CantFindObj e) {
			//  attilax 老哇的爪子 上午01:59:02   2014-9-1   
		core.warn(e);	return false;
		}
	
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean containsAll(Collection c) {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean addAll(Collection c) {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean removeAll(Collection c) {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public boolean retainAll(Collection c) {
		// attilax 老哇的爪子  i_57_g   o85 
		return false;
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#clear()
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public void clear() {
		// attilax 老哇的爪子  i_57_g   o85 
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.attilax.collection.CycleQueue#moveToTail(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */
	@Override public void moveToTail(Object o) {
		// attilax 老哇的爪子  i_57_g   o85 
	baseDAO c = new baseDAO();
	GvCycleQueue t;
	if( o instanceof GvCycleQueue)
	{
		GvCycleQueue t_tmp=(GvCycleQueue) o; 
		
		  t=this.findById(t_tmp.getId());
	}else
	{
		 t=this.findById(Integer.parseInt(o.toString()) );
	}
		if(t.getCirTimes() ==null)
			t.setCirTimes(0);
		t.setCirTimes(t.getCirTimes()+1);
		c.merge(t);
	//	return true;
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.attilax.collection.CycleQueue#peek(int)
	 * @author  attilax 老哇的爪子
	 *@since  o85 i_57_g$
	 */ @Deprecated
	@Override public List peek(int fetchCount) {
		// attilax 老哇的爪子  m_9_r   o7s 
throw new RuntimeException("cant impt:");
		
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o85 j_w_z$
	
	 * @param i
	 * @param type
	 * @return
	 */
	@SuppressWarnings("all")
	public List peek(int fetchCount, String type) {
		if("1".equals("1"))
			throw new RuntimeException("....p1h");
		// attilax 老哇的爪子  j_w_z   o85 
		final List li=new ArrayList();
//		 
		//  noticeFlag is null
		String hql="from GvCycleQueue   where 1=1 and del=0  and rectype='"+type+"' order by cirTimes  ";
		Query q = getSession().createQuery(hql); 
		q.setFirstResult(0); 
		q.setMaxResults(fetchCount); 
		List l = q.list(); 
		return l;
		
	}

}
