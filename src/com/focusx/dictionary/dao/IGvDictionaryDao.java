package com.focusx.dictionary.dao;

import java.util.List;
import java.util.Map;

import com.focusx.dictionary.entity.GvDictionary;
import com.focusx.pager.Pager;

/**
 * 
 * 
 * @author：luojun
 * 
 * @Program Name：starx BS坐席
 * 
 * @type_name:DictionaryDao
 * 
 * @Description: 数据字典管理
 * 
 * @CreateTime：2014-7-14 下午01:41:16
 * @version
 * 
 */
public interface IGvDictionaryDao {

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:获取数据字典列表(管理操作)
	 * 
	 * @CreateTime: 2014-7-14 下午01:44:03
	 * 
	 * @param page
	 * @param data
	 * @return
	 */
	public List<GvDictionary> getDictionaryList(Pager<GvDictionary> pager,
			Map conditionMap);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:更加编码查询父类和子类数据(用于数据展示)
	 * 
	 * @CreateTime: 2014-7-14 下午01:55:56
	 * 
	 * @param dCode
	 *            父类编码
	 * @return
	 */
	public List<GvDictionary> getDictionaryList(String dPcode);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:获取数据字典列表(管理操作)
	 * 
	 * @CreateTime: 2014-7-14 下午01:44:03
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	public GvDictionary getDictionary(Integer id);
	
	/**
	 * 
	  * Description: 获取对象
	  *  
	  * CreateTime: 2014-8-18 上午10:26:45
	  *
	  * @param DCode 编码
	  * @param DPcode 父编码
	  * @return
	 */
	public GvDictionary  getDictionaryByCodeAndPcode(String DCode,String DPcode);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:插入数据字典
	 * 
	 * @CreateTime: 2014-7-14 下午01:51:18
	 * 
	 * @param dictionary
	 * @return
	 */
	public boolean insert(GvDictionary dictionary);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description:更新数据字典
	 * 
	 * @CreateTime: 2014-7-14 下午01:53:04
	 * 
	 * @param dictionary
	 * @return
	 */
	public boolean update(GvDictionary dictionary);

	/**
	 * 
	 * @author：luojun
	 * 
	 * @Description: 删除数据字典
	 * 
	 * @CreateTime: 2014-7-14 下午01:54:43
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
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
	public boolean  batchdelete(List<Integer> ids);

}
