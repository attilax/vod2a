package com.focusx.service;

import java.util.List;
import java.util.Map;

import com.focusx.pager.Pager;
import com.focusx.pojo.EquipmentVeriyf;

public interface IEquipmentVeriyfService {
	public List<EquipmentVeriyf> getListPage(Map<String, Object> data, Pager<EquipmentVeriyf> pager);

	public void save(EquipmentVeriyf equipmentVeriyf);

	public void update(EquipmentVeriyf equipmentVeriyf);

	public void delete(EquipmentVeriyf equipmentVeriyf);

	public EquipmentVeriyf getEquipmentVeriyf(int id);
}