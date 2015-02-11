package com.focusx.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.focusx.dao.ITaskDao;
import com.focusx.entity.vod.DownloadRecord;
import com.focusx.entity.vod.DownloadTask;
import com.focusx.service.ITaskService;

public class TaskDaoImpl implements ITaskDao {
	
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public DownloadTask getDownloadTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean updateDownloadTask(DownloadTask upTask) {
		try{
			getSession().update(upTask);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addDownloadRecord(DownloadRecord aRecord) {
		try{
			getSession().save(aRecord);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DownloadTask> getDownloadTask() {
		try{
			String hql = "from DownloadTask d where d.downloadStatus = ";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

}
