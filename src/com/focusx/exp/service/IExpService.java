package com.focusx.exp.service;

import java.util.List;
import java.util.Map;

public interface IExpService {
	/**
	 * 播放流水
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List playWater(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 播放统计
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List playCount(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 下载流水
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List downWater(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 下载管理
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List downMana(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 下载统计
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List downCount(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 发布
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List publish(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 导出组织架构
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List expStore(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 播放统计
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List playCountByMtrlArea(Map<String, String> QueryPropertyssMap);
	/**
	 * 下载统计--根据素材分公司
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List downCountByMtarlBranch(Map<String, String> QueryPropertyssMap);
	
	/**
	 * 下载统计-根据素材门店分组
	 * @Description:
	 * @Author:luojungong
	 * @param QueryPropertyssMap
	 * @return
	 */
	public List downCountByMtarlStore(Map<String, String> QueryPropertyssMap);

}
