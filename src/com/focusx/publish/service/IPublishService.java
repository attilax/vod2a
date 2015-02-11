package com.focusx.publish.service;

import java.util.List;
import java.util.Map;

import com.focusx.pager.Pager;
import com.focusx.publish.entity.GvPublish;

public interface IPublishService {

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:获取发布列表(管理操作)
	 * 
	 * @CreateTime: 2014-7-14 下午01:44:03
	 * 
	 * @param page
	 * @param data
	 * @return
	 */
	public List<GvPublish> getPublishList(Pager<GvPublish> pager,
			Map conditionMap);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:获取发布列表(管理操作)
	 * 
	 * @CreateTime: 2014-7-14 下午01:44:03
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	public GvPublish getPublish(Integer publishId);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:插入发布
	 * 
	 * @CreateTime: 2014-7-14 下午01:51:18
	 * 
	 * @param publish
	 * @param groups 
	 * @return
	 */
	public boolean insert(GvPublish publish,String equipmentIds);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:更新发布
	 * 
	 * @CreateTime: 2014-7-14 下午01:53:04
	 * 
	 * @param publish
	 * @return
	 */
	public boolean update(GvPublish publish);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description: 删除发布
	 * 
	 * @CreateTime: 2014-7-14 下午01:54:43
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer publishId);
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:批量删除数据
	  *  
	  * @CreateTime: 2014-7-15 下午03:13:13
	  *
	  * @param ids
	  * @return
	 */
	public boolean  deleteBatch(List<Integer> ids);
}
