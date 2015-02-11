package com.attilax.collection;

import com.focusx.elmt.BaseHibernateDAO;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A data access object (DAO) providing persistence and search support for
 * GvCycleQueue entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * @see com.attilax.collection.GvCycleQueue
 * @author MyEclipse Persistence Tools */

public class GvCycleQueueDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(GvCycleQueueDAO.class);
	// property constants
	public static final String CIR_TIMES = "cirTimes";
	public static final String RECTYPE = "rectype";
	public static final String DEL = "del";

	public void save(GvCycleQueue transientInstance) {
		log.debug("saving GvCycleQueue instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GvCycleQueue persistentInstance) {
		log.debug("deleting GvCycleQueue instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public Session sess2;
	public GvCycleQueue findById(java.lang.Integer id) {
		log.debug("getting GvCycleQueue instance with id: " + id);
		try {
			Session sess= getSession();
			if(sess==null)
				sess=sess2;
				
			GvCycleQueue instance = (GvCycleQueue) sess.get("com.attilax.collection.GvCycleQueue", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GvCycleQueue> findByExample(GvCycleQueue instance) {
		log.debug("finding GvCycleQueue instance by example");
		try {
			List<GvCycleQueue> results = (List<GvCycleQueue>) getSession().createCriteria("com.attilax.collection.GvCycleQueue").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding GvCycleQueue instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from GvCycleQueue as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
		 	queryObject.setFirstResult(0);
			queryObject.setMaxResults(900);
		//	q.setFirstResult(500);   
		//	q.setMaxResults(100); 
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByHql(String Hql,int size) {
	//	log.debug("finding GvCycleQueue instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = Hql;
			Query queryObject = getSession().createQuery(queryString);
		//	queryObject.setParameter(0, value);
			queryObject.setFirstResult(0);
			queryObject.setMaxResults(size);
		//	q.setFirstResult(500);   
		//	q.setMaxResults(100); 
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Object findByProperty_SingleObj(String propertyName, Object value) throws CantFindObj {
		log.debug("finding GvCycleQueue instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from GvCycleQueue as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			if(list.size()==0)
				throw new CantFindObj("---CantFindObj");
			return list.get(0);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List<GvCycleQueue> findByCirTimes(Object cirTimes) {
		return findByProperty(CIR_TIMES, cirTimes);
	}

	public List<GvCycleQueue> findByRectype(Object rectype) {
		return findByProperty(RECTYPE, rectype);
	}

	public List<GvCycleQueue> findByDel(Object del) {
		return findByProperty(DEL, del);
	}

	public List findAll() {
		log.debug("finding all GvCycleQueue instances");
		try {
			String queryString = "from GvCycleQueue";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GvCycleQueue merge(GvCycleQueue detachedInstance) {
		log.debug("merging GvCycleQueue instance");
		try {
			GvCycleQueue result = (GvCycleQueue) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GvCycleQueue instance) {
		log.debug("attaching dirty GvCycleQueue instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GvCycleQueue instance) {
		log.debug("attaching clean GvCycleQueue instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}