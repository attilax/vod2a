package com.focusx.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.anno.AopX;
import com.attilax.anno.Inj;
import com.attilax.anno.aop;
import com.attilax.anno.hql;
import com.attilax.api.Handler;
import com.attilax.api.HandlerChain;
import com.attilax.db.HbSessionGetor;
import com.attilax.ioc.IocX;
import com.focusx.dao.IEquipmentDAO;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.util.HbX4vod;
import com.focusx.util.HibernateSessionFactory;


/**
 * 设备管理DAO类
 * @see com.focusx.pojo.Equipment
 * @author zhoujianbin
 */

public class EquipmentDAOImpl extends baseDao implements IEquipmentDAO {
	private SessionFactory sessionFactory;
	
	@Inj(HbX4vod.class)
	public Session getSession(){
		//return 
		HbSessionGetor sessGtr = IocX.getBean(HbSessionGetor.class);
		Session s=sessGtr.getSession(new Closure() {

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子  4_v_y   o9f 
				return sessionFactory.getCurrentSession();
				
			}});
		System.out.println("sessid:"+ core.objID(s));
		if(s!=null) return s;
		if(s==null )
		return  sessionFactory.getCurrentSession();
		return s;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 获取设备总条数
	 */
	@Override 
	public Integer getCount(@Param("hql") String hql) {
		final String countHql = "select count(*) " + hql;
		@aop Query query = (Query) new AopX() {
			@Override public Object $$(Object t) {
				return getSession().createQuery(countHql);
			}
		}.$("$injPoint",countHql);
		
		 
 
	 
		return ((Long) query.uniqueResult()).intValue();
	}
	
	

	
	
	/**
	 * 分页查询所有设备
	 */
	@Override
	public List<Equipment> getListPage(Map<String, Object> data, Pager<Equipment> pager) {
		Session session = getSession();
		StringBuffer hql = new StringBuffer(" from Equipment e where 1=1 ");
		

		if(data.containsKey("SerialCode")){
			hql.append(" and e.serialCode like '%" + data.get("SerialCode") + "%' ");
		}
		
		if(data.containsKey("mome")){
			hql.append(" and e.mome like '%" + data.get("mome") + "%' ");
		}
		
		if(data.containsKey("status")){
			hql.append(" and e.status = " + data.get("status") + " ");
		}
		
		if(data.containsKey("groupid")){
			hql.append(" and e.departId in (" + data.get("groupid") + ") ");
		}
		
		if(proFltr.get()!=null)
			hql.append(proFltr.get().getExp());
		pager.setTotal(getCount(hql.toString()));
		
		Query query = session.createQuery(hql.toString());
		
		if (pager != null) {
			query.setFirstResult(pager.getFirstResult());
			query.setMaxResults(pager.getMaxResults());
		}
		return query.list();
	}
	
	/**
	 * 
	@author attilax 老哇的爪子
		@since  o7n h_44_h$
	
	 * @param hql
	 * @param num
	 * @param session
	 * @return
	 */
	public  Object getTop(	String  hql ,int num,Session session) {
	 
		 
	 
		
	 
		
		Query query = session.createQuery(hql.toString());
		
	 
		 
			query.setMaxResults(num);
		 if(num==1)
			 return query.list().get(0);
		 else
		return query.list();
	}
	
	@Override
	public void save(Equipment equipment) {
		getSession().save(equipment);
	}

	@Override
	public void update(Equipment equipment) {
		getSession().update(equipment);
	}

	@Override
	public void delete(Equipment equipment) {
		getSession().delete(equipment);
	}

	@Override
	public Equipment getEquipment(int id) {
		Session session = getSession();
		String hql = " from Equipment e where e.equipmentId = " + id;
		Query query = session.createQuery(hql);
		return (Equipment) (query.list().size() > 0 ? query.list().get(0) : null);
		
	}
	
	/**
	 * 根据机身串码查询
	 */
	@Override
	public List<Equipment> checkSerialCode(String serialCode) {
		Session session = getSession();
		String hql = " from Equipment e where e.serialCode = '" + serialCode + "'";
		
		Query query = session.createQuery(hql);
		return query.list();
	}

	/**
	 * 根据设备ID集合查询
	 */
	@Override
	public List<Equipment> getListByIds(String Ids) {
		Session session = getSession();
		String hql = " from Equipment e where e.equipmentId in (" + Ids + ") ";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public List<Equipment> getListByDepartId(String departIds) {
		Session session = getSession();
		String hql = " from Equipment e where e.departId in (" + departIds + ") ";
		Query query = session.createQuery(hql);
		return query.list();
	}


	
	
}