package com.focusx.service;

import java.util.List;
import java.util.Map;

import com.focusx.entity.TMbGroup;
import com.focusx.entity.TUserGroup;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
/**
 * 设备管理Service接口
 * @author zhoujianbin
 *
 */
public interface IEquipmentService {
	/**
	 * 分页查询设备
	 * @param data
	 * @param pager
	 * @return
	 */
	public List<Equipment> getListPage(Map<String, Object> data, Pager<Equipment> pager);
	
	/**
	 * 增加、更新保存
	 * @param equipment
	 */
	public void saveUpdate(Equipment equipment);

	/**
	 * 根据设备ID查询
	 * @param id
	 * @return
	 */
	public Equipment getEquipment(int id);

	/**
	 * 根据机身编码查询
	 * @param serialCode
	 * @return
	 */
	public List<Equipment> checkSerialCode(String serialCode);
	
	/**
	 * 删除设备
	 * @param item
	 * @return
	 */
	public boolean deleteEquipments(String[] item);
	
	/**
	 * 根据设备ID集合查询
	 * @param Ids
	 * @return
	 */
	public List<Equipment> getListByIds(String Ids);
	
	/**
	 * 根据组织ID集合查询所有设备组织
	 * @param ids 设备ID集合
	 * @return
	 */
	public List<TMbGroup> getGroupList(String groupIds);
	
	/**
	 * 根据组织ID集合查询
	 * @param departIds
	 * @return
	 */
	public List<Equipment> getListByDepartId(String departIds);
}