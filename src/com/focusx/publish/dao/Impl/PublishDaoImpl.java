package com.focusx.publish.dao.Impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.attilax.core;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.focusx.pager.Pager;
import com.focusx.publish.dao.IPublishDao;
import com.focusx.publish.entity.GvPublish;
import com.focusx.util.Sys;
import com.focusx.util.Utils;
import com.focusx.push.spot;
 
public class PublishDaoImpl implements IPublishDao {
	protected static Logger logger = Logger.getLogger(PublishDaoImpl.class);
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

public static ThreadLocal<PropFilter> proFltr=new ThreadLocal<PropFilter>();
	@Override
	public List<GvPublish> getPublishList(Pager<GvPublish> pager,
			Map conditionMap) {
		// TODO Auto-generated method stub
		
		List<GvPublish> rsList = new ArrayList<GvPublish>();
		
		List<Object> args = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("Select p.publish_id,p.publish_type,p.interrupted_start_time,p.start_time,p.end_time,p.status,p.publish_man_id,p.publish_time,p.review_man_id,p.review_time,p.progarmme_id,p.equipment_id,p.mome,e.mome as equipmentName, u.Name as publishManName, g.[describe] as progarmmeName ");
		
		StringBuffer fromSql = new StringBuffer(" from  gv_publish p left join gv_equipment e on p.equipment_id = e.equipment_id left join t_user_users u  on u.ID = p.publish_man_id left join gv_programme g on p.progarmme_id = g.progarmme_id   ");
		
		 
	//	String str = "where 1 = 1 and  p.equipment_id = e.equipment_id and p.publish_man_id = u.ID and p.progarmme_id = g.progarmme_id";
		StringBuffer condition = new StringBuffer(" where 1 = 1 ");
		//发布描述
		if (conditionMap.containsKey("mome")) {
			condition.append(" and p.mome like ?  ");
			args.add("%" + conditionMap.get("mome") + "%");
		}
		//设备
		if (conditionMap.containsKey("equipmentName")) {
			condition.append(" and e.mome like ?  ");
			args.add("%" + conditionMap.get("equipmentName") + "%");
		}
		//类型
		if (conditionMap.containsKey("publishType")) {
			condition.append(" and p.publish_type = ? ");
			args.add(conditionMap.get("publishType"));
		}
		//原节目执行状态
		if (conditionMap.containsKey("publishStatus")) {
			condition.append(" and p.status = ? ");
			args.add(conditionMap.get("publishStatus"));
		}
		//设备
		if (conditionMap.containsKey("publishManName")) {
			condition.append(" and u.name like ?  ");
			args.add("%" + conditionMap.get("publishManName") + "%");
		}
		//节目单
		if (conditionMap.containsKey("progarmmeName")) {
			condition.append(" and g.[describe] like ?  ");
			args.add("%" + conditionMap.get("progarmmeName") + "%");
		}
		
		if (conditionMap.containsKey("beginTime")) {
			condition.append(" and p.publish_time > ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("beginTime").toString()));
		}

		if (conditionMap.containsKey("endTime")) {
			condition.append(" and p.publish_time < ?  ");
			args.add(Timestamp.valueOf(conditionMap.get("endTime").toString()));
		}
		
		

		fromSql.append(condition);
		if(proFltr.get()!=null)
			fromSql.append(proFltr.get().getExp());
		// 获取分页总数
		if (pager != null) {
			generatePageTotalCount(fromSql.toString(), args, pager);
		}

		sql.append(fromSql).append(" order by p.publish_time desc ");
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
		List list = query.list();
		if (Utils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				GvPublish gvPublish = new GvPublish();
				int i = 0;
				gvPublish.setPublishId(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvPublish.setPublishType(Utils.convertStringToInteger(String.valueOf(obj[i++]))); 
				gvPublish.setInterruptedStartTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				gvPublish.setStartTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				gvPublish.setEndTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				gvPublish.setStatus(Utils.convertStringToInteger(String.valueOf(String.valueOf(obj[i++]))));
				gvPublish.setPublishManId(Utils.convertStringToInteger(String.valueOf(String.valueOf(obj[i++]))));
				gvPublish.setPublishTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				gvPublish.setReviewManId(Utils.convertStringToInteger(String.valueOf(String.valueOf(obj[i++]))));
				gvPublish.setReviewTime(Utils.convertObjectToTimestamp(String.valueOf(obj[i++])));
				gvPublish.setProgarmmeId(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvPublish.setEquipmentId(Utils.convertStringToInteger(String.valueOf(obj[i++])));
				gvPublish.setMome(String.valueOf(obj[i++]));
				
				gvPublish.setEquipmentName(String.valueOf(obj[i++]));
				gvPublish.setPublishManName(String.valueOf(obj[i++]));
				gvPublish.setProgarmmeName(String.valueOf(obj[i++]));
				rsList.add(gvPublish);
			}
			return rsList;
		}
		return null;
	}

	@Override
	public GvPublish getPublish(Integer publishId) {
		// TODO Auto-generated method stub
		String hql = "from GvPublish d where 1=1 and d.publishId = ?";
		
		Query query = getSession().createQuery(hql);
		String sql = "";
		getSession().createSQLQuery(sql).addEntity(GvPublish.class);
		query.setInteger(0, publishId);
		List<GvPublish> list = query.list();
		if (Utils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean insert(GvPublish publish) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			getSession().save(publish);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = false;
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return msg;
	}

	@Override
 public boolean insertBatch(List<GvPublish> list) {
		// TODO Auto-generated method stub
		boolean msg = true;
		// Provider<T>
		try {
			for (GvPublish publish : list) {
				Session session = getSession();
				core.log(core.objID(session));
			//	publish.setPublishId(publishId)
				
				session.save(publish);
				// o99
				spot sp = IocX.getBean(spot.class);
				sp.pushNappMtrPush(publish, session);

			}
			// for (int i = 0; i < list.size(); i++) {
			// GvPublish publish = list.get(i);
			// getSession().save(publish);
			// // Sys.sessVar.set(getSession());
			// // Sys.pubVar.set(publish);
			//
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = false;
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return msg;
	}

	@Override
	public boolean update(GvPublish publish) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			getSession().update(publish);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = false;
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			String hql = "delete from GvPublish d where d.publishId = ? ";
			Query query = getSession().createQuery(hql);
			query.setInteger(0, id);
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
	public boolean deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			String hql = "delete from GvPublish d where d.publishId in (:ids)";
			Query query = getSession().createQuery(hql);
			query.setParameterList("ids", ids);
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
	public boolean isExist(List<Integer> ids) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			String hql = "select count(d.publishId) from GvPublish d where d.progarmmeId in (:ids)";
			Query query = getSession().createQuery(hql);
			query.setParameterList("ids", ids);
			Object obj = query.uniqueResult();
			if(obj!=null){
				if(Integer.valueOf(obj.toString())<=0){
					msg = false;
				}
			} 
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
			Pager<GvPublish> pager) {
		String generatedCountHql = "select count(*) " + hql;
		Query query = getSession().createSQLQuery(generatedCountHql);
		if (Utils.isNotEmpty(args)) {
			int i = 0;
			for (Object str : args) {
				query.setParameter(i++, str);
			}
		}
		int total = Utils.convertStringToInteger(String.valueOf(query.uniqueResult()));
		pager.setTotal(total);
	}
	
}
