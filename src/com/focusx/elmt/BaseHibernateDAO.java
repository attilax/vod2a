package com.focusx.elmt;

import com.focusx.util.HibernateSessionFactory;

import org.hibernate.Session;


/** base o8
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
@Deprecated
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(HibernateSessionFactory.getSession());
	}
	
}