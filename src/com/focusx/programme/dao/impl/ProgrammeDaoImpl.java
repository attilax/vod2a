package com.focusx.programme.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.bsf.Main;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 



import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.collection.CollX;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.focusx.dao.impl.baseDao;
import com.focusx.eq.CantFindAnyDeviceEx;
import com.focusx.eq.EqX;
import com.focusx.pager.Pager;
import com.focusx.programme.dao.IProgrammeDao;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.service.impl.ProgrammeServiceImpl;
import com.focusx.publish.entity.GvPublish;
import com.focusx.util.Utils;

public class ProgrammeDaoImpl  extends baseDao implements IProgrammeDao   {

	protected static Logger logger = Logger.getLogger(ProgrammeDaoImpl.class);
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

	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:批量删除数据
	  *  
	  * @CreateTime: 2014-7-15 下午03:13:13
	  *
	  * @param ids
	  * @return
	 */
	@Override
	public boolean  deleteBatch(List<Integer> ids){
		boolean msg = true;
		try {
			String hql = "update Gv_Programme  set logicdel=1 where  progarmme_Id in ("+ CollX.toString(ids )+")";
			Query query = getSession().createSQLQuery(hql); 
		//	query.setParameterList("ids", ids);
			int num = query.executeUpdate();
			if (num <= 0) {
				msg = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = false;
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(),e);
		}
		return msg;
	}
	
