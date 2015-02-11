package com.focusx.dao;

import java.util.List;

import com.focusx.entity.vod.DownloadRecord;
import com.focusx.entity.vod.DownloadTask;

public interface ITaskDao {
	
	
	public DownloadTask  getDownloadTaskById(Integer taskId);
	
	public boolean updateDownloadTask(DownloadTask upTask);
	
	
	public boolean addDownloadRecord(DownloadRecord aRecord);
	
	
	public List<DownloadTask>  getDownloadTask();
	

}
