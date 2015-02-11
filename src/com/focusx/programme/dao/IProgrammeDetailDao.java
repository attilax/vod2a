package com.focusx.programme.dao;

import java.util.List;
import java.util.Map;

import com.focusx.pager.Pager; 
import com.focusx.programme.entity.GvProgrammeDetail;

/**
 * 
 * @author luojun
 * 
 * Program Name:starx BS坐席
 *
 * Description: 节目单详情详情
 * 
 * CreateTime: 2014-7-16  下午3:14:15
 *  
 *
 */
public interface IProgrammeDetailDao {
	 

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:批量插入节目单详情
	 * 
	 * @CreateTime: 2014-7-14 下午01:51:18
	 * 
	 * @param list
	 * @return
	 */
	public boolean insertBatch(List<GvProgrammeDetail> list);

	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:批量删除数据
	  *  
	  * @CreateTime: 2014-7-15 下午03:13:13
	  * 
	  * @param programmeId 节目单id
	  * @return
	 */
	public boolean  deleteBatch(List<Integer> programmeIds);
	


	/**
	 * 
	  * Description:删除数据 根据节目单
	  *  
	  * CreateTime: 2014-7-21 上午9:48:39
	  *
	  * @param programmeId
	  * @return
	 */
	public boolean  deleteByProgrammeId(Integer programmeId);
	
	/**
	 * 
	  * Description:根据节目单ID 获取节目单详情
	  *  
	  * CreateTime: 2014-7-21 下午2:13:13
	  *
	  * @param programmeId
	  * @return
	 */
	public List<GvProgrammeDetail> getProgrammeDetail(Integer programmeId);
}
