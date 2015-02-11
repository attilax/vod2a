/**
 * 
 */
package com.attilax.review;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.attilax.BaseX;
import com.attilax.Closure;
import com.attilax.Closure2;
import com.attilax.core;
import com.attilax.biz.oplog.IOpLogger;
import com.attilax.dsm.BaseSvsFinal;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.persistence.PX;
import com.attilax.time.timeUtil;
import com.attilax.util.DwrX;
import com.focusx.auth.authx;
import com.focusx.elmt.GvMaterial;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.util.OperLogUtil4vod;

/**
 * @author ASIMO
 *
 */

public class ReviewX extends BaseX implements IReview  {
	
public	IReview targetSvs;

		/**
		@author attilax 老哇的爪子
		 * @return 
		@since   ob2 m_58_z
		 
		 */

	IOpLogger oplgr=new IOpLogger() {
	};
	@SuppressWarnings("all")
	public String save(Map<String, String> m) {
		try {
			System.out.println("");
			m.put("targetid", m.get("targetid").toString().trim());
			Review rv=new Closure2<Map, Review>() {

				@Override
				public Review execute(Map arg0) {
					//new BaseSvsFinal().findByPropertyss(arg0, Review.class);
					Criteria c= px.getSession().createCriteria(Review.class);
					c.add(Restrictions.eq("targetid", Integer.parseInt(arg0.get("targetid").toString().trim())  ));
					c.add(Restrictions.eq("targettype", arg0.get("targettype")));
					Review r=	(Review) c.uniqueResult();
					if(r==null)
						return new Review();
				//	Review t=px.findByHql(" from Review where targetid=@id   and targettype='@t' " )
					return r;
				}
			}.execute(m);
					
					
				//	
			core.copyProperties(rv, m);
			rv.setTimex(timeUtil.getTimestamp());
			rv.setReviewerid( Integer.parseInt(authx.getuid( DwrX.getReq()))  );
			rv.setReviewer(authx.getCurUsername(DwrX.getReq()));
			px.merge(rv);
			
			ReviewHistry rh=new ReviewHistry();
			core.copyProperties(rh, rv);
			
			px.save(rh);
			
			
			
			try {
				oplgr.log("审核"+m.get("datatypeNdecrib") +" 审核结果:"+rv.getRvw_txt());
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "ok";
		} catch (Exception e) {
			filex.saveLog(e, "c:\\e");
			return core.toJsonStrO88(e);
		}
		
		
	}

			/**
			@author attilax 老哇的爪子
			@since   ob3 2_49_6
			 
			 */
		public Object findRevwList(Map<String, String> m) {
			Criteria c= px.getSession().createCriteria(ReviewHistry.class);
			c.add(Restrictions.eq("targetid", Integer.parseInt(m.get("targetid").toString())  ));
			c.add(Restrictions.eq("targettype", m.get("targettype")));
			c.addOrder(Order.desc("id"));
			c.setMaxResults(30);
			List<ReviewHistry> li=c.list();
			for (ReviewHistry hst : li) {		 
				 if(targetSvs!=null)
				 {
					TargetObj to = targetSvs.convertObj2Targetobj(hst);
			 
					hst.setTar(to);
				 }
				 
			}
			
			return li;
		}
		
		public static String stateFmt(Integer state)
		{
			try {
				 if(state==null)
						return "待审核";
				if(1==state)
					return "审核通过";
				if(0==state)
					return "未通过";
			
			} catch (Exception e) {
				return "...";
			}
			return null;
			
		}

			/**
			@author attilax 老哇的爪子
			 * @param dataType 
			@since   ob7 h_d_52
			 
			 */
		public   void reviewPassedCheck_shouldBeYetNoReviewOrBack(int id, String dataType) {
			Criteria c= px.getSession().createCriteria(Review.class);
			c.add(Restrictions.eq("targetid",  id ));
			c.add(Restrictions.eq("targettype", dataType));
			Review r=	(Review) c.uniqueResult();
			
		}

			/* (non-Javadoc)
			 * @see com.attilax.review.IReview#setReviewObj4List(java.util.List)
			 */
			@Override
			public void setReviewObj4List(List data) {
				// TODO Auto-generated method stub
				
			}

			/* (non-Javadoc)
			 * @see com.attilax.review.IReview#convertObj2Targetobj(com.attilax.review.ReviewHistry)
			 */
			@Override
			public TargetObj convertObj2Targetobj(ReviewHistry bizobj) {
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see com.attilax.review.IReview#reviStateFiltedIds(java.lang.Object)
			 */
			@Override
			public String reviStateFiltedIds(Object p) throws NoFiltEx {
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see com.attilax.review.IReview#setReviewPassedPropFilter(java.lang.Object)
			 */
			@Override
			public Object setReviewPassedPropFilter(Object p) {
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see com.attilax.review.IReview#hasReviewAuth(java.lang.Object)
			 */
			@Override
			public boolean hasReviewAuth(Object p) {
				// TODO Auto-generated method stub
				return false;
			}

			/* (non-Javadoc)
			 * @see com.attilax.review.IReview#reviewPassedCheck(java.lang.Object, java.lang.String)
			 */
			@Override
			public boolean reviewPassedCheck(Object obj, String dataType)
					  {
				Criteria c= px.getSession().createCriteria(Review.class);
				c.add(Restrictions.eq("targetid",  (Integer)obj ));
				c.add(Restrictions.eq("targettype", dataType));
				Review r=	(Review) c.uniqueResult();
				if(r==null)
					return false;
				if(r.getState()==1)
					return true;
				
				return false;
			}

			/* (non-Javadoc)
			 * @see com.attilax.review.IReview#reviewPassedCheck(java.lang.Object)
			 */
	 

}