	@Override
	public boolean delete(Integer id) {
		 
		
		boolean msg = true;
		try {
			String hql = "update gv_programme   set logicdel=1  where progarmme_Id =  "+id.toString();
			Query query = getSession().createSQLQuery(hql); 
		//	query.setInteger(0, id);
			int num = query.executeUpdate();
			if (num <= 0) {
				msg = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = false;
			e.printStackTrace();
		}
		return msg;
	}
	
	@Override
	public GvProgramme getProgramme(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from GvProgramme d where 1=1 and d.progarmmeId = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		List<GvProgramme> list = query.list();
		if (Utils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<GvProgramme> getProgrammeList(Pager<GvProgramme> pager,
			Map conditionMap) {
	 
		List<GvProgramme> rsList = new ArrayList<GvProgramme>();
		
		List<Object> args = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("select g.progarmme_id,g.[describe],g.screen,g.total_duration,g.material_number,g.create_man,g.create_time,u.Name as createManName ");
		
		// from gv_programme g , t_user_users u 
		StringBuffer fromSql = new StringBuffer(" from gv_programme g left join  t_user_users u  on   g.create_man = u.ID ");
		
		 //and g.create_man = u.ID
		StringBuffer condition = new StringBuffer("where 1 = 1  ");

		if (conditionMap.containsKey("describe")) {
			condition.append(" and g.[describe] like ?  ");
			args.add("%" + conditionMap.get("describe") + "%");
		}
		if (conditionMap.containsKey("screen")) {
			condition.append(" and g.screen = ? ");
			args.add(conditionMap.get("screen"));
		}
		if (conditionMap.containsKey("totalDuration")) {
			condition.append(" and g.total_duration = ? ");
			args.add(conditionMap.get("totalDuration"));
		}
 
		if (conditionMap.containsKey("materialNumber")) {
			condition.append(" and g.material_number = ? ");
			args.add(conditionMap.get("materialNumber"));
		}
		if (conditionMap.containsKey("createManName")) {
			condition.append(" and u.name like ?  ");
			args.add("%"+conditionMap.get("createManName")+"%");
		}
		
		if (conditionMap.containsKey("beginTime")) {
			condition.append(" and g.create_time > ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("beginTime").toString()));
		}
		
		if (conditionMap.containsKey("endTime")) {
			condition.append(" and g.create_time < ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("endTime").toString()));
		}

		//ob4 4 review filt
				if(proFltr.get()!=null)
					condition.append(proFltr.get().getExp());
		
		fromSql.append(condition);
		
		
		// 获取分页总数
		if (pager != null) {
			//formsql= from gv_programme g left join  t_user_users u  on   g.create_man = u.ID where 1 = 1 
			generatePageTotalCount(fromSql.toString(), args, pager);
		}

		sql.append(fromSql).append(" order by g.create_time desc ");
		filex.save_SF(sql.toString(), "c:\\sql_queryPrgrm.txt");
		logger.info("参数：" + args);
		Query query = getSession().createSQLQuery(sql.toString());
		// 给参数注入值
		if (Utils.isNotEmpty(args)) {
			int i = 0;
			for (Object str : args) {
				query.setParameter(i++, str);
			}
		}
		// 插入分页参数
		if (pager != null) {
			query.setFirstResult(pager.getFirstResult());
			query.setMaxResults(pager.getMaxResults());
		}
		@SuppressWarnings("rawtypes")
		List list = query.list();
		if (Utils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				GvProgramme gvProgramme = new GvProgramme();
				int i = 0;
				gvProgramme.setProgarmmeId(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setDescribe(String.valueOf(obj[i++])); 
				gvProgramme.setScreen(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setTotalDuration(Utils.convertStringToDouble(String.valueOf(obj[i++])));
				gvProgramme.setMaterialNumber(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setCreateMan(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setCreateTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				 
				gvProgramme.setCreateManName(String.valueOf(obj[i++]));
				rsList.add(gvProgramme);
			}
			return rsList;
		}
		return list;
	}

	@Override
	public boolean insert(GvProgramme dictionary) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			getSession().save(dictionary);
		} catch (Exception e) {
			 
			msg = false;
			e.printStackTrace();
			System.out.println("--");
			// org.hibernate.cfg.AnnotationConfiguration.
			AnnotationConfiguration an=new 	 org.hibernate.cfg.AnnotationConfiguration();
		//	an.gete
			throw new RuntimeException(e.getMessage(), e);
		}
		return msg;
	}

	@Override
	public boolean update(GvProgramme dictionary) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			getSession().update(dictionary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = false;
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 该方法会改变参数page的totalCount字段
	 * 
	 * @param originHql
	 *            原始hql语句
	 * @param params
	 *            原始参数
	 * @param page
	 *            页面对象
	 */
	private void generatePageTotalCount(String hql, List<Object> args,
			Pager<GvProgramme> pager) {
		String generatedCountHql = "select count(*) " + hql;
		Query query = getSession().createSQLQuery(generatedCountHql);
		if (Utils.isNotEmpty(args)) {
			int i = 0;
			for (Object str : args) {
				query.setParameter(i++, str);
			}
		}
		System.out.println(args);
		int total = Integer.valueOf(String.valueOf(query.uniqueResult()));
		ProgrammeServiceImpl.totalRowsCount.set(total);
		pager.setTotal(total);
	}
	
	/**
	 * @author leo
	 * 获取节目单门店接收情况
	 * @param pager
	 * @param conditionMap
	 * @return list
	 */
	public List<GvProgramme> getProgrammeReceiveList(Pager<GvProgramme> pager,Map<String, Object> conditionMap) {
		
		List<GvProgramme> rsList = new ArrayList<GvProgramme>();
		List<Object> args = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer(" select gp.progarmme_id,gp.describe,p.publish_time,g.groupid,g.groupname,e.equipment_id,e.mome,c.del ");
		
		StringBuffer fromSql = new StringBuffer(" from gv_cycleQueue c ");
		fromSql.append(" left join gv_publish p on c.rltRecID = p.publish_id ");
		fromSql.append(" left join gv_programme gp on gp.progarmme_id = p.progarmme_id ");
		fromSql.append(" left join gv_equipment e on e.equipment_id = p.equipment_id ");
		fromSql.append(" left join t_mb_group g on e.depart_id=g.groupid ");
		fromSql.append(" where e.equipment_id is not null ");
		
		if (conditionMap.containsKey("progarmmeId")) {
			fromSql.append(" and gp.progarmme_id = ?  ");
			args.add(conditionMap.get("progarmmeId"));
		}
		if (conditionMap.containsKey("del")) {
			fromSql.append(" and c.del = ?  ");
			args.add(conditionMap.get("del"));
		}
		if (conditionMap.containsKey("groupid")) {
			fromSql.append(" and g.groupid in("+conditionMap.get("groupid")+") ");
		}
		if (conditionMap.containsKey("startTime")) {
			fromSql.append(" and p.publish_time >= ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("startTime").toString()+" 00:00:00"));
		}
		if (conditionMap.containsKey("overTime")) {
			fromSql.append(" and p.publish_time <= ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("overTime").toString()+" 00:00:00"));
		}
		
		//分公司数据
		fromSql.append(getExp());
			
		
		
		// 获取分页总数
		if (pager != null) {
			generatePageTotalCount(fromSql.toString(), args, pager);
		}
		sql.append(fromSql).append(" order by p.publish_time desc ");
		
		 filex.save_SF(sql.toString(), "c:\\y\\sql.txt");
		
		//filex.save_SF(conditionMap.toString(), "c:\\y\\log.txt");
		//filex.save_SF(sql.toString(), "c:\\y\\sql.txt");
		logger.info("参数：" + args);
		Query query = getSession().createSQLQuery(sql.toString());
		// 给参数注入值
		if (Utils.isNotEmpty(args)) {
			int i = 0;
			for (Object str : args) {
				query.setParameter(i++, str);
			}
		}
		// 插入分页参数
		if (pager != null) {
			query.setFirstResult(pager.getFirstResult());
			query.setMaxResults(pager.getMaxResults());
		}
		List<Object> list = query.list();
		if (Utils.isNotEmpty(list)) {
			for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				GvProgramme gvProgramme = new GvProgramme();
				int i = 0;
				//gp.progarmme_id,gp.describe,gp.create_time,g.groupid,g.groupname,e.equipment_id,e.mome,c.del
				gvProgramme.setProgarmmeId(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setDescribe(String.valueOf(obj[i++])); 
				gvProgramme.setCreateTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				gvProgramme.setGroupid(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setGroupname(String.valueOf(obj[i++]));
				gvProgramme.setEquipmentid(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setMome(String.valueOf(obj[i++]));
				gvProgramme.setDel(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				rsList.add(gvProgramme);
			}
			return rsList;
		}
		return null;
	}
	
	/**
	 * @author leo
	 * 获取节目单门店接收情况,查询条件下的所有数据,数据导出
	 * @param pager
	 * @param conditionMap
	 * @return list
	 */
	public List<GvProgramme> getAllProgrammeReceiveList(Map<String, Object> conditionMap) {
	 
		List<GvProgramme> rsList = new ArrayList<GvProgramme>();
		List<Object> args = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer(" select gp.progarmme_id,gp.describe,p.publish_time,g.groupid,g.groupname,e.equipment_id,e.mome,c.del ");
		
		StringBuffer fromSql = new StringBuffer(" from gv_cycleQueue c ");
		fromSql.append(" left join gv_publish p on c.rltRecID = p.publish_id ");
		fromSql.append(" left join gv_programme gp on gp.progarmme_id = p.progarmme_id ");
		fromSql.append(" left join gv_equipment e on e.equipment_id = p.equipment_id ");
		fromSql.append(" left join t_mb_group g on e.depart_id=g.groupid ");
		fromSql.append(" where e.equipment_id is not null ");
		
		if (conditionMap.containsKey("progarmmeId")) {
			fromSql.append(" and gp.progarmme_id = ?  ");
			args.add(conditionMap.get("progarmmeId"));
		}
		if (conditionMap.containsKey("del")) {
			fromSql.append(" and c.del = ?  ");
			args.add(conditionMap.get("del"));
		}
		if (conditionMap.containsKey("groupid")) {
			fromSql.append(" and g.groupid in("+conditionMap.get("groupid")+") ");
		}
		if (conditionMap.containsKey("startTime")) {
			fromSql.append(" and p.publish_time >= ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("startTime").toString()+" 00:00:00"));
		}
		if (conditionMap.containsKey("overTime")) {
			fromSql.append(" and p.publish_time <= ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("overTime").toString()+" 00:00:00"));
		}
		
		//分公司数据
		fromSql.append(getExp());

		sql.append(fromSql).append(" order by p.publish_time desc ");
		
		Query query = getSession().createSQLQuery(sql.toString());
		// 给参数注入值
		if (Utils.isNotEmpty(args)) {
			int i = 0;
			for (Object str : args) {
				query.setParameter(i++, str);
			}
		}
		List<Object> list = query.list();
		if (Utils.isNotEmpty(list)) {
			for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				GvProgramme gvProgramme = new GvProgramme();
				int i = 0;
				gvProgramme.setProgarmmeId(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setDescribe(String.valueOf(obj[i++])); 
				gvProgramme.setCreateTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				gvProgramme.setGroupid(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setGroupname(String.valueOf(obj[i++]));
				gvProgramme.setEquipmentid(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvProgramme.setMome(String.valueOf(obj[i++]));
				gvProgramme.setDel(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				rsList.add(gvProgramme);
			}
			return rsList;
		}
		return null;
	}
	
		public String getExp() {
			try {
				HttpServletRequest request = ServletActionContext
						.getRequest();
			  String eqIds=EqX.FindByLoginUid(request);			
			
				 return " and e.equipment_id in (@ids) ".replaceAll("@ids", eqIds);
				
			}   catch (isSuperAdminEx e) {
				return "  ";
			} catch (CantFindAnyDeviceEx e) {
				return "   and e.equipment_id=8888  ";
			}
		}
	
}

 