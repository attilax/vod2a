package com.focusx.publish.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.api.HandlerChain;
import com.attilax.collection.CollectionUtils;
import com.attilax.concur.IconcurTest;
import com.attilax.concur.dataISEmptyEx;
import com.attilax.io.filex;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.noticer.jsonFldSerialIgone;
import com.attilax.ref.cantFindIDFieldEx;
import com.attilax.time.timeUtil;
import com.focusx.dao.IEquipmentDAO;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.elmt.GvMaterial;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.pager.Pager;
import com.focusx.playRec.baseDAO;
import com.focusx.pojo.Equipment;
import com.focusx.programme.dao.IProgrammeDao;
import com.focusx.publish.dao.IPublishDao;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.service.IPublishService;
import com.focusx.push.FailState;
import com.focusx.push.FileNotExist;
import com.focusx.push.TaskNoticer;
import com.focusx.push.pushX;
import com.focusx.util.Utils;

public class PublishServiceImpl implements IPublishService,IconcurTest<GvPublish> {

	protected static Logger logger = Logger.getLogger(PublishServiceImpl.class);

	/**
	 * 发布
	 */
	private IPublishDao publishDao;

	/**
	 * 组织架构
	 */
	private IEquipmentDAO equipmentDAO;
	/**
	 * 节目单
	 */
	private IProgrammeDao programmeDao;

	@Override
	public List<GvPublish> getPublishList(Pager<GvPublish> pager,
			Map conditionMap) {
		// TODO Auto-generated method stub
		return publishDao.getPublishList(pager, conditionMap);
	}

	@Override
	public GvPublish getPublish(Integer publishId) {
		// TODO Auto-generated method stub
		return publishDao.getPublish(publishId);
	}
 
	@Override
	/**
	 * save 
	 */
	public boolean insert(GvPublish publish, String equipmentIds) {
		// TODO Auto-generated method stub
		boolean msg = false;
		List<Equipment> groupList = equipmentDAO
				.getListByDepartId(equipmentIds);
		filex.save_SF("getEqListByDepartId is null:"+String.valueOf(equipmentIds), "c:\\pubSaveErr\\eqListIsEmpty"+filex.getUUidName()+".txt");
		
		
		List<GvPublish> list = new ArrayList<GvPublish>();
		if (Utils.isNotEmpty(groupList)) {
			for (Equipment equipment : groupList) {
				GvPublish gvPublish = new GvPublish();
				gvPublish.setPublishType(publish.getPublishType());
				gvPublish.setInterruptedStartTime(publish
						.getInterruptedStartTime());
				gvPublish.setStartTime(publish.getStartTime());
				gvPublish.setEndTime(publish.getEndTime());
				gvPublish.setStatus(publish.getStatus());
				gvPublish.setPublishManId(publish.getPublishManId());
				gvPublish.setPublishTime(publish.getPublishTime());
				gvPublish.setReviewManId(publish.getReviewManId());
				gvPublish.setReviewTime(publish.getReviewTime());
				gvPublish.setProgarmmeId(publish.getProgarmmeId());
				gvPublish.setMome(publish.getMome());
				gvPublish.setInterruptedTimeType(publish.getInterruptedTimeType());
				//设备
				gvPublish.setEquipmentId(equipment.getEquipmentId());
				list.add(gvPublish);
				publish.setPublishId(gvPublish.getPublishId());
			}
		}
		msg = publishDao.insertBatch(list);
		return msg;
	}

	@Override
	public boolean update(GvPublish publish) {
		// TODO Auto-generated method stub
		return publishDao.update(publish);
	}

	@Override
	public boolean delete(Integer publishId) {
		// TODO Auto-generated method stub
		return publishDao.delete(publishId);
	}

	@Override
	public boolean deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		return publishDao.deleteBatch(ids);
	}

	public IPublishDao getPublishDao() {
		return publishDao;
	}

	public void setPublishDao(IPublishDao publishDao) {
		this.publishDao = publishDao;
	}

	public IProgrammeDao getProgrammeDao() {
		return programmeDao;
	}

	public void setProgrammeDao(IProgrammeDao programmeDao) {
		this.programmeDao = programmeDao;
	}

	public IEquipmentDAO getEquipmentDAO() {
		return equipmentDAO;
	}

	public void setEquipmentDAO(IEquipmentDAO equipmentDAO) {
		this.equipmentDAO = equipmentDAO;
	}

	/* (non-Javadoc)
	 * @see com.attilax.concur.IconcurTest#maxID(java.lang.Class)
	 * @author  attilax 老哇的爪子
	 *@since  o8o m_w_0$
	 */
	@Override public int maxID(Class<?> cls) throws cantFindIDFieldEx, dataISEmptyEx {
		// attilax 老哇的爪子  m_w_0   o8o 
		return 0;
		
	}

	/* (non-Javadoc)
	 * @see com.attilax.concur.IconcurTest#count()
	 * @author  attilax 老哇的爪子
	 *@since  o8o m_w_0$
	 */
	@Override public int count() {
		// attilax 老哇的爪子  m_w_0   o8o 
		return 1;
		
	}
  ThreadLocal<Integer> appointedPubid=new ThreadLocal<Integer>();

private pushX pushx=new pushX();
	/* (non-Javadoc)
	 * @see com.attilax.concur.IconcurTest#op()
	 * @author  attilax 老哇的爪子
	 *@since  o8o m_w_0$
	 */
	@Override public <k,v> Object op(Map<k,v> mapx) {
		// attilax 老哇的爪子  m_w_0   o8o 
		int count = count();
		appointedPubid.set((Integer) mapx.get("pubid"));
		List li = peek(count);
		CollectionUtils.each_safe(li, new Closure<GvDownloadTask, Object>() {

			@Override public Object execute(GvDownloadTask arg0) throws Exception {
				// attilax 老哇的爪子 m_p_t o8o
			//	remove_forever(arg0);
				return null;

			}
		});
		 
		return null;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o8o m_x_7   
	
	 * @param count
	 * @return
	 */
	private List peek(int count) {
		// attilax 老哇的爪子  m_x_7   o8o 
		int pubid=appointedPubid.get();
		
		return null;
		
	}
	
	
	


	
}
