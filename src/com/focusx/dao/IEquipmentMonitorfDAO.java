package com.focusx.dao;

import java.util.List;
import java.util.Map;

import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentMonitor;

public interface IEquipmentMonitorfDAO {
	/**
	 * 分页查询数据总条数
	 * @param hql
	 * @return
	 */
	public Integer getCount(String hql);

	/**
	 * 分页查询监控数据
	 * @param data
	 * @param pager
	 * @return
	 */
	public List<EquipmentMonitor> getListPage(Map<String, Object> data, Pager<EquipmentMonitor> pager);

	/**
	 * 保存
	 * @param equipmentMonitor
	 */
	public void save(EquipmentMonitor equipmentMonitor);

	/**
	 * 更新
	 * @param equipmentMonitor
	 */
	public void update(EquipmentMonitor equipmentMonitor);

	/**
	 * 删除
	 * @param equipmentMonitor
	 */
	public void delete(EquipmentMonitor equipmentMonitor);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public EquipmentMonitor getEquipmentMonitor(int id);
	
	/**
	 * 根据编码查询
	 * @param serialCode
	 * @return
	 */
	public EquipmentMonitor getEmBySerialCode(String serialCode);
	
	/**
	 * 查询所有监控数据
	 * @param serialCode
	 * @return
	 */
	public List<EquipmentMonitor> getEmList();
	
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
