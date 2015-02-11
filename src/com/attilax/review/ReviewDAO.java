package com.attilax.review;

import com.focusx.elmt.BaseHibernateDAO;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Review entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.attilax.review.Review
 * @author MyEclipse Persistence Tools
 */

public class ReviewDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ReviewDAO.class);
	// property constants
	public static final String TARGETID = "targetid";
	public static final String TARGETTYPE = "targettype";
	public static final String STATE = "state";
	public static final String REVIEWER = "reviewer";
	public static final String REVIEWERID = "reviewerid";

	public void save(Review transientInstance) {
		log.debug("saving Review instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Review persistentInstance) {
		log.debug("deleting Review instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Review findById(java.lang.Integer id) {
		log.debug("getting Review instance with id: " + id);
		try {
			Review instance = (Review) getSession().get(
					"com.attilax.review.Review", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Review> findByExample(Review instance) {
		log.debug("finding Review instance by example");
		try {
			List<Review> results = (List<Review>) getSession()
					.createCriteria("com.attilax.review.Review")
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
		log.debug("finding Review instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Review as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Review> findByTargetid(Object targetid) {
		return findByProperty(TARGETID, targetid);
	}

	public List<Review> findByTargettype(Object targettype) {
		return findByProperty(TARGETTYPE, targettype);
	}

	public List<Review> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Review> findByReviewer(Object reviewer) {
		return findByProperty(REVIEWER, reviewer);
	}

	public List<Review> findByReviewerid(Object reviewerid) {
		return findByProperty(REVIEWERID, reviewerid);
	}

	public List findAll() {
		log.debug("finding all Review instances");
		try {
			String queryString = "from Review";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Review merge(Review detachedInstance) {
		log.debug("merging Review instance");
		try {
			Review result = (Review) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Review instance) {
		log.debug("attaching dirty Review instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Review instance) {
		log.debug("attaching clean Review instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}