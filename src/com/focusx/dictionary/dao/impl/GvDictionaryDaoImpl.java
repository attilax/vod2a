package com.focusx.dictionary.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.focusx.dictionary.dao.IGvDictionaryDao;
import com.focusx.dictionary.entity.GvDictionary;
import com.focusx.pager.Pager;
import com.focusx.util.Utils;

public class GvDictionaryDaoImpl implements IGvDictionaryDao {
	protected static Logger logger = Logger.getLogger(GvDictionaryDaoImpl.class);
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
	public boolean  batchdelete(List<Integer> ids){
		boolean msg = true;
		try {
			String hql = "delete from GvDictionary d where d.id in (:ids)";
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
	
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		
		boolean msg = true;
		try {
			String hql = "delete from GvDictionary d where d.id = ? ";
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

	public GvDictionary getDictionary(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from GvDictionary d where 1=1 and d.id = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		List<GvDictionary> list = query.list();
		if (Utils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	public GvDictionary  getDictionaryByCodeAndPcode(String DCode,String DPcode){
		// TODO Auto-generated method stub
		String hql = "from GvDictionary d where 1=1 and d.DCode = ? and d.DPcode = ? ";
		Query query = getSession().createQuery(hql);
		query.setString(0, DCode);
		query.setString(1, DPcode);
		List<GvDictionary> list = query.list();
		if (Utils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public List<GvDictionary> getDictionaryList(Pager<GvDictionary> pager,
			Map conditionMap) {
		// TODO Auto-generated method stub
		List<Object> args = new ArrayList<Object>();

		StringBuffer hql = new StringBuffer("from GvDictionary d where 1=1 ");

		if (conditionMap.containsKey("DCode")) {
			hql.append(" and d.DCode like ? ");
			args.add("%"+conditionMap.get("DCode")+"%");
		}
		if (conditionMap.containsKey("DName")) {
			hql.append(" and d.DName = ? ");
			args.add(conditionMap.get("DName"));
		}

		if (conditionMap.containsKey("DPcode")) {
			hql.append(" and d.DPcode = ? ");
			args.add(conditionMap.get("DPcode"));
		}
		if (conditionMap.containsKey("DIsvalid")) {
			hql.append(" and d.DIsvalid = ? ");
			args.add(conditionMap.get("DIsvalid"));
		}
		if (conditionMap.containsKey("DLevel")) {
			hql.append(" and DLevel = ?  ");
			args.add(conditionMap.get("DLevel"));
		}
		if (conditionMap.containsKey("DRemar")) {
			hql.append(" and d.DRemar like ?  ");
			args.add("%" + conditionMap.get("DRemar") + "%");
		}

		// 获取分页总数
		if (pager != null) {
			generatePageTotalCount(hql.toString(), args, pager);
		}

		hql.append(" order by d.DLevel asc , d.createTime desc  ");
		logger.info("参数：" + args);
		Query query = getSession().createQuery(hql.toString());
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
		List<GvDictionary> list = query.list();
		if (Utils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}

	public List<GvDictionary> getDictionaryList(String dPcode) {
		// TODO Auto-generated method stub
		String hql = "from GvDictionary d where 1=1 and d.DPcode = ? ";
		Query query = getSession().createQuery(hql);
		query.setString(0, dPcode); 
		List<GvDictionary> list = query.list();
		if (Utils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}

	public boolean insert(GvDictionary dictionary) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			getSession().save(dictionary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = false;
			e.printStackTrace();
		}
		return msg;
	}

	public boolean update(GvDictionary dictionary) {
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
			Pager<GvDictionary> pager) {
		String generatedCountHql = "select count(*) " + hql;
		Query query = getSession().createQuery(generatedCountHql);
		if (Utils.isNotEmpty(args)) {
			int i = 0;
			for (Object str : args) {
				query.setParameter(i++, str);
			}
		}
		System.out.println(args);
		int total = ((Long) query.uniqueResult()).intValue();
		pager.setTotal(total);
	}

}
