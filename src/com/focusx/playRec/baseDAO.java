package com.focusx.playRec;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.concur.dataISEmptyEx;
import com.attilax.db.IDBX;
import com.attilax.ref.cantFindIDFieldEx;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.focusx.dao.impl.EquipmentDAOImpl;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.elmt.BaseHibernateDAO;
import com.focusx.pojo.Equipment;
import com.focusx.util.HbX4vod;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Id;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.DataException;
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
@Deprecated 
public class baseDAO extends BaseHibernateDAO implements IDBX  {
	     private static final Logger log = LoggerFactory.getLogger(baseDAO.class);
		//property constants
	public static final String SCREEN = "screen";
	public static final String PUBLISH_TYPE = "publishType";
	public static final String MATERIAL_ID = "materialId";
	public static final String EQUIPMENT_ID = "equipmentId";

public static void main(String[] args) {
	String hql = "from Equipment e where 1=1 order by equipmentId desc  ";
	final EquipmentDAOImpl equipmentDAOImpl = new EquipmentDAOImpl();
	Equipment e = (Equipment) equipmentDAOImpl.getTop(hql, 1, HbX4vod.getSession());
	System.out.println(e.getEquipmentId());

}

Object obj=new Object();
    
    public void save(Object transientInstance) {
        log.debug("saving GvPlayRecord instance");
        Session session = null ;
        try {
        	synchronized (obj) {
        		session= getSession();
            	Transaction tx = session.beginTransaction();
            	session.save(transientInstance);        	
            	tx.commit();
			}
        
            log.debug("save successful");
        }
            catch(DataException de)
            {
            	if(de.getMessage().contains("截断字符串或二进制数据"))
            	{
            		core.log(de);
            	}
            	//return transientInstance;
            }
        	 
        	 catch (Exception re) {
                log.error("merge failed", re);
                try {
                	if(session!=null)
                    	session.close();
    			} catch (Exception e) {
    				core.log(e);
    			}
                throw new RuntimeException(re);
            }
    }
    /**
     * 
    @author attilax 老哇的爪子
    	@since  o7n h_44_c$
    
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
	
	public void delete(Object persistentInstance) {
        log.debug("deleting   instance");
        try {
        	Session session = getSession();
        	Transaction tx = session.beginTransaction();
        	session.delete(persistentInstance);
        	
        	tx.commit();
           
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    /**
     * 
    @author attilax 老哇的爪子
    	@since  o7t h_47_8$
    
     * @param cls
     * @param id
     * @return
     */
    public Object findById(Class<?> cls,java.lang.Integer id) {
        log.debug("getting   instance with id: " + id);
        try {
            Object instance =   getSession()
                    .get(cls, id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    /**
	@author attilax 老哇的爪子
		@since  o8r k_43_43   
	
	 * @param string
	 * @return
	 */
	public List findByIds(final Class<?> cls,String idsx) {
		// attilax 老哇的爪子  k_43_43   o8r 
		String[] ids=idsx.split(",");
		List li=Arrays.asList(ids);
		return (List) CollectionUtils.each_safe(li, new Closure(){

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子  h572   o7g 
				
				Integer id =Integer.parseInt(arg0.toString());
				Object o=findById(cls,id);
				return o;
				 
				
			}});
	//	return null;
		
	}
    /**
     * 
    @author attilax 老哇的爪子
    	@since  o7t h_47_5$
    
     * @param cls
     * @param id
     * @return
     */
    public Object get(Class<?> cls,java.lang.Integer id) {
        log.debug("getting   instance with id: " + id);
        try {
            Object instance =   getSession()
                    .get(cls, id);
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
    
    
	public int maxID(Class<?> o) throws cantFindIDFieldEx, dataISEmptyEx {

		String idfld;
		try {
			idfld = refx.getIdFld_EX(o).getName();
		} catch (cantFindMatchFieldException e) {
			// attilax 老哇的爪子 j_57_q o8o
			throw new cantFindIDFieldEx();
		}
		String cls = o.getName();
		Integer c = (Integer) getSession().createQuery("select max(a." + idfld + ") from " + cls + " a ").uniqueResult();
		if (c == null) throw new dataISEmptyEx();
		return c;
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
	
    public Object merge(Object detachedInstance) {
    	 try {
    	Session session = getSession();
    	Transaction tx = session.beginTransaction();
    	Object r=session.merge(detachedInstance);
    	
    	tx.commit();
        log.debug("merging   instance");
       
          
            return r;
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


	/**
	@author attilax 老哇的爪子
		@since  o8t 0_4_45   
	
	 * @param t
	 */
	public void merge_syn(Object t) {
		
		synchronized (baseDAO.class) {
			merge(t);
		}
		// attilax 老哇的爪子  0_4_45   o8t 
	
	}
}