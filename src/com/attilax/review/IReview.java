/**
 * 
 */
package com.attilax.review;

import java.util.List;

import com.focusx.programme.entity.GvProgramme;

/**
 * @author ASIMO
 *
 */
public   interface IReview<atiType> {

	/**
	@author attilax 老哇的爪子
	@since   ob3 j_w_50
	 
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	/**
	 * use by target List page   show rev state .iin the query table 
		@author attilax 老哇的爪子
		@since   ob4 j_44_k
	 */
	public abstract void setReviewObj4List(List<atiType> data);
	
	
	/**
	 * use by target List page...for the query (  review state ) conditobn filter..
	 * get the ids filt by review state  	 * findTargetIds_by_reviewState
	 * eg2: can ret sql condition exp    left join reviTable on  xxxx where reviTable.state=passed
		@author attilax 老哇的爪子
		@since   ob4 j_43_52
	 */
	public String reviStateFiltedIds(Object p) throws NoFiltEx;
	
	/**
	 * use by showReview histroy List ...
	 * convert ReviewHistry  to targetObj
		@author attilax 老哇的爪子
		@since   ob4 j_44_x
	 */
	public abstract TargetObj convertObj2Targetobj(ReviewHistry bizobj);
	

	
	/**jeig haosyo mayon
	 * otehr modul invok tagert d siheu ,,yao filter
		@author attilax 老哇的爪子
		@since   ob4 j_50_57
	 */
	public abstract Object setReviewPassedPropFilter(Object p);
	
	
	/**
	 * use by In list where show review btn..imp by AuthX
		@author attilax 老哇的爪子
		@since   ob8 j_37_41
	 */
	public boolean hasReviewAuth(Object p);
	
	/**
	 * use by 
	 * 4 edit ,del
	 * @param obj
	 * @return
	 * @throws AlreadyReviewPassedEx,ReviewBackEx 
	 */
	boolean reviewPassedCheck(Object obj,String dataType) throws AlreadyReviewPassedEx,ReviewBackEx;

	

}
