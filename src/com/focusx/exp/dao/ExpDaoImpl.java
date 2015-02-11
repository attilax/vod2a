package com.focusx.exp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.errEventProcess;
import com.attilax.io.filex;
import com.focusx.util.DateUtil;

@SuppressWarnings("all")
public class ExpDaoImpl implements IExpDao {

	protected static Logger logger = Logger.getLogger(ExpDaoImpl.class);
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List playWater(final Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub 
		return	 (List) core.retry3(new Closure () {
			@Override
			public Object execute(Object arg0) throws Exception {
				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
					
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				
				String sql = "{Call proc_playwater(?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(end_time));
				query.setParameter(4, equipmentIds); 
				return query.list(); 
			}
		}, new errEventProcess(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\playWaterQueryLog");
	}

	@Override
	public List<Object[]> playCount(final Map<String, String> QueryPropertyssMap) {
		
		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception {

				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
					
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				
				String sql = "{Call proc_playcount(?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(end_time));
				query.setParameter(4, equipmentIds); 
				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\playCountQueryLog");
		
	}

	@Override
	public List<Object[]> downWater(final Map<String, String> QueryPropertyssMap) { 
		
		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception { 
				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				
				String sql = "{Call proc_downwater(?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(end_time));
				query.setParameter(4, equipmentIds); 

				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\downWaterQueryLog");
		
	}

	@Override
	public List<Object[]> downMana(final Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception { 

				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
					
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				
				String sql = "{Call proc_downmana(?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(end_time));
				query.setParameter(4, equipmentIds);
				// query.executeUpdate(); //没有返回值
				// query.list();//有返回值

				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\downManaQueryLog");
	}

	@Override
	public List<Object[]> downCount(final Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub

		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception { 


				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
					
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				
				String sql = "{Call proc_downcount(?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(end_time));
				query.setParameter(4, equipmentIds);
				// query.executeUpdate(); //没有返回值
				// query.list();//有返回值

				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\downCountQueryLog");
		
	}

	@Override
	public List publish(final Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception { 


				String equipmentName = null;
				if (QueryPropertyssMap.containsKey("equipmentName")) {
					equipmentName = QueryPropertyssMap.get("equipmentName");
				}
				int publishType = 0;
				if (QueryPropertyssMap.containsKey("publishType")) {
					publishType = Integer.valueOf(QueryPropertyssMap.get("publishType")); 
				}
				String progarmmeName = null;
				if (QueryPropertyssMap.containsKey("progarmmeName")) {
					progarmmeName = QueryPropertyssMap.get("progarmmeName");
				}
				int publishStatus = 0;
				if (QueryPropertyssMap.containsKey("publishStatus")) {
					publishStatus = Integer.valueOf(QueryPropertyssMap.get("publishStatus")); 
				}
				String mome = null;
				if (QueryPropertyssMap.containsKey("mome")) {
					mome = QueryPropertyssMap.get("mome");
				}
				String publishManName = null;
				if (QueryPropertyssMap.containsKey("publishManName")) {
					publishManName = QueryPropertyssMap.get("publishManName");
				} 
				
				String beginTime = QueryPropertyssMap.get("beginTime");
				String endTime = QueryPropertyssMap.get("endTime");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				String groupid = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupid = QueryPropertyssMap.get("groupid");
				}
				
				String sql = "{Call proc_publish(?,?,?,?,?,?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, equipmentName);
				query.setParameter(1, publishType);
				query.setParameter(2, progarmmeName);
				query.setParameter(3, publishStatus);
				query.setParameter(4, mome);
				query.setParameter(5, publishManName);
				query.setParameter(6, beginTime);
				query.setParameter(7, endTime);
				query.setParameter(8, equipmentIds);
				query.setParameter(9, groupid);
				// query.executeUpdate(); //没有返回值
				// query.list();//有返回值

				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\publishQueryLog");
	}

	@Override
	public List expStore(final Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
				return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
					@Override
					public Object execute(Object arg0) throws Exception { 


						String groupname = "";
						if (QueryPropertyssMap.containsKey("groupname")) {
							groupname = QueryPropertyssMap.get("groupname");
						}
						String groupid = "";
						if (QueryPropertyssMap.containsKey("groupid")) {
							groupid = QueryPropertyssMap.get("groupid"); 
						} 
						String parentId = "";
						if (QueryPropertyssMap.containsKey("parentId")) {
							parentId = QueryPropertyssMap.get("parentId"); 
						} 
						String grpIds = "";
						if (QueryPropertyssMap.containsKey("grpIds")) {
							grpIds = QueryPropertyssMap.get("grpIds");
						}
						
						String sql = "{Call proc_store(?,?,?,?)}";
						Query query = getSession().createSQLQuery(sql);
						query.setParameter(0, groupname);
						query.setParameter(1, groupid); 
						query.setParameter(2, parentId); 
						query.setParameter(3, grpIds); 

						return query.list();
					}
				}, new errEventProcess<Object, Object>(){ 
					@Override
					public List execute(Object arg0) throws Exception {
						 return new ArrayList();
					}
					
				} 
				,	"c:\\exp\\storeQueryLog");
	}
	

	@Override
	public List<Object[]> playCountByMtrlArea(final Map<String, String> QueryPropertyssMap) {
		
		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception {

				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
					
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				} 
				//proc_playcountbymtrlarea [2756, , , , null]
				String sql = "{Call proc_playcountbymtrlarea(?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(end_time));
				query.setParameter(4, equipmentIds); 
				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\playCountQueryLog");
		
	}

	@Override
	public List downCountByMtarlBranch(final Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub

		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception { 


				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
					
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				
				String sql = "{Call proc_downcountbymtarlbranch(?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(end_time));
				query.setParameter(4, equipmentIds);
				// query.executeUpdate(); //没有返回值
				// query.list();//有返回值

				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\downCountbymtarlbranchQueryLog");
	}

	@Override
	public List downCountByMtarlStore(final Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub

		return	 (List<Object[]>) core.retry3(new Closure<Object, Object> () {
			@Override
			public Object execute(Object arg0) throws Exception { 


				String groupids = null;
				if (QueryPropertyssMap.containsKey("groupid")) {
					groupids = QueryPropertyssMap.get("groupid");
				}
				String material_id = "";
				if (QueryPropertyssMap.containsKey("materialId")) {
					material_id = QueryPropertyssMap.get("materialId");
					
				}

				String stat_time = QueryPropertyssMap.get("downloadCreateTime_start");
				String end_time = QueryPropertyssMap.get("downloadCreateTime_end");
				
				String equipmentIds = null;
				if (QueryPropertyssMap.containsKey("equipmentIds")) {
					equipmentIds = QueryPropertyssMap.get("equipmentIds");
				}
				String downloadStatus = null;
				if (QueryPropertyssMap.containsKey("downloadStatus")) {
					downloadStatus = QueryPropertyssMap.get("downloadStatus");
				}
				
				String sql = "{Call proc_downcountbymtarlstore(?,?,?,?,?,?)}";
				Query query = getSession().createSQLQuery(sql);
				query.setParameter(0, groupids);
				query.setParameter(1, material_id);
				query.setParameter(2, DateUtil.formatDate(stat_time));
				query.setParameter(3, DateUtil.formatDate(DateUtil.formatDate(end_time)));
				query.setParameter(4, equipmentIds);
				query.setParameter(5, downloadStatus);
				//filex.save_SF(downloadStatus, "c:\\exp\\downCountQueryLog\\sss.txt");
				// query.executeUpdate(); //没有返回值
				// query.list();//有返回值

				return query.list();
			}
		}, new errEventProcess<Object, Object>(){ 
			@Override
			public List execute(Object arg0) throws Exception {
				 return new ArrayList();
			}
			
		} 
		,	"c:\\exp\\downCountQueryLog");
	}

	
	public static void main(String[] arg){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
		  String dateString = "";
		  System.out.println(DateUtil.formatDate(dateString)); 
	}
	
	
}
