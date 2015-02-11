package com.focusx.playRec;

import com.focusx.elmt.BaseHibernateDAO;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for GvPlayRecord entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.focusx.playRec.GvPlayRecord
  * @author MyEclipse Persistence Tools 
 */

public class GvPlayRecordDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(GvPlayRecordDAO.class);
		//property constants
	public static final String SCREEN = "screen";
	public static final String PUBLISH_TYPE = "publishType";
	public static final String MATERIAL_ID = "materialId";
	public static final String EQUIPMENT_ID = "equipmentId";



    
    public void save(GvPlayRecord transientInstance) {
        log.debug("saving GvPlayRecord instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(GvPlayRecord persistentInstance) {
        log.debug("deleting GvPlayRecord instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public GvPlayRecord findById( java.lang.Integer id) {
        log.debug("getting GvPlayRecord instance with id: " + id);
        try {
            GvPlayRecord instance = (GvPlayRecord) getSession()
                    .get("com.focusx.playRec.GvPlayRecord", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<GvPlayRecord> findByExample(GvPlayRecord instance) {
        log.debug("finding GvPlayRecord instance by example");
        try {
            List<GvPlayRecord> results = (List<GvPlayRecord>) getSession()
                    .createCriteria("com.focusx.playRec.GvPlayRecord")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding GvPlayRecord instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from GvPlayRecord as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<GvPlayRecord> findByScreen(Object screen
	) {
		return findByProperty(SCREEN, screen
		);
	}
	
	public List<GvPlayRecord> findByPublishType(Object publishType
	) {
		return findByProperty(PUBLISH_TYPE, publishType
		);
	}
	
	public List<GvPlayRecord> findByMaterialId(Object materialId
	) {
		return findByProperty(MATERIAL_ID, materialId
		);
	}
	
	public List<GvPlayRecord> findByEquipmentId(Object equipmentId
	) {
		return findByProperty(EQUIPMENT_ID, equipmentId
		);
	}
	

	public List findAll() {
		log.debug("finding all GvPlayRecord instances");
		try {
			String queryString = "from GvPlayRecord";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public GvPlayRecord merge(GvPlayRecord detachedInstance) {
        log.debug("merging GvPlayRecord instance");
        try {
            GvPlayRecord result = (GvPlayRecord) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(GvPlayRecord instance) {
        log.debug("attaching dirty GvPlayRecord instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(GvPlayRecord instance) {
        log.debug("attaching clean GvPlayRecord instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}