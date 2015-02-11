package com.focusx.elmt;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A data access object (DAO) providing persistence and search support for
 * GvMaterial entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * @see com.focusx.elmt.GvMaterial
 * @author MyEclipse Persistence Tools */

public class GvMaterialDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(GvMaterialDAO.class);
	// property constants
	public static final String MATERIAL_DESCRIPTION = "materialDescription";
	public static final String MATERIAL_TYPE = "materialType";
	public static final String CREATE_USER = "createUser";
	public static final String SIZE = "size";
	public static final String FILE_PATH = "filePath";
	public static final String CAN_DOWN_ORG = "canDownOrg";
	public static final String UPDATE_USER = "updateUser";
	public static final String APPLICATION_TYPE = "applicationType";
// deled meth
//	public void save(GvMaterial transientInstance) {
//		log.debug("saving GvMaterial instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(GvMaterial persistentInstance) {
		log.debug("deleting GvMaterial instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GvMaterial findById(java.lang.Integer id) {
		log.debug("getting GvMaterial instance with id: " + id);
		try {
			GvMaterial instance = (GvMaterial) getSession().get("com.focusx.elmt.GvMaterial", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GvMaterial> findByExample(GvMaterial instance) {
		log.debug("finding GvMaterial instance by example");
		try {
			List<GvMaterial> results = (List<GvMaterial>) getSession().createCriteria("com.focusx.elmt.GvMaterial").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding GvMaterial instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from GvMaterial as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GvMaterial> findByMaterialDescription(Object materialDescription) {
		return findByProperty(MATERIAL_DESCRIPTION, materialDescription);
	}

	public List<GvMaterial> findByMaterialType(Object materialType) {
		return findByProperty(MATERIAL_TYPE, materialType);
	}

	public List<GvMaterial> findByCreateUser(Object createUser) {
		return findByProperty(CREATE_USER, createUser);
	}

	public List<GvMaterial> findBySize(Object size) {
		return findByProperty(SIZE, size);
	}

	public List<GvMaterial> findByFilePath(Object filePath) {
		return findByProperty(FILE_PATH, filePath);
	}

	public List<GvMaterial> findByCanDownOrg(Object canDownOrg) {
		return findByProperty(CAN_DOWN_ORG, canDownOrg);
	}

	public List<GvMaterial> findByUpdateUser(Object updateUser) {
		return findByProperty(UPDATE_USER, updateUser);
	}

	public List<GvMaterial> findByApplicationType(Object applicationType) {
		return findByProperty(APPLICATION_TYPE, applicationType);
	}

	public List findAll() {
		log.debug("finding all GvMaterial instances");
		try {
			String queryString = "from GvMaterial";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GvMaterial merge(GvMaterial detachedInstance) {
		log.debug("merging GvMaterial instance");
		try {
			GvMaterial result = (GvMaterial) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GvMaterial instance) {
		log.debug("attaching dirty GvMaterial instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GvMaterial instance) {
		log.debug("attaching clean GvMaterial instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}