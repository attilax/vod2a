package com.focusx.service.impl;

import java.util.List;
import java.util.Map;

import com.focusx.dao.BranchManagerDao;
import com.focusx.dao.IEquipmentDAO;
import com.focusx.entity.TMbGroup;
import com.focusx.entity.TUserGroup;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.service.IEquipmentService;
import com.focusx.util.MD5;
/**
 * 设备管理Service
 * @author zhoujianbin
 *
 */
public class EquipmentServiceImpl implements IEquipmentService {
	private IEquipmentDAO equipmentDAO;
	
	private BranchManagerDao branchManagerDao;

	public void setEquipmentDAO(IEquipmentDAO equipmentDAO) {
		this.equipmentDAO = equipmentDAO;
	}

	public void setBranchManagerDao(BranchManagerDao branchManagerDao) {
		this.branchManagerDao = branchManagerDao;
	}
	
	public void saveUpdate(Equipment equipment) {
		if(equipment != null){
			if(equipment.getEquipmentId() == null){
				equipmentDAO.save(equipment);
			}else{
				equipmentDAO.update(equipment);
			}
		}
	}
	
	public List<Equipment> getListPage(Map<String, Object> data, Pager<Equipment> pager) {
		return equipmentDAO.getListPage(data, pager);
	}

	@Override
	public Equipment getEquipment(int id) {
		return equipmentDAO.getEquipment(id);
	}

	@Override
	public List<Equipment> checkSerialCode(String serialCode) {
		return equipmentDAO.checkSerialCode(MD5.getMD5(serialCode));
	}

	@Override
	public boolean deleteEquipments(String[] item) {
		if(item.length > 0){
			for (String s : item) {
				Equipment equipment = new Equipment(Integer.parseInt(s));
				equipmentDAO.delete(equipment);
			}
		}
		return true;
	}

	@Override
	public List<Equipment> getListByIds(String Ids) {
		return equipmentDAO.getListByIds(Ids);
	}

	@Override
	public List<TMbGroup> getGroupList(String groupIds) {
		return branchManagerDao.getGroupListByIds(groupIds);
	}

	@Override
	public List<Equipment> getListByDepartId(String departIds) {
		return equipmentDAO.getListByDepartId(departIds);
	}
	
}
