/**
 * 
 */
package com.attilax.datadic;

import java.util.List;

import net.sf.json.JSONArray;

import com.attilax.core;
import com.attilax.corePkg.JSONObject;
import com.attilax.spri.SpringUtil;
import com.focusx.dictionary.entity.GvDictionary;
import com.focusx.dictionary.service.IGvDictionaryServcie;

/**
 * @author ASIMO
 *
 */
public class DatadicX {

	/**
	@author attilax 老哇的爪子
	@since   p1i g_t_55
	 
	 */
	public static void main(String[] args) {
		IGvDictionaryServcie ddc=	SpringUtil.getBean(IGvDictionaryServcie.class);
	 
	  // System.out.println(ja.toString());
	//	gene(ddc);
		System.out.println("--f");
		JSONArray ja=getDicList("");
		for (Object jo : ja) {
			net.sf.json.JSONObject  jo2=(net.sf.json.JSONObject) jo;
			jo2.get("DName").toString();
			jo2.get("DCode");
		}

	}
	
	public  static JSONArray getDicList(String cateid)
	{	IGvDictionaryServcie ddc=	SpringUtil.getBean(IGvDictionaryServcie.class);
		  List li=	ddc.getDictionaryList("appcate");
		   JSONArray ja=JSONArray.fromObject(core.toJsonStrO88(li));
		   return ja;
	}
	
	

	private static void gene(IGvDictionaryServcie ddc) {
		String s=" 动作 喜剧 爱情 恐怖 犯罪 剧情 战争 科幻 悬疑 动画 冒险 文艺 惊悚 魔幻 青春 ";
		String[] a=s.split(" ");
		int start=5;
		for (String it : a) {
			if(it.trim().length()>0)
			{
				GvDictionary d=new GvDictionary();
				d.setDCode(String.valueOf(start));
				d.setDName(it.trim());
				d.setDPcode("appcate");
				d.setDLevel(2);
				ddc.insert(d);
				start++;
				System.out.println(it.trim());
			}
		}
	}

}
