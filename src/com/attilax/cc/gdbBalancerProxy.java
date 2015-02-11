package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import m.eml.emlC;
import m.numpkg.numUtil;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.god;
import com.attilax.util.securyInt;
import com.attilax.util.tryX;

public class gdbBalancerProxy extends ccParser implements Ibalance  {

	public  gdbBalancerProxy()
	{
		this.acc="gdb";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		//String path="C:\\ccx\\尊敬的艾龙先生，广发卡2014年02月电子对账单.eml";
		String path="C:\\ccx\\cc c8\\尊敬的艾龙先生，广发卡2013年08月电子对账单.eml";
		 path="C:\\ccx\\cc cb\\尊敬的艾龙先生，广发卡2013年12月电子对账单.eml";
		 path="c:\\ccx\\尊敬的艾龙先生，广发卡2014年02月电子对账单.eml";
		 
		
		String txt = emlC.eml2txt(path);
		 
		filex.write(path + ".txt", txt);
		
		gdbBalancerProxy c=new gdbBalancerProxy();
		 
		item it=	c.getBalanceItem(path);
		System.out.println(c.emltxt);
		
		
	System.out.println(listUtil.toString_jsonFmt(it));
	
	item o2=c.miniPayment();
	System.out.println(listUtil.toString_jsonFmt(o2));

	}




	
	/**
	 * o3i
	 */
	 public item getBalance_itemFmt(String path) {
		 //Balance 25,327.69 本期最低还款额
		this.emltxt=emlC.eml2txt(path);
		 String left="Balance";
		 String right="本期最低还款额";
		 String s=strUtil.Mid(this.emltxt, left, right);
		 float bls=trimBalanceMinipay_o3(s);
			 
			//o39
			 
			 
				item it=new item();
				it.acc="gdb";
				
				it.cardno=cardno;
				it.accx=it.acc+it.cardno;
				it.m=bls;
			return it;

			

		
			
		 
			//return li;
		}
	 
		public Element getTable(Document doc, String keyword,String paichuWord) {
			
			Element tb_mid=super.getTable(doc, keyword, paichuWord);
			filex.write("c:\\tableFist.htm", tb_mid.toString());
			Element 	tb_mini=tb_mid.getElementsByTag("table").get(21);
			this.table=tb_mini;
		//	String s=tb_mini.toString();
		//	FileService.writeFile("c:\\table.txt",s);
			return tb_mini;
			
		}
		
		public float getBalance(Element tb_mid) {
			String bls_str = CellValue(tb_mid, 1, 2);

			bls_str = bls_str.replaceAll("RMB", "");
			bls_str = bls_str.replaceAll(",", "");
			bls_str=numUtil.trim(bls_str);
			float bls = securyInt.getFloat(bls_str, 0);
			return bls;
		}
		
		@Override
		public item miniPayment() {
			
			 
		      item blsItem=new tryX<item>()
		      {

				@Override
				public  item item(Object t) throws Exception {
					 
					return  gdbBlancer.miniPayment(); 
				}
				
				 
		    	  
		      }.$(null);
		    	 
		      if(blsItem==null)
		      {
		    	    blsItem=new tryX<item>()
			      {

					@Override
					public item item(Object t) throws Exception {
						 
						 return gdbBlancer_o3_instance.miniPayment( );
					}
					
					 
			    	  
			      }.$(null);
		    	 
		      }
		      
		      if(blsItem==null)
		      {
		    	return balancerInstace.miniPayment();  
		      }
		      return blsItem;
		}


		  final gdbBalancer gdbBlancer=new gdbBalancer();
		  gdbBalancer_O3 gdbBlancer_o3_instance=new gdbBalancer_O3();
		  Ibalance balancerInstace;
		@Override
		public item getBalanceItem(final String filepath) {
		    
		      item blsItem=new tryX<item>()
		      {

				@Override
				public item item(Object t) throws Exception {
					 
					return  gdbBlancer.getBalanceItem(filepath); 
				}
				
				 
		    	  
		      }.$(null);
		      this.emltxt=gdbBlancer.emltxt;
		      core.log("o3i1"+blsItem);
		    	core.log(this.emltxt);
		      if(blsItem==null)
		      {
		    	  core.log(" proser gdbBlancer is fail satrt  gdbBlancer_o3_instance ");
		    	  blsItem=new tryX<item>()
			      {

					@Override
					public item item(Object t) throws Exception {
						 
						 
						 return gdbBlancer_o3_instance.getBalanceItem(filepath);
					}
					
					 
			    	  
			      }.$(null);
		    	 
		      }else
		      {
		    	  core.log("gdbBlancer get balance sucess.. ");
		      }
		      if(blsItem==null)
		      {
		    	  core.log(" proser gdbBlancer_o3_instance is fail satrt  gdbBalancer_o4 ");
		    	  gdbBalancer_o4 gc=new gdbBalancer_o4();
		    	  balancerInstace=gc;
		    	  return gc.getBalanceItem(filepath);
		      }else
		      {
		    	  core.log("gdbBlancer_o3_instance get balance sucess.. ");
		      }
		      
		      
		      
		      
		      
		      return blsItem;
			 
		}

}
