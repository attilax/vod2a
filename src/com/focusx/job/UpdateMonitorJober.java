package com.focusx.job;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.monitor.baseMonitor;
import com.attilax.rails.foreachProcessor;
import com.attilax.util.DateUtil;
import com.focusx.entity.TMbGroup;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentMonitor;
import com.focusx.service.IEquipmentMonitorfService;
//extends baseMonitor
/**
 * 
 * @author  attilax 老哇的爪子
 *@since  o9n j_50_0$
 */
public class UpdateMonitorJober  implements Runnable {
	public static String heartbreakMsg;
	private final static Logger log = Logger.getLogger(UpdateMonitorJober.class);
	private final static int MAX_TASK_COUNT = 20; //最大20个任务
	public static int run = 1; //最大20个任务
	
	private IEquipmentMonitorfService monitorfService;
//	private IEquipmentMonitorfDAO monitorfDAO;
//	
//	
//
//	
//
//	public UpdateMonitorJober(IEquipmentMonitorfDAO monitorfDAO) {
//		this.monitorfDAO = monitorfDAO;
//	}

	public UpdateMonitorJober(IEquipmentMonitorfService monitorfService) {
		this.monitorfService = monitorfService;
	}



	@SuppressWarnings("all") @Override
	public void run() {
		
		if(run==0)
			return;
		this.heartbreakMsg = DateUtil.getDatetime(new Date());
		try {
			
			log.info("正在清除多余设备监控数据...");
			List<EquipmentMonitor> monitors = monitorfService.getRedundancyMonitor();
			if(monitors.size() > 0){
				for (EquipmentMonitor equipmentMonitor : monitors) {
					log.info("清除多余设备监控数据，设备【"+ equipmentMonitor.getEquipmentMome() +"】，共计"+ monitors.size() +"条");
					monitorfService.delete(equipmentMonitor);
				}
			}
			log.info("清除多余设备监控数据结束...");
			
			log.info("正在增加未监控设备数据...");
			List<Equipment> equipments = monitorfService.getNoMonitor();
			if(equipments.size() > 0){
				for (Equipment equipment : equipments) {
					log.info("增加未监控设备的监控数据，设备【"+ equipment.getMome() +"】，共计"+ equipments.size() +"条");
					EquipmentMonitor em = new EquipmentMonitor();
					em.setEquipmentId(equipment.getEquipmentId());
					em.setEquipmentMome(equipment.getMome());
					em.setStatus(equipment.getStatus());
					if(equipment.getDepartId() != null){
						TMbGroup group = monitorfService.getTMbGroup(String.valueOf(equipment.getDepartId()));
						if(group != null){
							em.setDepartId(equipment.getDepartId());
							em.setDepartName(group.getGroupname());
						}
					}
					em.setAScreenMaterialId(null);
					em.setBScreenMaterialId(null);
					em.setCScreenMaterialId(null);
					em.setMonitorTime(new Date());
					em.setAsmContent("");
					em.setBsmContent("");
					em.setCsmContent("");
					em.setEquipmentLineFlag(0);
					em.setEquipmentStatus(3);
					monitorfService.save(em);
				}
			}
			log.info("增加未监控设备数据结束...");
			
			
			
			
			log.info("正在监控设备数据...");
			//获取监控数据列表
			List<EquipmentMonitor> emList = monitorfService.getEmList();
			if(emList.size() > 0){
				for (EquipmentMonitor em : emList) {
					if(em.getEquipmentId().equals(1096))
						System.out.println("");
					Date date = new Date();
					//如果当前时间比距离上次监控数据时间大于60秒，则表示设备监控中断
					long span= date.getTime() - em.getMonitorTime().getTime() ;
					if(( span/ 1000) >= 100){
						//log
						logx(em, date, span);
						
						
						log.info("设备[" + em.getSerialCode() + "," + em.getEquipmentMome() + "]监控未开机或中断，修改设备监控数据。");
						Equipment equipment = monitorfService.getEquipmentById(String.valueOf(em.getEquipmentId()));
						if(equipment != null){
							if(equipment.getDepartId() != null){
								TMbGroup group = monitorfService.getTMbGroup(String.valueOf(equipment.getDepartId()));
								if(group != null){
									em.setDepartId(equipment.getDepartId());
									em.setDepartName(group.getGroupname());
									em.setEquipmentId(equipment.getEquipmentId());
									em.setEquipmentMome(equipment.getMome());
									em.setStatus(equipment.getStatus());// able hesh disable
								}
							}
						}
						em.setAScreenMaterialId(null);
						em.setBScreenMaterialId(null);
						em.setCScreenMaterialId(null);
						em.setMonitorTime(new Date());
						em.setAsmContent("");
						em.setBsmContent("");
						em.setCsmContent("");
						em.setEquipmentLineFlag(0);// offline
						em.setEquipmentStatus(3);
						monitorfService.update(em);
					}
				}
			}
			log.info("监控设备数据处理结束...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}



	private void logx(EquipmentMonitor em, Date date, long span) {
		try {
			String s="eqid:"+em.getEquipmentId().toString() + " now:"+DateUtil.date2str(date,true)+"--mntTime:"+DateUtil.date2str( em.getMonitorTime(),true)+"--span:"+String.valueOf(span)+"\r\n;";
			filex.save_SF(s, "c:\\eqmntDirO022\\eqmnt."+filex.getUUidName()+".txt");
		} catch (Exception e) {
			core.warn(e);
		}
		
	}
}
