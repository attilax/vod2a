package com.focusx.dao;

import java.util.List;
import java.util.Map;

import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
/**
 * 设备管理DAO类接口
 * @author zhoujianbin
 *
 */
public interface IEquipmentDAO {
	public Integer getCount(String hql);

	/**
	 * 分页查询
	 * @return
	 */
	public List<Equipment> getListPage(Map<String, Object> data, Pager<Equipment> pager);

	/**
	 * 增加
	 * @return
	 */
	public void save(Equipment equipment);
	
	/**
	 * 更新
	 * @return
	 */
	public void update(Equipment equipment);

	/**
	 * 删除
	 * @return
	 */
	public void delete(Equipment equipment);
	
	/**
	 * 根据设备ID查询
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
	 * 根据设备ID集合查询
	 * @return
	 */
	public List<Equipment> getListByIds(String Ids);
	
	/**
	 * 根据组织ID集合查询
	 * @param departIds
	 * @return
	 */
	public List<Equipment> getListByDepartId(String departIds);
}