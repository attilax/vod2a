package com.focusx.service;

import java.util.List;
import java.util.Map;

import com.focusx.entity.TMbGroup;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentMonitor;

public interface IEquipmentMonitorfService {
	
	/**
	 * 分页查询设备监控信息
	 * @param data
	 * @param pager
	 * @return
	 */
	public List<EquipmentMonitor> getListPage(Map<String, Object> data, Pager<EquipmentMonitor> pager);

	/**
	 * 保存监控数据
	 * @param equipmentMonitor
	 */
	public void save(EquipmentMonitor equipmentMonitor);

	/**
	 * 更新监控数据
	 * @param equipmentMonitor
	 */
	public void update(EquipmentMonitor equipmentMonitor);

	/**
	 * 删除监控数据
	 * @param equipmentMonitor
	 */
	public void delete(EquipmentMonitor equipmentMonitor);
	
	/**
	 * 根据监控数据ID查询
	 * @param equipmentMonitor
	 */
	public EquipmentMonitor getEquipmentMonitor(int id);
	
	/**
	 * 根据编码查询 监控数据
	 * @param serialCode
	 * @return
	 */
	public EquipmentMonitor getEmBySerialCode(String serialCode);
	
	/**
	 * 处理监控数据
	 * @param equipmentMonitor
	 */
	public boolean updateCacher(EquipmentMonitor equipmentMonitor);
	
	/**
	 * 查询所有监控数据
	 * @param serialCode
	 * @return
	 */
	public List<EquipmentMonitor> getEmList();
	
	/**
	 * 根据ID查询组织
	 * @param groupId
	 * @return
	 */
	public TMbGroup getTMbGroup(String groupId);
	
	/**
	 * 根据编码查询 设备数据
	 * @param serialCode
	 * @return
	 */
	public Equipment getEquipmentBySerialCode(String serialCode);
	
	/**
	 * 根据ID查询 设备数据
	 * @param id
	 * @return
	 */
	public Equipment getEquipmentById(String id);
	
	/**
	 * 获取没有监控的数据（尚未添加到监控数据表中的设备）
	 * @return
	 */
	public List<Equipment> getNoMonitor();
	
	/**
	 * 获取不需要监控的数据（设备已经不存在的设备监控数据）
	 * @return
	 */
	public List<EquipmentMonitor> getRedundancyMonitor();
}
