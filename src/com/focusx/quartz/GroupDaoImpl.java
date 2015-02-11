package com.focusx.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.focusx.entity.TMbGroup;
import com.focusx.entity.TMbTag;
import com.focusx.util.Constant;

public class GroupDaoImpl {
	
	public Session getSession() {
		if (Constant.sessionFactory != null)
			return Constant.sessionFactory.openSession();
		return null;
	}
	
	public List<TMbGroup> getTopBranch(){
		Session session = getSession();
		if(session != null){
			try {
				String sql = "select groupid,groupname from t_mb_group where parentId=0";
				Query query = session.createSQLQuery(sql).addScalar("groupid", StandardBasicTypes.INTEGER)
						.addScalar("groupname", StandardBasicTypes.STRING).setResultTransformer(Transformers.aliasToBean(TMbGroup.class));
				return query.list();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
		}
		return null;
	}
	
	public List<TMbTag> getTagByGroupid(Integer groupid){
		Session session = getSession();
		if(session != null){
			try {
				String hql = "from TMbTag where groupid="+groupid;
				Query query = session.createQuery(hql);
				return query.list();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				session.close();
			}
		}
		return null;
	}
	
	public Integer getWeixinUserWithNullBranchCount(){
		Session session = getSession();
		if(session != null){
			try {
				String sql = "select count(*) from t_mb_weixinuser where subscribe=1 and groupid is null or groupid=0";
				Query query = session.createSQLQuery(sql);
				return (Integer) query.uniqueResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				session.close();
			}
		}
		return 0;
	}
	
	
	
	public void updateToGroupId(final Map<Integer, Integer> data) {
		Session session = getSession();
		try{
			session.doWork(new Work(){
				String sql = "update t_mb_weixinuser set groupid =? where userId = ?";
				Set<Integer> keySet = data.keySet();
				public void execute(Connection conn) throws SQLException {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					int i = 0;
					for(Integer userId : keySet){
						i++;
						pstmt.setInt(1, data.get(userId));
						pstmt.setInt(2, userId);
						
						pstmt.addBatch();
						if(i%500 == 0 && i > 0){
							pstmt.executeBatch();
							pstmt.clearBatch();
						}
					}
					pstmt.executeBatch();
				}
			});
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	public TMbGroup getBranchByGroupName(String groupname) {
		Session session = getSession();
		if(session != null){
			try {
				String hql = "from TMbGroup where groupname='"+groupname+"'";
				Query query = getSession().createQuery(hql);
				return (TMbGroup) query.uniqueResult();
			} catch (Exception e) {
				e.printStackTrace();
			}  finally{
				session.close();
			}
		}
		return null;
	}

}
