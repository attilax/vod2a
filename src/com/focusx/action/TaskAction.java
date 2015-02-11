package com.focusx.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m.datepkg.LOGGER;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.focusx.entity.vod.DownloadRecord;
import com.focusx.entity.vod.DownloadTask;
import com.focusx.service.ITaskService;

public class TaskAction {
	
	private final static Logger loger = Logger.getLogger(TaskAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ITaskService taskService;
	private String serialCode;
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private void init(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//获取上传串号
		serialCode = request.getParameter("");
	}
	
	
	//实时反馈下载信息
	public String inFeedback(){
		//初始化上下文请求
		init();
		//检查串号权限
		checkPrivilege();
		//根据反馈，更新任务状态
		updateTask();
		return null;
	}
	
	
	private void checkPrivilege(){
		
	}
	
	private void updateTask(){
		
		//反馈的任务id
		Integer taskId = Integer.parseInt(request.getParameter("taskId"));
		//任务状态
		Integer download_status = Integer.parseInt(request.getParameter("download_status"));
		
		DownloadTask upTask = taskService.getDownloadTaskById(taskId);
		if(upTask != null){
				upTask.setDownloadStatus(download_status);
				if(taskService.updateDownloadTask(upTask)){
					loger.info("更新任务["+taskId+"]状态为["+download_status+"]，成功");
				}else{
					loger.info("更新任务["+taskId+"]状态为["+download_status+"]，失败");
				}
		}else{
			loger.info("找不到任务记录===>" + taskId);
		}
	}
	
	
	/**
	 * @category 增加下载流水
	 * @return
	 */
	public String taskSerial(){
		
		//初始化上下文请求
		init();
		//检查是否合法
		checkPrivilege();
		//增加下载流水
		addTaskRecord();
		
		return null;
	}

	
	private void addTaskRecord(){
		
		
		DownloadRecord aRecord = new DownloadRecord();
		
		int download_flag = Integer.parseInt(request.getParameter("download_flag"));
		String download_over_time = request.getParameter("download_over_time");
		int programme_id = Integer.parseInt(request.getParameter("programme_id"));
		int material_id = Integer.parseInt(request.getParameter("material_id"));
		int equiment_id = Integer.parseInt(request.getParameter("equiment_id"));
		String sj_file_name = request.getParameter("sj_file_name");
		
		Date dot = null;
		try {
			dot = sdf.parse(download_over_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		aRecord.setDownloadFlag(download_flag);
		if(dot != null){
			aRecord.setDownloadOverTime(new Timestamp(dot.getTime()));
		}
		aRecord.setEquipmentId(equiment_id);
		aRecord.setMaterialId(material_id);
		aRecord.setSjFileName(sj_file_name);
		aRecord.setProgrammeId(programme_id);
		
		if(taskService.addDownloadRecord(aRecord)){
			loger.info("添加下载流水["+equiment_id+"]["+material_id+"]["+programme_id+"]["+sj_file_name+"]["+download_over_time+"]成功");
		}else{
			loger.info("添加下载流水["+equiment_id+"]["+material_id+"]["+programme_id+"]["+sj_file_name+"]["+download_over_time+"]失败");
		}
		
	}


	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	
	
	
	
}
