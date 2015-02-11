package com.attilax.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.net.websitex;

public class BaiduPicSearch {
	public static void main(String[] args) throws IOException {
		String apiurl="http://image.baidu.com/i?tn=baiduimagejson&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1349413075627_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&word=@word&rn=@pagesize&pn=@page";
		String keyword="Cheap Breitling A2736423/B823-1RT Mens 43.5mm Sapphire Automatic Round";
		apiurl=apiurl.replaceAll("@word", URLEncoder.encode(keyword, "utf-8") );
		apiurl=apiurl.replaceAll("@pagesize", "7");
				apiurl=apiurl.replaceAll("@word", "1");
			String json=	websitex.WebpageContent(apiurl);
			filex.save_SF(json, "c:\\img0\\json.txt");
			JSONObject jo=JSONObject.fromObject(json);
			JSONArray ja=jo.getJSONArray("data");
			for (Object object : ja) {
				JSONObject jo2=(JSONObject) object;
				try {
					String imgurl=jo2.getString("objURL");
					
					websitex.down(imgurl, "c:\\img0\\"+filex.getUUidName()+".jpg");
				} catch (Exception e) {
					core.log(e);
				}
				
			}
		
			
				System.out.println(apiurl);
	}

}
