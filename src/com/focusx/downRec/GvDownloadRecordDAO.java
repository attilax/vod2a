package com.focusx.downRec;

import com.focusx.elmt.BaseHibernateDAO;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A data access object (DAO) providing persistence and search support for
 * GvDownloadRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * @see com.focusx.downRec.GvDownloadRecord
 * @author MyEclipse Persistence Tools */

public class GvDownloadRecordDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(GvDownloadRecordDAO.class);
	// property constants
	public static final String DOWNLOAD_FLAG = "downloadFlag";
	public static final String PROGRAMME_ID = "programmeId";
	public static final String MATERIAL_ID = "materialId";
	public static final String EQUIPMENT_ID = "equipmentId";
	public static final String SJ_FILE_NAME = "sjFileName";

	public void save(GvDownloadRecord transientInstance) {
		log.debug("saving GvDownloadRecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GvDownloadRecord persistentInstance) {
		log.debug("deleting GvDownloadRecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GvDownloadRecord findById(java.lang.Integer id) {
		log.debug("getting GvDownloadRecord instance with id: " + id);
		try {
			GvDownloadRecord instance = (GvDownloadRecord) getSession().get("com.focusx.downRec.GvDownloadRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GvDownloadRecord> findByExample(GvDownloadRecord instance) {
		log.debug("finding GvDownloadRecord instance by example");
		try {
			List<GvDownloadRecord> results = (List<GvDownloadRecord>) getSession().createCriteria("com.focusx.downRec.GvDownloadRecord").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding GvDownloadRecord instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from GvDownloadRecord as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GvDownloadRecord> findByDownloadFlag(Object downloadFlag) {
		return findByProperty(DOWNLOAD_FLAG, downloadFlag);
	}

	public List<GvDownloadRecord> findByProgrammeId(Object programmeId) {
		return findByProperty(PROGRAMME_ID, programmeId);
	}

	public List<GvDownloadRecord> findByMaterialId(Object materialId) {
		return findByProperty(MATERIAL_ID, materialId);
	}

	public List<GvDownloadRecord> findByEquipmentId(Object equipmentId) {
		return findByProperty(EQUIPMENT_ID, equipmentId);
	}

	public List<GvDownloadRecord> findBySjFileName(Object sjFileName) {
		return findByProperty(SJ_FILE_NAME, sjFileName);
	}

	public List findAll() {
		log.debug("finding all GvDownloadRecord instances");
		try {
			String queryString = "from GvDownloadRecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GvDownloadRecord merge(GvDownloadRecord detachedInstance) {
		log.debug("merging GvDownloadRecord instance");
		try {
			GvDownloadRecord result = (GvDownloadRecord) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GvDownloadRecord instance) {
		log.debug("attaching dirty GvDownloadRecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GvDownloadRecord instance) {
		log.debug("attaching clean GvDownloadRecord instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}