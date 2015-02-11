package com.focusx.service;

import java.util.List;
import java.util.Map;

import com.focusx.entity.vod.DownloadRecord;
import com.focusx.entity.vod.DownloadTask;


/***
 * @category  
 * @author Administrator
 *
 */

public interface ITaskService {
	
	/**
	 * @category 添加下载任务
	 * @param taskList
	 * @return
	 */
	public boolean addDownloadTask(List<DownloadTask> taskList);
	
	/**
	 * @category 获取任务列表
	 * @param params
	 * @return
	 */
	public List<DownloadTask>   getDownloadTask(Map<Object,Object> params);
	
	
	public DownloadTask  getDownloadTaskById(Integer taskId);
	
	/**
	 * @category 更新某个任务状态
	 * @param upTask
	 * @return
	 */
	public boolean updateDownloadTask(DownloadTask upTask);
	
	
	/**
	 * @category 更新批量任务
	 * @param upTaskList
	 * @return
	 */
	public boolean updateDownloadTaskList(List<DownloadTask> upTaskList);

	
	/**
	 * @category 删除某个下载任务
	 * @param delTask
	 * @return
	 */
	public boolean deleteDownloadTask(DownloadTask delTask);
	
	
	/**
	 * @category 删除下载任务列表
	 * @param delTaskList
	 * @return
	 */
	public boolean deleteDownloadTaskList(List<DownloadTask>  delTaskList);
	
	/**
	 * @category 根据参数删除下载任务
	 * @param params
	 * @return
	 */
	public boolean deleteDownloadTaskByParams(Map<Object,Object> params);
	
	
	/**
	 * @category 增加下载流水
	 * @param aRecord
	 * @return
	 */
	public boolean addDownloadRecord(DownloadRecord aRecord);
	
	
	/**
	 * @category 批量增加下载流水
	 * @param recordList
	 * @return
	 */
	public boolean addDownloadRecordList(List<DownloadRecord>  recordList);
	
}
