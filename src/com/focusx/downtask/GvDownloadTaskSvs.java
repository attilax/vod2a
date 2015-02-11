package com.focusx.downtask;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.attilax.Closure;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.errEventProcess;
import com.attilax.MDA.IAdapter;
import com.attilax.Stream.Mapx;
import com.attilax.api.Handler;
import com.attilax.api.HandlerChain;
import com.attilax.biz.orgGroup.OrgFilter;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.coll.ListX;
import com.attilax.collection.CollX;
import com.attilax.collection.CollectionUtils;
import com.attilax.concur.IconcurTest;
import com.attilax.concur.dataISEmptyEx;
import com.attilax.db.DBX;
import com.attilax.db.ix;
import com.attilax.dsm.BaseSvs;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.net.IReqParaConverter;
import com.attilax.noticer.jsonFldSerialIgone;
import com.attilax.persistence.HbxX;
import com.attilax.persistence.PX;
import com.attilax.ref.cantFindIDFieldEx;
import com.attilax.secury.propertyReader;
import com.attilax.spri.SpringUtil;
import com.attilax.text.strUtil;
import com.attilax.time.timeUtil;
import com.attilax.util.WebCfgRead;
import com.focusx.ServiceLoctor4vod;
import com.focusx.auth.authx;
import com.focusx.downRec.GvDownloadRecord;
import com.focusx.downRec.GvDownloadRecordSvs;
import com.focusx.elmt.BaseHibernateDAO;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.entity.TMbGroup;
import com.focusx.playRec.GvPlayRecord;
import com.focusx.playRec.baseDAO;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.entity.GvProgrammeDetail;
import com.focusx.publish.dao.Impl.PublishDaoImpl;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.service.IPublishService;
import com.focusx.push.TaskNoticer;
import com.focusx.push.pushX;
import com.focusx.util.Constant;
import com.focusx.util.HbX4vod;
import com.focusx.util.OperLogUtil4vod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static com.attilax.core.new_cglib;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.focusx.downtask.GvDownloadTaskSvs com.focusx.downtask.DownmanaItem
 * 
 * A data access object (DAO) providing persistence and search support for
 * GvDownloadTask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.focusx.downtask.GvDownloadTask
 * @author MyEclipse Persistence Tools   o826 if IconcurTest not use generic
 *         .then op() cant compiler.. or op() not use generi can compiler.
 */
