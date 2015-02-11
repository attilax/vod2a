package com.focusx.publish.dao;

import java.util.List;
import java.util.Map;

import com.focusx.pager.Pager;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.entity.GvPublish;

public interface IPublishDao {

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
	 * @return
	 */
	public boolean insert(GvPublish publish);
	

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:批量插入发布
	 * 
	 * @CreateTime: 2014-7-14 下午01:51:18
	 * 
	 * @param list
	 * @return
	 */
	public boolean insertBatch(List<GvPublish> list);

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


	/**
	 * 
	  * Description:查询数据是否存在
	  *  
	  * CreateTime: 2014-8-8 下午1:48:26
	  *
	  * @param ids 表示节目单ID
	  * @return 存在返回true,不存在返回false
	 */
	public boolean isExist(List<Integer> ids);
	
}
