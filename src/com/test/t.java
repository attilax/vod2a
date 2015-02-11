/**
 * @author attilax 老哇的爪子
	@since  o01 3_t_55$
 */
package com.test;
import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.System.Web.UI.WebControls.DataGridView;
import com.attilax.collection.CollX;
import com.attilax.dsm.BaseSvsO9o4Cri;
import com.attilax.io.filex;
import com.attilax.util.DateUtil;
import com.focusx.publish.entity.GvPublish;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
/**
 * @author  attilax 老哇的爪子
 *@since  o01 3_t_55$
 */
public class t {
	//  attilax 老哇的爪子 3_t_55   o01   
	public static void main(String[] args) {
		o9();
		String s=filex.read("c:\\dt.txt");
		JSONArray ja= net.sf.json.JSONArray.fromObject(s);
			BaseSvsO9o4Cri basesvs=new BaseSvsO9o4Cri();
			List li=	basesvs.findByPropertyss(new HashMap(), GvPublish.class);
			
			JSONArray ja2= CollX.List2jsonArr(li);
			
		
		DataGridView GridView1 = new DataGridView();
		Object myds = null;
	 
		GridView1.DataSource=ja2;
		GridView1.column="equipmentId";
		GridView1.columnCN="设备id";

	    //    GridView1.DataKeyNames = new string[] { "id" };//主键

	        GridView1.DataBind();
	        
	        List<Map>  li2=(List<Map>) GridView1.DataSource;
	        for (Map map : li2){
	        	Object x = map.get("startTime");
	        	if(map.get("publishId").toString().equals("1441"))
	        	{
	        		System.out.println("dbg");
	        	   System.out.println(Mapx.get(map,"eq.dpt.groupid"));
	        	}
	        	//{"date":4,"day":4,"hours":0,"minutes":0,"month":8,"nanos":0,"seconds":0,"time":1409814000000,"timezoneOffset":420,"year":114}
			//	System.out.println(  DateUtil.jsonObj2Str(x)  );
	        }
	}

	/**
	@author attilax 老哇的爪子
		@since  o01 i_42_1   
	
	 */
	private static void o9() {
		// attilax 老哇的爪子  i_42_1   o01 
		HttpServletRequest request = null;
		   DataGridView 	GridView1=(DataGridView)request.getSession().getAttribute("GridView1");
		   List<Map>  liO9=(List<Map>) GridView1.DataSource;
	}
}

//  attilax 老哇的爪子