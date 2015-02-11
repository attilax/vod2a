/**
 * @author attilax 老哇的爪子
	@since  o01 2_t_59$
 */
package  com.attilax.System.Web.UI.WebControls;

import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.corePkg.JSONArray;
import com.attilax.corePkg.JSONObject;
import com.attilax.io.filex;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o01 2_t_59$
 */
public class DataGridView {
	//List,map  or   jsonArray
	public Object DataSource;
	public String column;
	public String columnCN;

	//  attilax 老哇的爪子 2_t_59   o01   
	public static void main(String[] args) {
		String s=filex.read("c:\\dt.txt");
		
		net.sf.json.JSONArray ja= net.sf.json.JSONArray.fromObject(s);
		
		DataGridView GridView1 = new DataGridView();
		Object myds = null;
		GridView1.DataSource = new JSONArray() {
			{
				add(new JSONObject().put("a", "Av").put("b", "bv"));
			}
		}.$();
		GridView1.DataSource=ja;
		GridView1.column="equipmentId";

	    //    GridView1.DataKeyNames = new string[] { "id" };//主键

	        GridView1.DataBind();
	        
	    List<Map>   li= (List<Map>) GridView1.DataSource; 
	    for (Map map : li) {
		System.out.println(map);	
		System.out.println(Mapx.get(map,"eq.dpt.groupname"));
		}
	}

	/**
	@author attilax 老哇的爪子
		@since  o01 2_x_d   
	
	 */
	public void DataBind() {
		// attilax 老哇的爪子  2_x_d   o01 
		
	}
}

//  attilax 老哇的爪子