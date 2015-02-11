package com.focusx.job;

import org.apache.log4j.Logger;

import com.focusx.dao.ITaskDao;

public class CheckTaskJober implements Runnable {
	
	private final static Logger log = Logger.getLogger(CheckTaskJober.class);
	private final static int MAX_TASK_COUNT = 20; //最大20个任务
	
	private ITaskDao taskDao;
	
	public CheckTaskJober(ITaskDao taskDao){
		this.taskDao = taskDao;
	}
	

	@Override
	public void run() {
		
		log.info("正在检查任务列表...");
		
		//获取任务列表
		
		//检查任务个数
		
		//定时获取任务push
		
		
	}

}
