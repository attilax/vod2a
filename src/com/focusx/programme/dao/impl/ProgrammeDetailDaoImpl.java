package com.focusx.programme.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.focusx.programme.dao.IProgrammeDetailDao;
import com.focusx.programme.entity.GvProgrammeDetail;

public class ProgrammeDetailDaoImpl implements IProgrammeDetailDao {
	
	protected static Logger logger = Logger.getLogger(ProgrammeDetailDaoImpl.class);
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
	
	@Override
	public boolean insertBatch(List<GvProgrammeDetail> list) {
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			for (GvProgrammeDetail gvProgrammeDetail : list) {
				 getSession().save(gvProgrammeDetail);
			}
		} catch (Exception e) {
			msg = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("【batchinsert】执行出现异常"+e.getMessage());
		}
		return msg;
	}

	@Override
	public boolean deleteBatch(List<Integer> programmeIds) {
		boolean msg = true;
		try {						  							  
			String hql = "delete from GvProgrammeDetail d where d.programmeId in (:ids)";
			Query query = getSession().createQuery(hql); 
			query.setParameterList("ids", programmeIds);
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
	
	@Override
	public boolean deleteByProgrammeId(Integer programmeId){
		// TODO Auto-generated method stub
		boolean msg = true;
		try {
			String hql = "delete from GvProgrammeDetail d where d.programmeId = ? ";
			Query query = getSession().createQuery(hql);  
			query.setInteger(0, programmeId);
			int num = 1;
			query.executeUpdate(); ///obo maybe logic del programl cant del the detail info
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

	@Override
	public List<GvProgrammeDetail> getProgrammeDetail(Integer programmeId) {
		// TODO Auto-generated method stub
		String hql = " from GvProgrammeDetail d where d.programmeId = ? order by playOrder asc ";
		Query query = getSession().createQuery(hql);  
		query.setInteger(0, programmeId);
		List<GvProgrammeDetail> list = query.list();
		return list;
	}

}
