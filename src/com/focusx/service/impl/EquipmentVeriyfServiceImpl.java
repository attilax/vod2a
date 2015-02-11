package com.focusx.service.impl;

import java.util.List;
import java.util.Map;

import com.focusx.dao.IEquipmentVeriyfDAO;
import com.focusx.pager.Pager;
import com.focusx.pojo.EquipmentVeriyf;
import com.focusx.service.IEquipmentVeriyfService;

public class EquipmentVeriyfServiceImpl implements IEquipmentVeriyfService {
	private IEquipmentVeriyfDAO equipmentVeriyfDAO;
	
	

	@Override
	public void delete(EquipmentVeriyf equipmentVeriyf) {
		equipmentVeriyfDAO.delete(equipmentVeriyf);
		
	}

	@Override
	public EquipmentVeriyf getEquipmentVeriyf(int id) {
		// TODO Auto-generated method stub
		return equipmentVeriyfDAO.getEquipmentVeriyf(id);
	}

	@Override
	public List<EquipmentVeriyf> getListPage(Map<String, Object> data,
			Pager<EquipmentVeriyf> pager) {
		// TODO Auto-generated method stub
		return equipmentVeriyfDAO.getListPage(data, pager);
	}

	@Override
	public void save(EquipmentVeriyf equipmentVeriyf) {
		equipmentVeriyfDAO.save(equipmentVeriyf);
		
	}

	@Override
	public void update(EquipmentVeriyf equipmentVeriyf) {
		equipmentVeriyfDAO.update(equipmentVeriyf);
		
	}

	public IEquipmentVeriyfDAO getEquipmentVeriyfDAO() {
		return equipmentVeriyfDAO;
	}

	public void setEquipmentVeriyfDAO(IEquipmentVeriyfDAO equipmentVeriyfDAO) {
		this.equipmentVeriyfDAO = equipmentVeriyfDAO;
	}
	
}
