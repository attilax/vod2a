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

public class jsbBalancerO3 extends ccParser  implements Ibalance,IgetAccDate{

	



	public jsbBalancerO3()
	{
		this.acc="jsb";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		jsbBalancerO3 c = new jsbBalancerO3();
		// c.tableKeyword = "人民币/美元账户";
		// c.tableKeywordExclude = "本期应还款金额 ";
		
		String path = "C:\\ccx\\cc ca\\中信银行信用卡电子账单(4).eml";
		c.readEmlTxt(path);
		System.out.println(c.emltxt);	
		item it = c.getBalance_itemFmt_o3(c.emltxt);
	

		item it2=c. miniPayment();
		
		System.out.println(listUtil.toString_jsonFmt(it));
		core.print(it2);

	}

	private void readEmlTxt(String emlPath) {
		final String txt = emlC.eml2txt(emlPath);
		this.emltxt=txt;
		
	}
	/**
	 * o3i
	 * //本期应还款额 Current Balance RMB 1,806.38 USD
	 */
	@Deprecated
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
	
	
	/**
	 * o3i
	 * //本期应还款额 Current Balance RMB 1,806.38 USD
	 */
	public item getBalance_itemFmt_o3(final String emltxt2) {

		
		
		return new tryX<item>(){
			
			
//
//			public Object log(Exception e) {
//			//	MoodUserIndexService.logger.error(god.getTrace(e));
//				core.logger.error("-----catch except la ..");
//				core.logger.error(god.getTrace(e));
//				return e;
//			}

			@Override
			public  item  item(Object t) throws Exception {
				 String left = "本期应还款额";
				String right = "USD";
				String add = com.attilax.text.strUtil.Mid(emltxt2, left, right);
				item it = new item();
				it.acc = "jsb";

				// it.cardno = cardno;
				it.accx = it.acc + it.cardno;
				String bls = numUtil.trim(add);
				it.m = securyInt.getFloat(bls, 0);
				it.date=getAccDate();
				return it;
			}

			
			
		}.$(null);
		
		// return add;

	}
	
	
	public String getAccDate() {
		//2013年10月的信用卡电邮账单
		String s=strUtil.Mid(emltxt, "现为您奉上", "月的信用卡");
		s=datex.fix(s)+"-10";
		return s;
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



	@Override
	@Deprecated
	public item getBalanceItem(String filepath) {
	 
		return (item) getBalance_itemFmt(filepath);
	}

}
