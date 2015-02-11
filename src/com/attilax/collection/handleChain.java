package com.attilax.collection;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.attilax.time.Handler;
import com.attilax.util.Func_4SingleObj;
@SuppressWarnings("all") @Deprecated
public class handleChain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//todox o7g if trans by arr ,is val trans ,,not ref trans ..
	//object ya bsin ,yash val trans..only list ok..
	public static Object handleReq(List bingo,Handler... proccsor2) throws NoHandlerCanProcessReqExcpt   {
	
	//	Handler bingo=proccsor2[0];
		 List li =Arrays.asList(proccsor2);
		// List li=li2.subList(1, li2.size());
		// li.get(0)
		 final List li_r=new ArrayList ();
		 Handler bingoPrcsr;
		try {
			bingoPrcsr = (Handler) Any.any(li,new Func_4SingleObj(){

				@Override
				public Object invoke(Object o) {
					Handler p=(Handler) o;
					try {
						Object rst=p.handleReq("");
						li_r.add(rst);
						return true;
					} catch (ParseException e) {
						 return false;
					} catch (Exception e) {
						//  attilax 老哇的爪子 h47i   o7l   
						 return false;
					}
					 
				}});
		} catch (NoBinElmtExcpt e) {
			 throw new NoHandlerCanProcessReqExcpt();
		}
		 bingo.add(bingoPrcsr);
		return li_r.get(0);
	}

}
