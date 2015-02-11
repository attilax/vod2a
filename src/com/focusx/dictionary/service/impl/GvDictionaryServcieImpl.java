package com.focusx.dictionary.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.focusx.dictionary.dao.IGvDictionaryDao;
import com.focusx.dictionary.entity.GvDictionary;
import com.focusx.dictionary.service.IGvDictionaryServcie;
import com.focusx.pager.Pager;

/**
 * 
 *     
 * @author：luojun
 * 
 * @Program Name：starx BS坐席
 *
 * @type_name:DictionaryServcieImpl    
 *
 * @Description:   
 *
 * @CreateTime：2014-7-14 下午02:49:11  
 * @version     
 *
 */
public class GvDictionaryServcieImpl implements IGvDictionaryServcie {

	private IGvDictionaryDao dictionaryDao;
	
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return dictionaryDao.delete(id);
	}
	
	public boolean batchdelete(List<Integer> ids) {
		// TODO Auto-generated method stub
		return dictionaryDao.batchdelete(ids);
	}
	public GvDictionary getDictionary(Integer id) {
		// TODO Auto-generated method stub
		return dictionaryDao.getDictionary(id);
	}
	
	public GvDictionary  getDictionaryByCodeAndPcode(String DCode,String DPcode){
		// TODO Auto-generated method stub
		return dictionaryDao.getDictionaryByCodeAndPcode(DCode,DPcode);
	}

	public List<GvDictionary> getDictionaryList(Pager<GvDictionary> pager,
			Map conditionMap) {
		// TODO Auto-generated method stub
		return dictionaryDao.getDictionaryList(pager, conditionMap);
	}

	public List<GvDictionary> getDictionaryList(String dPcode) {
		// TODO Auto-generated method stub
		return dictionaryDao.getDictionaryList(dPcode);
	}

	public boolean insert(GvDictionary dictionary) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new Date().getTime()));
		dictionary.setCreateTime(new Timestamp(new Date().getTime()));
		return dictionaryDao.insert(dictionary);
	}

	public boolean update(GvDictionary dictionary) {
		// TODO Auto-generated method stub
		return dictionaryDao.update(dictionary);
	}

	public IGvDictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public void setDictionaryDao(IGvDictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

}
