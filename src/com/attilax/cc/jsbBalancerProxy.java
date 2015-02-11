package com.attilax.cc;

import java.util.List;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.text.strUtil;
import com.attilax.util.god;
import com.attilax.util.securyInt;
import com.attilax.util.tryX;

import m.eml.emlC;
import m.numpkg.numUtil;

public class jsbBalancerProxy extends ccParser  implements Ibalance {

	



	public jsbBalancerProxy()
	{
		this.acc="jsb";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		jsbBalancerProxy c = new jsbBalancerProxy();
		// c.tableKeyword = "人民币/美元账户";
		// c.tableKeywordExclude = "本期应还款金额 ";
		String filepath = "C:\\ccx\\cc ca\\中信银行信用卡电子账单(4).eml";
		filepath="C:\\ccx\\cc ca\\中信银行信用卡电子账单(4).eml";
		filepath="c:\\ccx\\cc ca\\中信银行信用卡电子账单(1).eml";
		filepath="c:\\ccx\\cc ca\\中信银行信用卡电子账单(3).eml";
		 
		item it = c.getBalanceItem(filepath);
		System.out.println(c.emltxt);	

		item it2=c. miniPayment();
		
		System.out.println(listUtil.toString_jsonFmt(it));
		core.print(it2);

	}

	/**
	 * o3i
	 * //本期应还款额 Current Balance RMB 1,806.38 USD
	 */
	public item getBalance_itemFmt(String path) {

		final String txt = emlC.eml2txt(path);
		this.emltxt=txt;
		
		return new tryX<item>(){
			
			

			public Object log(Exception e) {
			//	MoodUserIndexService.logger.error(god.getTrace(e));
				core.logger.error("-----catch except la ..");
				core.logger.error(god.getTrace(e));
				return e;
			}

			@Override
			public  item  item(Object t) throws Exception {
				 String left = "RMB";
				String add = com.attilax.text.strUtil.Mid(txt, left, "USD");
				item it = new item();
				it.acc = "jsb";

				// it.cardno = cardno;
				it.accx = it.acc + it.cardno;
				String bls = numUtil.trim(add);
				it.m = securyInt.getFloat(bls, 0);
				return it;
			}
			
		}.$(null);
		
		// return add;

	}
	
	
	

	@Override
	public item miniPayment() {
	//	return null;
		
		
		
		
		List<String> li=strUtil.Mid_ListFmt("RMB", "USD",  emltxt);
//		String bls_str=CellValue(table,2,3);
 		float bls = trimBalanceMinipay_o3(li.get(1));
// 
//		
		item it=new item();
		it.acc=this.acc;
		it.m=bls;
		it.accx=it.acc+cardno;
		it.balanceOrMinipay=item.minipay;
		it.date=getAccDate();
		return it;
	}



	private String getAccDate() {
	IgetAccDate gadc=	(IgetAccDate) balancerInstanceO3;
	return gadc.getAccDate();
	//	return null;
	}
	@Override
	public item getBalanceItem(final String filepath) {
		 final jsbBalancer c=new jsbBalancer();
		 balancerInstanceO3=c;
		  item blsItem=new tryX<item>()
	      {

			@Override
			public  item  item(Object t) throws Exception {
				
				return  c.getBalanceItem(filepath); 
			}
			
			 
	    	  
	      }.$(null);
	    	 
	      this.emltxt=c.emltxt;
	      if(blsItem==null)
	      {
	    	jsbBalancerO3 c2=new jsbBalancerO3();
	    	balancerInstanceO3=c2;
	    	  return c2.getBalance_itemFmt_o3(c.emltxt);
	      }
	      return blsItem;
	}

}
