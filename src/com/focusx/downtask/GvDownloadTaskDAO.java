package com.focusx.downtask;

import com.focusx.elmt.BaseHibernateDAO;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for GvDownloadTask entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.focusx.downtask.GvDownloadTask
  * @author MyEclipse Persistence Tools 
 */

public class GvDownloadTaskDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(GvDownloadTaskDAO.class);
		//property constants
	public static final String NOTICE_FLAG = "noticeFlag";
	public static final String DOWNLOAD_STATUS = "downloadStatus";
	public static final String MATERIAL_ID = "materialId";
	public static final String EQUIPMENT_ID = "equipmentId";



    
    public void save(GvDownloadTask transientInstance) {
        log.debug("saving GvDownloadTask instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(GvDownloadTask persistentInstance) {
        log.debug("deleting GvDownloadTask instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public GvDownloadTask findById( java.lang.Integer id) {
        log.debug("getting GvDownloadTask instance with id: " + id);
        try {
            GvDownloadTask instance = (GvDownloadTask) getSession()
                    .get("com.focusx.downtask.GvDownloadTask", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<GvDownloadTask> findByExample(GvDownloadTask instance) {
        log.debug("finding GvDownloadTask instance by example");
        try {
            List<GvDownloadTask> results = (List<GvDownloadTask>) getSession()
                    .createCriteria("com.focusx.downtask.GvDownloadTask")
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
      log.debug("finding GvDownloadTask instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from GvDownloadTask as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<GvDownloadTask> findByNoticeFlag(Object noticeFlag
	) {
		return findByProperty(NOTICE_FLAG, noticeFlag
		);
	}
	
	public List<GvDownloadTask> findByDownloadStatus(Object downloadStatus
	) {
		return findByProperty(DOWNLOAD_STATUS, downloadStatus
		);
	}
	
	public List<GvDownloadTask> findByMaterialId(Object materialId
	) {
		return findByProperty(MATERIAL_ID, materialId
		);
	}
	
	public List<GvDownloadTask> findByEquipmentId(Object equipmentId
	) {
		return findByProperty(EQUIPMENT_ID, equipmentId
		);
	}
	

	public List findAll() {
		log.debug("finding all GvDownloadTask instances");
		try {
			String queryString = "from GvDownloadTask";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public GvDownloadTask merge(GvDownloadTask detachedInstance) {
        log.debug("merging GvDownloadTask instance");
        try {
            GvDownloadTask result = (GvDownloadTask) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(GvDownloadTask instance) {
        log.debug("attaching dirty GvDownloadTask instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(GvDownloadTask instance) {
        log.debug("attaching clean GvDownloadTask instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}