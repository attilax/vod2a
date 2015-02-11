package com.focusx.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.focusx.dao.BranchManagerDao;
import com.focusx.dao.IEquipmentDAO;
import com.focusx.dao.IEquipmentMonitorfDAO;
import com.focusx.entity.TMbGroup;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentMonitor;
import com.focusx.service.IEquipmentMonitorfService;
import com.focusx.util.MD5;
import com.focusx.util.Utils;
import  org.apache.commons.beanutils.PropertyUtils;

public class EquipmentMonitorfServiceImpl implements IEquipmentMonitorfService {
	protected static Logger logger = Logger.getLogger(EquipmentMonitorfServiceImpl.class);
	private IEquipmentMonitorfDAO equipmentMonitorfDAO;
	private IEquipmentDAO equipmentDAO;
	private BranchManagerDao branchManagerDao;
	
	public IEquipmentMonitorfDAO getEquipmentMonitorfDAO() {
		return equipmentMonitorfDAO;
	}

	public void setEquipmentMonitorfDAO(IEquipmentMonitorfDAO equipmentMonitorfDAO) {
		this.equipmentMonitorfDAO = equipmentMonitorfDAO;
	}
	
	public IEquipmentDAO getEquipmentDAO() {
		return equipmentDAO;
	}

	public void setEquipmentDAO(IEquipmentDAO equipmentDAO) {
		this.equipmentDAO = equipmentDAO;
	}
	
	

	@Override
	public void delete(EquipmentMonitor equipmentMonitor) {
		equipmentMonitorfDAO.delete(equipmentMonitor);
	}

	@Override
	public EquipmentMonitor getEquipmentMonitor(int id) {
		// TODO Auto-generated method stub
		return equipmentMonitorfDAO.getEquipmentMonitor(id);
	}

	@Override
	public List<EquipmentMonitor> getListPage(Map<String, Object> data,
			Pager<EquipmentMonitor> pager) {
		return equipmentMonitorfDAO.getListPage(data, pager);
	}

	@Override
	public void save(EquipmentMonitor equipmentMonitor) {
		equipmentMonitorfDAO.save(equipmentMonitor);
	}

	@Override
	public void update(EquipmentMonitor equipmentMonitor) {
		equipmentMonitorfDAO.update(equipmentMonitor);
	}

	

	@Override
	public boolean updateCacher(EquipmentMonitor em) {
		if(Utils.isNotEmpty(em.getSerialCode())){
			EquipmentMonitor checkEm = equipmentMonitorfDAO.getEmBySerialCode(em.getSerialCode());
			if(checkEm == null){
				logger.info("未检索到设备编码[" + em.getSerialCode() + "]增加新设备监控数据。");
				equipmentMonitorfDAO.save(em);
			}else{
				logger.info("检索到设备编码[" + em.getSerialCode() + "]更新设备监控数据。");
//				int emId = checkEm.getEmId();
//				checkEm.setAScreenMaterialId(em.getAScreenMaterialId());
//				checkEm.setAsmContent(em.getAsmContent());
//				checkEm.setBScreenMaterialId(em.getBScreenMaterialId());
//				checkEm.setBsmContent(em.getBsmContent());
//				checkEm.setCScreenMaterialId(em.getCScreenMaterialId());
//				checkEm.setCsmContent(em.getCsmContent());
				checkEm.setDepartName("0000000000000");
				
				//copyProperties(checkEm, em);
				
				try {
					//BeanUtils.copyProperties(checkEm, em);
					//checkEm.setEmId(emId);
					System.out.println(checkEm.getEmId());
					equipmentMonitorfDAO.update(checkEm);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}else{
			logger.info("设备编码为空或丢失!");
		}
		return false;
	}
	
	/**
	 * 根据编码查询
	 * @param serialCode
	 * @return
	 */
	public EquipmentMonitor getEmBySerialCode(String serialCode){
		return equipmentMonitorfDAO.getEmBySerialCode(serialCode);
	}
	
	@Override
	public Equipment getEquipmentBySerialCode(String serialCode) {
		List<Equipment> list = equipmentDAO.checkSerialCode(MD5.getMD5(serialCode));
		return list.size() > 0 ? list.get(0) : null; 
	}

	@Override
	public List<EquipmentMonitor> getEmList() {
		return equipmentMonitorfDAO.getEmList();
	}
	
	
	@Override
	public TMbGroup getTMbGroup(String groupId) {
		return branchManagerDao.getGroup(Integer.parseInt(groupId));
	}
	
	@Override
	public List<Equipment> getNoMonitor() {
		return equipmentMonitorfDAO.getNoMonitor();
	}

	@Override
	public List<EquipmentMonitor> getRedundancyMonitor() {
		return equipmentMonitorfDAO.getRedundancyMonitor();
	}
	
	@Override
	public Equipment getEquipmentById(String id) {
		return equipmentDAO.getEquipment(Integer.parseInt(id));
	}

	public BranchManagerDao getBranchManagerDao() {
		return branchManagerDao;
	}

	public void setBranchManagerDao(BranchManagerDao branchManagerDao) {
		this.branchManagerDao = branchManagerDao;
	}
	
}
