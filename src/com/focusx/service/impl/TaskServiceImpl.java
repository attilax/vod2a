package com.focusx.service.impl;

import java.util.List;
import java.util.Map;

import com.focusx.dao.ITaskDao;
import com.focusx.entity.vod.DownloadRecord;
import com.focusx.entity.vod.DownloadTask;
import com.focusx.service.ITaskService;

public class TaskServiceImpl implements ITaskService {
	
	private ITaskDao taskDao;

	@Override
	public boolean addDownloadTask(List<DownloadTask> taskList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DownloadTask> getDownloadTask(Map<Object, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DownloadTask getDownloadTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateDownloadTask(DownloadTask upTask) {
		return taskDao.updateDownloadTask(upTask);
	}

	@Override
	public boolean updateDownloadTaskList(List<DownloadTask> upTaskList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDownloadTask(DownloadTask delTask) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDownloadTaskList(List<DownloadTask> delTaskList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDownloadTaskByParams(Map<Object, Object> params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDownloadRecord(DownloadRecord aRecord) {
		return taskDao.addDownloadRecord(aRecord);
	}

	@Override
	public boolean addDownloadRecordList(List<DownloadRecord> recordList) {
		// TODO Auto-generated method stub
		return false;
	}

	public ITaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(ITaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	

}