@RemoteProxy(name = "downmanaX")
public class GvDownloadTaskSvs<EE> extends BaseHibernateDAO implements
		QueueCycle<EE>, Handler, IconcurTest<Object> {
	/**
	 * 
	 */
	private static final int nullval = 8;
	private static final Logger log = LoggerFactory
			.getLogger(GvDownloadTaskSvs.class);
	// property constants
	public static final String NOTICE_FLAG = "noticeFlag";
	public static final String DOWNLOAD_STATUS = "downloadStatus";
	public static final String MATERIAL_ID = "materialId";
	public static final String EQUIPMENT_ID = "equipmentId";
	@com.google.inject.Inject
	public DBX dbx;
	@com.google.inject.Inject
	public BaseSvs base = new BaseSvs();

	@RemoteMethod
	@SuppressWarnings("all")
	/**
	 * with page spt
		@author attilax 老哇的爪子
		@since   oaa e_50_l
	 */
	public List findByPropertyss(final Map QueryPropertyssMap) {
		IReqParaConverter<Map, Map> cvtr = new IReqParaConverter<Map, Map>() {

			@Override
			public Map convert(Map map) {
				if (strUtil.isNotEmpty(map.get("progarmmeId"))) {

					map.put("prgrmID", map.get("progarmmeId"));
				}
				if (map.get("downloadStatus") != null) {
					if (map.get("downloadStatus").toString().equals("0"))// watit
																			// down..

					{
						map.put("downloadStatus", nullval);
						map.put("startdownFlag", nullval);
					}

					if (map.get("downloadStatus").toString().equals("2"))// downing..
					{
						map.put("downloadStatus", nullval);
						map.put("startdownFlag", 1);
					}
					if (map.get("downloadStatus").toString().equals("1"))// down
																			// ok..
					{
						// map.put("downloadStatus", 1);
						map.put("trueDownFlag", 1);
					}
					if (map.get("downloadStatus").toString().equals("3"))// existed..
					{
						map.put("downloadStatus", null);
						map.put("trueDownFlag", 2);
					}
					// if
					// (map.get("downloadStatus").toString().contains("in(3,4,7)"))//
					// failt..
					// {
					// // map.put("downloadStatus",nullval);
					// // map.put("downloadStatus", nullval);
					// map.put("trueDownFlag", "3,4,7");
					// }
					if (map.get("downloadStatus").toString().length() > 10) {
						// fail state
						String fltIds = (String) getIdsByDownstate(QueryPropertyssMap);// obb
						QueryPropertyssMap.put("dsId", fltIds);
						QueryPropertyssMap.put("downloadStatus", "");

					}
					// obb end

				}

				return map;
			}
		};

		Map mp2 = cvtr.convert(QueryPropertyssMap);
		List rzt = base.findByPropertyss(mp2, GvDownloadTask.class);
		return rzt;

	}

	/**
	 * @author attilax 老哇的爪子
	 * @param queryPropertyssMap
	 * @since obe i_q_w
	 */
	@SuppressWarnings("all")
	private Object getIdsByDownstate(Map queryPropertyssMap) {
		String s = " SELECT TOP 1000   ds_id FROM [gv_download_task] rec where 1=1 @where ";
		s = s.replaceAll("@where", queryPropertyssMap.get("downloadStatus")
				.toString());

		List<Map> li = this.px.findBySql(s);
		String ids = CollX.toString(li, "ds_id");
		return ids;
	}

	@RemoteMethod
	@SuppressWarnings("all")
	public List first(int id) {
		base.modifyLevel(String.valueOf(id));

		return null;

	}

	@RemoteMethod
	@SuppressWarnings("all")
	public List firstAllOa5(String ids) {
		base.modifyLevel(ids);
		return null;

	}

	@com.google.inject.Inject
	PX px = new HbxX();
	@com.google.inject.Inject
	pushX pushx = new pushX();

	/**
	 * 
	 @author attilax 老哇的爪子
	 * @since o00 j_59_38
	 * 
	 * @param id
	 * @return
	 */
	@RemoteMethod
	@SuppressWarnings("all")
	public List stop(int id) {
		GvDownloadTaskSvs c = IocX.getBean(GvDownloadTaskSvs.class);
		final GvDownloadTask t = c.findById(id);

		core.execMeth_Ays(new Runnable() {

			@Override
			public void run() {
				// attilax 老哇的爪子 k_4_f o00
				String meth = "stopdown";
				String msgAti = HandlerChain.addActNSecuryINfo(core
						.toJsonStrO7(t, jsonFldSerialIgone
								.jsonConfig_timefmtNinoneRetmsg()), meth);
				try {
					pushx.push(msgAti, t.getEquipmentId());
					log2db4stopdown(t, null, msgAti);
				} catch (APIConnectionException e) {
					// attilax 老哇的爪子 k_7_o o00
					core.err(e);
					log2db4stopdown(t, e, msgAti);

				} catch (APIRequestException e) {
					// attilax 老哇的爪子 k_7_o o00
					core.err(e);
					log2db4stopdown(t, e, msgAti);
				}
			}

			private void log2db4stopdown(GvDownloadTask tsk, Exception e,
					String pushMsg) {
				// attilax 老哇的爪子 上午01:49:53 2014-9-2
				System.out.println("aaaaa");
				try {
					logRec o = new logRec();
					o.setLevel(Level.info);
					o.setIdx(tsk.getRltID());
					o.setIdx2(tsk.getRltID2());
					o.setIdx3(tsk.getRltID3());
					o.setCreateTime(timeUtil.getTimestamp());
					o.setLogger("stopdown");
					o.setMsg(tsk.getSendMsg());
					if (e != null)
						o.setMsg(core.getTrace(e));
					o.setMsgA(pushMsg);
					o.setSend("send");
					o.setThread("stopdown");
					o.setCate("stopdown");
					// this.dbx.save(o);
					new baseDAO().save(o);
				} catch (Exception e1) {
					core.log(e1);
				}

			}
		}, "stopdownPushOa9Thrd");

		// ------------------------set opr and op_time----------------
		t.setDownloadStatus(1);
		try {
			t.setOpr(new Closure<Object, String>() {

				@Override
				public String execute(Object arg0) throws Exception {
					// attilax 老哇的爪子 f_s_w o9r
					WebContext webContext = WebContextFactory.get();
					// webContext.getSession() ;
					// ;

					//
					return authx.getCurUsername(webContext
							.getHttpServletRequest());

				}
			}.execute(null));
		} catch (Exception e) {
			// attilax 老哇的爪子 f_u_b o9r
			core.warn(e);
		}
		t.setOptim(timeUtil.getTimestamp());
		px.merge(t);

		try {
			OperLogUtil4vod.log("终止任务:" + t.getMtrl().getMaterialDescription());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;

	}

	@com.google.inject.Inject
	TaskNoticer tskNtsr = new TaskNoticer();

	@RemoteMethod
	@SuppressWarnings("all")
	/**
	 * test send push  from ui js
	 * @param taskID
	 * @return
	 */
	public String sendMtrl(Integer taskID) {
		try {

			GvDownloadTask t = (GvDownloadTask) this.px.get(
					GvDownloadTask.class, taskID);
			if (t == null)
				return "taskid is null:" + String.valueOf(taskID);

			return (String) tskNtsr.push_single_core(t);
		} catch (Exception e) {

			return core.getTrace(e);
		}

	}

	PropFilter orgFilter = new OrgFilter();

	@RemoteMethod
	@SuppressWarnings("all")
	public Map findByPropertyss_page(final Map QueryPropertyssMap) {

		// add org filter
		try {
			QueryPropertyssMap.putAll((Map) orgFilter.getExp());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return (Map) core.retry3(new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				List list_sub = findByPropertyss(QueryPropertyssMap);
				grpX.setProvNsubBrach(list_sub,
						new Closure<Object, TMbGroup>() {

							@Override
							public TMbGroup execute(Object arg0)
									throws Exception {
								GvDownloadTask item = (GvDownloadTask) arg0;

								return item.getEq().getDpt();
							}
						});
				Map mp = new HashMap();
				mp.put("total",
						SafeVal.val(base.threadLocal_rowsCount.get(),
								list_sub.size()));
				mp.put("rows", list_sub);
				// mp.put("pageSize",10);

				return mp;
			}
		}, new errEventProcess() {

			@Override
			public Object execute(Object arg0) throws Exception {
				Map mp = new HashMap();
				mp.put("total", 0);
				mp.put("rows", new ArrayList());
				return mp;
			}

		}
		// filex.save_SF(core.getTrace(e),
		// "c:\\downmanaQueryLog\\downmanaQueryE"+filex.getUUidName()+".txt");

				, "c:\\downmanaQueryLog");

	}

	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		// to8o();
		// oaa();
		Map map = new HashMap();
		map.put("downloadStatus", nullval); // 8 is nullval
		map.put("startdownFlag", nullval);
		map.put("pagesize", 1);
		map.put("page_page", 1);

		GvDownloadTaskSvs c = IocX.getBean(GvDownloadTaskSvs.class);
		List li = c.findByPropertyss(map);
		filex.save_SF(core.toJsonStrO88(li), "c:\\oa9.txt");

		// new GvDownloadTaskSvs().append2taskQueue(pub2, sess);
		System.out.println("--");
	}

	private static void oaa() {
		IocX.update = true;
		Session sess = HbX4vod.getSession();
		PublishDaoImpl c = new PublishDaoImpl();
		c.setSessionFactory(HbX4vod.getSessionFactory());
		sess = c.getSession();
		Transaction tx = sess.beginTransaction();
		final GvPublish pub2 = c.getPublish(1897);
		System.out.println(pub2.getMome());
	}

	private static void to8o() throws Exception {
		GvDownloadTask o = new GvDownloadTask();
		o.setFile("http://xxxx/aaa.mpg");
		// new baseDAO().save(o);
		System.out.println(core.toJsonStrO7(o));

		GvPublish pb = new GvPublish() {
			{
				this.setPublishId(1090);

			}
		};
		String f = "c:\\downFeedbk.json";
		GvDownloadTaskSvs c = new GvDownloadTaskSvs();
		// new GvDownloadTaskSvs().append2taskQueue(pb);
		// System.out.println(new GvDownloadTaskSvs().getTop(5));
		System.out.println(c.handleReq(filex.read(f)));
	}

	@SuppressWarnings("all")
	public void append2taskQueue(final GvPublish pub, Session session,
			final Closure klos) {
		// Session sess = HbX4vod.getSession();
		// PublishDaoImpl c = new PublishDaoImpl();
		// c.setSessionFactory(HbX4vod.getSessionFactory());
		// sess = c.getSession();
		// Transaction tx= sess.beginTransaction();
		// IPublishService c = (IPublishService)
		// SpringUtil.getBean("publishService");
		final GvPublish pub2 = (GvPublish) session.get(GvPublish.class,
				pub.getPublishId());
		// c.getPublish(pub.getPublishId());
		//
		core.logObj(pub2);
		GvProgramme prgrmO8 = new GvProgramme();
		// final GvProgramme prgrmO9h=prgrmO8;
		if (pub2.getPrgrm() == null) // o8q
		{
			try {
				prgrmO8 = (GvProgramme) session.get(GvProgramme.class,
						pub.getProgarmmeId());
				pub2.setPrgrm(prgrmO8);
			} catch (Exception e) {
				core.log(e);
				prgrmO8 = (GvProgramme) ServiceLoctor4vod.getDbx().getSession()
						.get(GvProgramme.class, pub.getProgarmmeId());
				pub2.setPrgrm(prgrmO8);
			}
		}

		List li = pub2.getPrgrm().getList();
		// for deduli o0k
		// final Set set_pdIDs=CollX.deDuli(li, new Closure() {
		//
		// @Override
		// public Object execute(Object arg0) throws Exception {
		// GvProgrammeDetail d=(GvProgrammeDetail) arg0;
		// return d.getMaterialId();
		// }});
		// tx.commit();;
		final Set set_pdIDs_alreday = new HashSet();
		CollectionUtils.each_safe(li, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 j_7_57 o7s
				GvProgrammeDetail d = (GvProgrammeDetail) arg0;
				if (set_pdIDs_alreday.contains(d.getMaterialId())) // for deduli
																	// o0k
					return null;
				else
					set_pdIDs_alreday.add(d.getMaterialId());
				GvDownloadTask t = new GvDownloadTask();
				t.setEquipmentId(pub2.getEquipmentId());
				t.setMaterialId(d.getMaterialId());
				t.setPrgrmID(pub2.getPrgrm().getProgarmmeId());// o9h
				t.setPubID(pub2.getPublishId());
				GvMaterialSvs gc = new GvMaterialSvs();
				try {
					t.setFile(gc.findById(d.getMaterialId()).getFilePath());
				} catch (Exception e) {
					core.log(e);
				}

				// o8q 14
				t.setRltID(pub2.getPublishId());
				t.setRltID2(pub2.getProgarmmeId());
				t.setRltID3(d.getPdId());

				// o0l sendMtrl_on_pub_async

				klos.execute(t);
				try {
					ServiceLoctor4vod.getDbx().save(t);
				} catch (Exception e) {
					core.log(e);
					new baseDAO().save(t);// retry
				}

				return null;

			}
		});

	}

	// private void log2db4stopdown (GvDownloadTask tsk) {
	// // attilax 老哇的爪子 上午01:49:53 2014-9-2
	// System.out.println("aaaaa");
	// try {
	// logRec o=new logRec();
	// o.setLevel(Level.info);
	// o.setIdx(tsk.getRltID());
	// o.setIdx2(tsk.getRltID2());
	// o.setIdx3(tsk.getRltID3());
	// o.setCreateTime(timeUtil.getTimestamp());
	// o.setLogger("stopdown");
	// o.setMsg(tsk.getSendMsg());
	//
	// o.setSend("send");
	// o.setThread("stopdown");
	// o.setCate("stopdown");
	// //this.dbx.save(o);
	// new baseDAO().save(o);

	public void save(GvDownloadTask transientInstance) {
		log.debug("saving GvDownloadTask instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GvDownloadTask persistentInstance) {
		log.debug("deleting GvDownloadTask instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GvDownloadTask findById(java.lang.Integer id) {
		log.debug("getting GvDownloadTask instance with id: " + id);
		try {
			GvDownloadTask instance = (GvDownloadTask) getSession().get(
					"com.focusx.downtask.GvDownloadTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GvDownloadTask> findByExample(GvDownloadTask instance) {
		log.debug("finding GvDownloadTask instance by example");
		try {
			List<GvDownloadTask> results = (List<GvDownloadTask>) getSession()
					.createCriteria("com.focusx.downtask.GvDownloadTask")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding GvDownloadTask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from GvDownloadTask as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GvDownloadTask> findByNoticeFlag(Object noticeFlag) {
		return findByProperty(NOTICE_FLAG, noticeFlag);
	}

	public List<GvDownloadTask> findByDownloadStatus(Object downloadStatus) {
		return findByProperty(DOWNLOAD_STATUS, downloadStatus);
	}

	public List<GvDownloadTask> findByMaterialId(Object materialId) {
		return findByProperty(MATERIAL_ID, materialId);
	}

	public List<GvDownloadTask> findByEquipmentId(Object equipmentId) {
		return findByProperty(EQUIPMENT_ID, equipmentId);
	}

	public List findAll() {
		log.debug("finding all GvDownloadTask instances");
		try {
			String queryString = "from GvDownloadTask";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	// synchronized public GvDownloadTask merge(GvDownloadTask detachedInstance)
	// {
	// log.debug("merging GvDownloadTask instance");
	// try {
	// GvDownloadTask result = (GvDownloadTask) getSession()
	// .merge(detachedInstance);
	// log.debug("merge successful");
	// return result;
	// } catch (RuntimeException re) {
	// log.error("merge failed", re);
	// throw re;
	// }
	// }

	public void attachDirty(GvDownloadTask instance) {
		log.debug("attaching dirty GvDownloadTask instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GvDownloadTask instance) {
		log.debug("attaching clean GvDownloadTask instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#size()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public int size() {
		// attilax 老哇的爪子 l_53_1 o7s
		return 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean isEmpty() {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean contains(Object o) {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public Iterator<EE> iterator() {
		// attilax 老哇的爪子 l_53_1 o7s
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public Object[] toArray() {
		// attilax 老哇的爪子 l_53_1 o7s
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(T[])
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		// attilax 老哇的爪子 l_53_1 o7s
		return null;

	}

	/*
	 * remove tmp and add the next cycle.
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean remove(Object o) {
		// attilax 老哇的爪子 l_53_1 o7s

		baseDAO c = new baseDAO();

		GvDownloadTask t_tmp = (GvDownloadTask) o;

		GvDownloadTask t = this.findById(t_tmp.getDsId());
		if (t.getNoticeFlag() == null)
			t.setNoticeFlag(0);
		t.setNoticeFlag(t.getNoticeFlag() + 1);
		c.merge(t);
		return true;

	}

	@Override
	public boolean removeNappend(Object o) {
		// attilax 老哇的爪子 l_53_1 o7s

		baseDAO c = new baseDAO();

		GvDownloadTask t_tmp = (GvDownloadTask) o;

		GvDownloadTask t = this.findById(t_tmp.getDsId());

		if (t.getRetryTimes() == null)
			t.setRetryTimes(0);
		t.setRetryTimes(t.getRetryTimes() + 1);

		// WebCfgRead
		int retime = getRetryTimes();
		if (t.getRetryTimes() >= retime) {
			t.setRetryTimes(0); // reset retrytime
			// queue point movedown
			if (t.getNoticeFlag() == null)
				t.setNoticeFlag(0);
			t.setNoticeFlag(t.getNoticeFlag() + 1);

		}

		c.merge_syn(t);
		// / merge(t);
		return true;

	}

	private int getRetryTimes() {
		try {
			String f = pathx.webAppPath() + "/noticerCfg.txt";
			Properties props = new Properties();

			props.load(new InputStreamReader(new FileInputStream(f), "gbk"));

			int retime = Integer.parseInt(props.getProperty("retrytimes"));
			return retime;
		} catch (Exception e) {
			return 7;
		}
	}

	/**
	 * 
	 @author attilax 老哇的爪子
	 * @since o8o m_r_53
	 * 
	 * @param o
	 * @return
	 */
	public boolean remove_forever(Object o) {
		// attilax 老哇的爪子 l_53_1 o7s

		baseDAO c = new baseDAO();

		GvDownloadTask t_tmp = (GvDownloadTask) o;

		GvDownloadTask t = this.findById(t_tmp.getDsId());
		t.setDownloadStatus(1);
		c.merge(t);
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean addAll(Collection<? extends EE> c) {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public void clear() {
		// attilax 老哇的爪子 l_53_1 o7s

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#add(java.lang.Object)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean add(EE ex) {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#offer(java.lang.Object)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public boolean offer(EE ex) {
		// attilax 老哇的爪子 l_53_1 o7s
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#remove()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public EE remove() {
		// attilax 老哇的爪子 l_53_1 o7s
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#poll()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public EE poll() {
		// attilax 老哇的爪子 l_53_1 o7s

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#element()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public EE element() {
		// attilax 老哇的爪子 l_53_1 o7s
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#peek()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7s l_53_1$
	 */
	@Override
	public EE peek() {
		// attilax 老哇的爪子 l_53_1 o7s
		return null;

	}

	/**
	 * @author attilax 老哇的爪子
	 * @since o7s m_9_r$
	 * 
	 * @param fetchCount
	 * @return
	 */
	public List peek(int fetchCount) {
		// attilax 老哇的爪子 m_9_r o7s
		final List li = new ArrayList();
		// new ix(i).times(new org.apache.commons.collections.Closure() {
		//
		// @Override public void execute(Object arg0) {
		// // attilax 老哇的爪子 m_k_39 o7s
		// Object o=poll();
		// li.add(o);
		//
		// }
		// });
		// noticeFlag is null
		String hql = " from GvDownloadTask   where 1=1 and downloadStatus=null   order by noticeFlag  ";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);
		q.setMaxResults(fetchCount);
		List l = q.list();
		return l;

	}

	// private Integer drId;
	// private Integer downloadFlag;
	// private Timestamp downloadOverTime;
	// private Integer programmeId;
	// private Integer materialId;
	// private Integer equipmentId;
	// private String sjFileName;
	/*
	 * (non-Javadoc) get down feedback..up downstate
	 * 
	 * @see com.attilax.api.Handler#handleReq(java.lang.Object)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o7t h_y_v$
	 */
	@Override
	public Object handleReq(Object arg) throws Exception {
		// attilax 老哇的爪子 h_y_v o7t
		JSONObject ja = JSONObject.fromObject(arg);
		int id = ja.getInt("dsId");
		final int trueDownFlag = core.toInt(ja.get("downtype"), 99);// 2 is
																	// false

		baseDAO c = new baseDAO();
		final GvDownloadTask tsk = (GvDownloadTask) c.get(GvDownloadTask.class,
				id);
		if (tsk == null)
			throw new RuntimeException("gvdownload svs:: no this id:"
					+ String.valueOf(id));
		tsk.setDownloadStatus(DownloadStatus.ok);

		// ob8
		tsk.setTrueDownFlag(trueDownFlag);

		c.merge(tsk);

		log2db(tsk);

		saveDownHstry(tsk, trueDownFlag);

		return "{\"errcode\":0,\"errmsg\":\"ok\" }";

	}

	private void log2db(GvDownloadTask tsk) {
		// attilax 老哇的爪子 上午01:49:53 2014-9-2
		System.out.println("aaaaa");
		try {
			logRec o = new logRec();
			o.setLevel(Level.info);
			o.setIdx(tsk.getRltID());
			o.setIdx2(tsk.getRltID2());
			o.setIdx3(tsk.getRltID3());
			o.setCreateTime(timeUtil.getTimestamp());
			o.setLogger("mtrback");
			o.setMsg(tsk.getSendMsg());
			o.setMsgA(tsk.getSendMsgA());
			o.setSend("recv");
			o.setThread("mtrback");
			o.setCate("mtrDownBack");
			// this.dbx.save(o);
			new baseDAO().save(o);
		} catch (Exception e) {
			core.log(e);
		}

	}

	@com.google.inject.Inject
	GvDownloadRecordSvs downRecC = IocX.getBean(GvDownloadRecordSvs.class);
	private int alreayExistMtrlNoneedDown = 2;
	private int noExistMtrlNeedDown = 1;

	public void saveDownHstry(final GvDownloadTask tsk, int trueDownFlag)
			throws IllegalAccessException, InvocationTargetException {
		try {
			if (downRecC == null)
				downRecC = IocX.getBean(GvDownloadRecordSvs.class);
			if (downRecC.AlreadyRecvMsg(tsk))// (tsk,trueDownFlag))
				return;
			if (trueDownFlag == alreayExistMtrlNoneedDown)
				return;

			final GvDownloadRecord r = new GvDownloadRecord();
			BeanUtils.copyProperties(r, tsk);
			r.setDownloadFlag(1);
			r.setDownloadOverTime(core.toTimeStamp(new Date()));
			// r.setProgrammeId(tsk.getMaterialId());

			new com.attilax.tryX<String>() {
				@Override
				public String $$(Object t) throws Exception {
					// attilax 老哇的爪子 j_m_3 o83
					GvMaterialSvs gc = new GvMaterialSvs();
					r.setSjFileName(gc.findById(tsk.getMaterialId())
							.getFilePath());
					return null;
				}
			}.$("");
			r.setTaskId(tsk.getDsId());
			// ob2
			try {
				r.setProgrammeId(tsk.getPrgrmID());
			} catch (Exception e) {
				// TODO: handle exception
			}
			new baseDAO().save(r);
		} catch (Exception e) {
			core.warn(e);
			filex.saveLog(e, "c:\\saveDownHstry");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.concur.IconcurTest#maxID(java.lang.Class)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o8o j_38_4$
	 */
	@Override
	public int maxID(Class cls) throws cantFindIDFieldEx, dataISEmptyEx {
		// attilax 老哇的爪子 j_38_4 o8o
		return new baseDAO().maxID(cls);

	}

	/*
	 * (non-Javadoc) out thred abt 50....
	 * 
	 * @see com.attilax.concur.IconcurTest#count()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o8o j_38_4$
	 */
	@Override
	public int count() {
		// attilax 老哇的爪子 j_38_4 o8o
		return 3;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.concur.IconcurTest#op()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o8o j_38_4$
	 */
	@Override
	public <k, v> Object op(Map<k, v> mapx) {
		// attilax 老哇的爪子 j_38_4 o8o
		int count = count();
		List li = peek(count);
		CollectionUtils.each_safe(li, new Closure<GvDownloadTask, Object>() {

			@Override
			public Object execute(GvDownloadTask arg0) throws Exception {
				// attilax 老哇的爪子 m_p_t o8o
				remove_forever(arg0);
				System.out.println("");
				return null;

			}
		});
		return null;

	}

	/**
	 * @author attilax 老哇的爪子
	 * @since o8r k_43_43
	 * 
	 * @param string
	 * @return
	 */
	public List findByIds(String idsx) {
		// attilax 老哇的爪子 k_43_43 o8r
		String[] ids = idsx.split(",");
		List li = Arrays.asList(ids);
		return (List) CollectionUtils.each_safe(li, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 h572 o7g

				Integer id = Integer.parseInt(arg0.toString());
				Object o = findById(id);
				if (o == null)
					throw new RuntimeException("");
				return o;

			}
		});
		// return null;

	}

	/**
	 * @author attilax 老哇的爪子
	 * @param m
	 * @return
	 * @since p14 d_50_c
	 */
	public Object stopAll(final Map<String, String> QueryPropertyssMap) {
		List<GvDownloadTask> li = (List) findbyPropFinal(QueryPropertyssMap);
		for (GvDownloadTask t : li) {
			try {
		 	stop(t.getDsId());
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return li.size();

	}

	/**
	 * org filt ,retry support
	 * 
	 * @author attilax 老哇的爪子
	 * @since p14 e_4_1
	 */
	private Object findbyPropFinal(final Map<String, String> QueryPropertyssMap) {
		// add org filter
		try {
			QueryPropertyssMap.putAll((Map) orgFilter.getExp());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return core.retry3(new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				List list_sub = findByPropertyss(QueryPropertyssMap);

				return list_sub;
			}
		}, new errEventProcess() {

			@Override
			public Object execute(Object arg0) throws Exception {

				return new ArrayList();
			}

		}
		// filex.save_SF(core.getTrace(e),
		// "c:\\downmanaQueryLog\\downmanaQueryE"+filex.getUUidName()+".txt");

				, "c:\\downmanaQueryLog");
	}

	/**
	 * @author attilax 老哇的爪子
	 * @param m
	 * @since p14 d_51_e
	 */
	public Object repushAll(Map<String, String> QueryPropertyssMap) {
		

		List<GvDownloadTask> li = (List) findbyPropFinal(QueryPropertyssMap);
		for (GvDownloadTask gvDownloadTask : li) {
			try {
		 		repush(  gvDownloadTask);
			} catch (Exception e) {
				filex.saveLog(e, "c:\\repush");
			}

		}
		return li.size();
	}

	private void repush( GvDownloadTask gvDownloadTask) {
		GvDownloadTaskSvs tc = IocX.getBean(GvDownloadTaskSvs.class);
		gvDownloadTask.setDownloadStatus(null);
		gvDownloadTask.setNotice_back(0);
		gvDownloadTask.setNoticeFlag(1);
		gvDownloadTask.setStartdownFlag(null);
		gvDownloadTask.setTrueDownFlag(null);
		gvDownloadTask.setSendMsg("");
		gvDownloadTask.setSendMsgA("");
		gvDownloadTask.setSendMsgContext("");
		gvDownloadTask.setSendRetMsg("");
		gvDownloadTask.setLastSuccRetMsg("");
		gvDownloadTask.setLastSuccTime(null);
		setOpInfo(gvDownloadTask);
		// gvDownloadTask.setOpr(opr)
		tc.dbx.merge_syn(gvDownloadTask);
	}

	protected void setOpInfo(GvDownloadTask t) {
		try {
			WebContext webContext = WebContextFactory.get();

			t.setOpr(authx.getCurUsername(webContext.getHttpServletRequest()));
			t.setOptim(timeUtil.getTimestamp());
		} catch (Exception e) {
			// TODO: handle exception
		}

		// return authx.getCurUsername

	}
}