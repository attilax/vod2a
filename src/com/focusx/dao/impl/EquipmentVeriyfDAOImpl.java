package com.focusx.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.focusx.dao.IEquipmentVeriyfDAO;
import com.focusx.pager.Pager;
import com.focusx.pojo.EquipmentVeriyf;


/**
 * 设备验证流水
 * @author zhoujianbin
 *
 */
public class EquipmentVeriyfDAOImpl implements IEquipmentVeriyfDAO {
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
	public void delete(EquipmentVeriyf equipmentVeriyf) {
		getSession().delete(equipmentVeriyf);
	}

	@Override
	public Integer getCount(String sql) {
		String countSql = "select count(*) " + sql;
		Query query = getSession().createSQLQuery(countSql);
		return (Integer) query.uniqueResult();
	}

	@Override
	public EquipmentVeriyf getEquipmentVeriyf(int id) {
		String hql = " from EquipmentVeriyf ev where ev.mvId = " + id;
		Query query = getSession().createQuery(hql);
		return (EquipmentVeriyf) query.list().get(0);
	}

	@Override
	public List<EquipmentVeriyf> getListPage(Map<String, Object> data,
			Pager<EquipmentVeriyf> pager) {
		Session session = getSession();
		StringBuffer hql = new StringBuffer(" from gv_equipment_veriyf ev left join gv_equipment e on ev.equipment_id = e.equipment_id where 1=1");
		
		if(data.containsKey("groupid")){
			hql.append(" and ev.equipment_id is not null and e.depart_id in (" + data.get("groupid") + ")");
		}
		
		if(data.containsKey("systemFlag")){
			hql.append(" and ev.system_flag = " + data.get("systemFlag") + " ");
		}
		
		if(data.containsKey("veriyfStartTime")){
			hql.append(" and ev.veriyf_time >= '" + data.get("veriyfStartTime") + "' ");
		}
		
		if(data.containsKey("veriyfEndTime")){
			hql.append(" and ev.veriyf_time <= '" + data.get("veriyfEndTime") + "' ");
		}
		pager.setTotal(getCount(hql.toString()));
		
		hql.append(" order by ev.veriyf_time desc");
		
		
		Query query = session.createSQLQuery("select ev.* " + hql.toString()).addEntity(EquipmentVeriyf.class);//.createQuery(hql.toString());
		
		if (pager != null) {
			query.setFirstResult(pager.getFirstResult());
			query.setMaxResults(pager.getMaxResults());
		}
		return query.list();
	}

	@Override
	public void save(EquipmentVeriyf equipmentVeriyf) {
		getSession().save(equipmentVeriyf);
	}

	@Override
	public void update(EquipmentVeriyf equipmentVeriyf) {
		getSession().update(equipmentVeriyf);
	}
}