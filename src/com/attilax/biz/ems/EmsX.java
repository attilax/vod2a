package com.attilax.biz.ems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.attilax.core;
import com.attilax.util.DateUtil;
import com.attilax.util.randomx;
import com.mchange.v2.ser.SerializableUtils;

public class EmsX {
private static final File savefile = new File("c:\\t.obj");

/**
 * 
 * @param args
 */
	public static void main(String[] args) {
	//	123456   北京      上海     王三  
		EmsX c=new EmsX();
		String now4t="2014-12-20 18:18:00";
		Map m=c.findByid("123456",now4t);
		c.iniData(m);
	List li=	c.findTraceByid("123456",now4t);
	System.out.println(core.toJsonStrO88(li));
		
	
		
		

	}

private List<Map> findTraceByid(String string, String now4t) {
	List<Map> rzt = null;
	try {
		rzt = (List<Map>) SerializableUtils.unmarshallObjectFromFile(  savefile );
	} catch ( Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rzt;
	
}

private void iniData(Map m) {
	if(Inied())
		return;
	int offset1=19;
 Map frst= geneMap(m,offset1,"快件由【"+m.get("send")+"】分部揽收 ");
 int offset2=offset1+3;
 Map sec= geneMap(m,offset2,"快件从【"+m.get("send")+"】分部发出 "); //快件从【北京】分部发出
 int offset3=offset2+40;
 Map m3= geneMap(m,offset3,"快件到达【"+m.get("to")+"】分部正在处理中 "); //快件从【北京】分部发出
 int offset4=offset3+20;
 Map m4= geneMap(m,offset4,"快件由【"+m.get("to")+"】分部派送中 "); //快件从【北京】分部
 int offset5= offset4+4;
 Map m5= geneMap(m,offset5,"快件由【"+m.get("name")+"】签收  ");
 List<Map> li=new ArrayList();
 li.add(frst);li.add(sec);li.add(m3);li.add(m4);li.add(m5);
 //快件由【本人】
    //
 try {
	SerializableUtils.marshallObjectToFile(li, savefile);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
// core.s
// frst.put("time",)

  
 
  
	
}

private Map geneMap(Map ori, int offsetHour, String msg) {
	Map m=new HashMap();
	String startDate=ori.get("showtime").toString();
	Date d=DateUtil.str2date(startDate, false);
	Date d2=DateUtil.addSecond(d, offsetHour*3600);
	String fullDateSdf = "yyyy-MM-dd HH:";
	String hour=DateUtil.date2str(d2, fullDateSdf);
	  String min =	 randomx.randomMinOrSec( );
	  String sec =	 randomx.randomMinOrSec( );
	  m.put("time",hour+min+":"+sec);
	  m.put("msg", msg);
	return  m  ;
}

private boolean Inied() {
	//return (savefile.exists());
	return false;
	 
}

private boolean notIni() {
	// TODO Auto-generated method stub
	return false;
}

private static Map findByid(String string, String now4t) {
	Map m=new HashMap();
	m.put("no","123456");
	m.put("send", "bj");
	m.put("to", "sh");
	m.put("name","att");
	m.put("showtime", "2014-07-03");
	return m;
}

}
