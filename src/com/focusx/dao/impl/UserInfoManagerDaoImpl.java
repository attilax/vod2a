package com.focusx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;

import com.attilax.io.filex;
import com.attilax.page.PageUtil;
import com.focusx.dao.UserInfoManagerDao;
import com.focusx.entity.TUserUsers;

public class UserInfoManagerDaoImpl extends baseDao implements UserInfoManagerDao {
	
	protected static Logger logger = Logger.getLogger("UserInfoManagerDaoImpl");
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public boolean deleteRoleFromUser(final int roleId, final String[] userIds) {
		boolean result = true;
		Session session = getSession();
		try{
			session.doWork(new Work(){
				String sql = "delete from t_user_usertorole where UserID=? and RoleID=?";
				public void execute(Connection conn) throws SQLException {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					for(int i = 0;i<userIds.length;i++){
						pstmt.setInt(1, Integer.parseInt(userIds[i].trim()));
						pstmt.setInt(2, roleId);
						
						pstmt.addBatch();
						if(i%500==0 && i>0){
							pstmt.executeBatch();
							pstmt.clearBatch();
						}
					}
					pstmt.executeBatch();
				}
			});
		}catch(Exception e){
			result = false;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public boolean insertUserIntoRole(final int roleId, final String[] userIds) {
		boolean result = true;
		Session session = getSession();
		try{
			session.doWork(new Work(){
				String sql = "insert into t_user_usertorole(UserID,RoleID) values(?,?)";
				public void execute(Connection conn) throws SQLException {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					for(int i = 0;i<userIds.length;i++){
						pstmt.setInt(1, Integer.parseInt(userIds[i].trim()));
						pstmt.setInt(2, roleId);
						
						pstmt.addBatch();
						if(i%500==0 && i>0){
							pstmt.executeBatch();
							pstmt.clearBatch();
						}
					}
					pstmt.executeBatch();
				}
				
			});
		}catch(Exception e){
			result = false;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		return result;
	}
	public List<TUserUsers> getAllUsers(){
		String hql = "from TUserUsers a order by a.id asc";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	public List<TUserUsers> getPagedUsers(int firstResult,int pagesize) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select row_number() over(order by id desc) as rowNum,* from t_user_users)as users");
		if(firstResult > 0 && pagesize > 0){
			sql.append(" where rowNum between "+firstResult+" and "+ (firstResult+pagesize-1));
		}else {
			sql.append(" where rowNum between 1 and 10");
		}
		sql.append(" order by id desc");
		
		Query query = getSession().createSQLQuery(sql.toString()).addEntity(TUserUsers.class);
		return query.list();
	}
	
	public int getUserTotalCount(){
		String sql = "select count(ID) from t_user_users ";
		Query query = getSession().createSQLQuery(sql);
		//把结果转换为Number，因为Number是Integer, BigInteger, Long等的base class
		int totalNum = ((Number) query.uniqueResult()).intValue();
		return totalNum;
	}
	
	public List<TUserUsers> getSearchUsers(String condition,int currpage,int pagesize){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select row_number() over(order by id desc) as rowNum,* from t_user_users where 1=1 ");
		if(condition.length()>0){
			sql.append(condition);
		}
		if(proFltr.get()!=null)
			sql.append(proFltr.get().getExp());
		
		//oae
		int firstResult=PageUtil.getStartIndexFirstResult(String.valueOf(currpage), 10);
		pagesize=10;
		//end
		sql.append(" )as users");
		if(firstResult > 0 && pagesize > 0){
			sql.append(" where rowNum between "+firstResult+" and "+ (firstResult+pagesize-1));
		}else {
			sql.append(" where rowNum between 1 and 10");
		}
		filex.save_SF(sql.toString(),"c:\\usermod\\"+filex.getUUidName()+".txt");
		
		Query query = getSession().createSQLQuery(sql.toString()).addEntity(TUserUsers.class);
		return query.list();
	}
	
	public int getSearchUsersCount(String condition){
		//String sql = "select count(ID) from t_user_users ";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(ID) from t_user_users");
		buffer.append(" where 1=1");
		if(condition.length()>0){
			buffer.append(condition);
		}
		//ob9
		if(proFltr.get()!=null)
			buffer.append(proFltr.get().getExp());
		
		Query query = getSession().createSQLQuery(buffer.toString());
		//把结果转换为Number，因为Number是Integer, BigInteger, Long等的base class
		int totalNum = ((Number) query.uniqueResult()).intValue();
		return totalNum;
	}

	public TUserUsers getUserById(int userId) {
		String hql = "from TUserUsers a where a.id = "+userId;
		Query query = getSession().createQuery(hql);
		TUserUsers user = (TUserUsers) query.uniqueResult();
		return user;
	}
	public TUserUsers getUserByWorkNo(String workNo){
		String hql = "from TUserUsers where workNo = '"+workNo+"'";
		Query query = getSession().createQuery(hql);
		TUserUsers user = (TUserUsers) query.uniqueResult();
		return user;
	}
	public boolean deleteUsersByIds(final String[] ids){
		boolean result = true;
		try {
			getSession().doWork(new Work(){

				public void execute(Connection conn) throws SQLException {
					String sql = "delete from t_user_users where id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					for(int i = 0;i<ids.length;i++){
						pstmt.setInt(1, Integer.parseInt(ids[i].trim()));
						pstmt.addBatch();
					}
					//执行批量操作
					pstmt.executeBatch();
				}
			});
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally{
			return result;
		}
	}

	public Number insertUserInfo(TUserUsers user) {
		Number id = -1;
		Session session = getSession();
		session.save(user);
		session.flush();
		id = user.getId();
		return id;
	}

	public boolean updateUserInfo(TUserUsers user) {
		boolean result = true;
		try {
			getSession().update(user);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally{
			return result;
		}
	}

	public TUserUsers login(Map<String, String> data){
		String hql = "from TUserUsers where name='"+data.get("username")+"' and PassWord='"+data.get("password")+"'";
		Query query = getSession().createQuery(hql);
		TUserUsers user = (TUserUsers)query.uniqueResult();
		return user;
	}

	public TUserUsers getUserByName(String name) {
		String hql = "from TUserUsers where name='"+name+"'";
		Query query = getSession().createQuery(hql);
		TUserUsers user = (TUserUsers)query.uniqueResult();
		return user;
	}

	@Override
	public List<TUserUsers> getAllUsersByRoleIds(Map<String, Object> data) {
		StringBuffer hql = new StringBuffer(" from TUserUsers u where 1=1 ");
		if(data.containsKey("roleId")){
			hql.append(" and u.roleIds like '%" + data.get("roleId") + "%' ");
		}
		if(data.containsKey("userName")){
			hql.append(" and u.name like '%" + data.get("userName") + "%' ");
		}
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<TUserUsers> getUsersByIds(String ids) {
		String hql = " from TUserUsers u where u.id in (" + ids + ") ";
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}
}
