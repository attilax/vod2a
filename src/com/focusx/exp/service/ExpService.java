package com.focusx.exp.service;

import java.util.List;
import java.util.Map;

import com.focusx.exp.dao.IExpDao;

public class ExpService implements IExpService {

	private IExpDao expDao;
	
	@Override
	public List playWater(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.playWater(QueryPropertyssMap);
	}

	@Override
	public List playCount(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.playCount(QueryPropertyssMap);
	}

	@Override
	public List downWater(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.downWater(QueryPropertyssMap);
	}

	@Override
	public List downMana(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.downMana(QueryPropertyssMap);
	}

	@Override
	public List downCount(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.downCount(QueryPropertyssMap);
	}

	public IExpDao getExpDao() {
		return expDao;
	}

	public void setExpDao(IExpDao expDao) {
		this.expDao = expDao;
	}

	@Override
	public List publish(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.publish(QueryPropertyssMap);
	}

	@Override
	public List expStore(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.expStore(QueryPropertyssMap);
	}
 
	@Override
	public List playCountByMtrlArea(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.playCountByMtrlArea(QueryPropertyssMap);
	}

	@Override
	public List downCountByMtarlBranch(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.downCountByMtarlBranch(QueryPropertyssMap);
	}

	@Override
	public List downCountByMtarlStore(Map<String, String> QueryPropertyssMap) {
		// TODO Auto-generated method stub
		return expDao.downCountByMtarlStore(QueryPropertyssMap);
	}
	

}
