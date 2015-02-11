package com.focusx.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.focusx.dao.IEquipmentMonitorfDAO;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentMonitor;
import com.focusx.pojo.EquipmentVeriyf;


/**
 * 设备监控DAO
 * @author zhoujianbin
 *
 */
public class EquipmentMonitorDAOImpl implements IEquipmentMonitorfDAO {
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void delete(EquipmentMonitor equipmentMonitor) {
		getSession().delete(equipmentMonitor);
	}

	@Override
	public Integer getCount(String sql) {
		String countSql = "select count(*) " + sql;
		Query query = getSession().createSQLQuery(countSql);
		return (Integer) query.uniqueResult();
	}

	@Override
	public EquipmentMonitor getEquipmentMonitor(int id) {
		String hql = " from EquipmentMonitor em where em.emId = " + id;
		Query query = getSession().createQuery(hql);
		return (EquipmentMonitor) query.list().get(0);
	}

	@Override
	public List<EquipmentMonitor> getListPage(Map<String, Object> data,
			Pager<EquipmentMonitor> pager) {
		Session session = getSession();
		StringBuffer hql = new StringBuffer(" from gv_equipment_monitor em where 1=1 ");
		
		if(data.containsKey("groupid")){
			hql.append(" and em.depart_id in (" + data.get("groupid") + ")");
		}
		
		if(data.containsKey("status")){
			hql.append(" and em.status = " + data.get("status") + " ");
		}
		
		if(data.containsKey("lineFlag")){
			hql.append(" and em.equipment_line_flag = '" + data.get("lineFlag") + "' ");
		}
		
		if(data.containsKey("equipmentStatus")){
			hql.append(" and em.equipment_status = '" + data.get("equipmentStatus") + "' ");
		}
		pager.setTotal(getCount(hql.toString()));
		hql.append(" order by em.equipment_status asc");//oa5
	//	hql.append(" order by em.equipment_id desc");
		
		
		Query query = session.createSQLQuery("select em.* " + hql.toString()).addEntity(EquipmentMonitor.class);//.createQuery(hql.toString());
		
		if (pager != null) {
			query.setFirstResult(pager.getFirstResult());
			query.setMaxResults(pager.getMaxResults());
		}
		return query.list();
	}

	@Override
	public void save(EquipmentMonitor equipmentMonitor) {
		getSession().save(equipmentMonitor);
	}

	@Override
	public void update(EquipmentMonitor equipmentMonitor) {
		getSession().update(equipmentMonitor);
		//getSession().flush();
	}

	@Override
	public EquipmentMonitor getEmBySerialCode(String serialCode) {
		String hql = " from EquipmentMonitor em where em.serialCode = '" + serialCode + "'";
		Query query = getSession().createQuery(hql);
		return  query.list().size() > 0 ? ((EquipmentMonitor)query.list().get(0)) : null;
	}

	@Override
	public List<EquipmentMonitor> getEmList() {
		Session session = getSession();
		Query query = session.createQuery(" from EquipmentMonitor ");
		return query.list();
	}

	@Override
	/**
	 * todox o9f 
	 * hb bind do
	 */
	public List<Equipment> getNoMonitor() {
		Session session = getSession();
		String sql = "select e.* from gv_equipment e left join gv_equipment_monitor em on e.equipment_id = em.equipment_id where em.equipment_id is null";
		Query query = session.createSQLQuery(sql).addEntity(Equipment.class);//.createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<EquipmentMonitor> getRedundancyMonitor() {
		Session session = getSession();
		String sql = "select em.* from gv_equipment_monitor em  left join gv_equipment e on e.equipment_id = em.equipment_id where e.equipment_id is null";
		Query query = session.createSQLQuery(sql).addEntity(EquipmentMonitor.class);//.createQuery(hql.toString());
		return query.list();
	}
}