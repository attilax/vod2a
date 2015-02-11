/**
 * @author attilax 老哇的爪子
	@since  o79 j448$
 */
package com.attilax.rails;
import com.attilax.core;
import com.attilax.cn.ChineseToPinYin;
import com.attilax.collection.Ireduce;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o79 j448$
 */
public class vodGenrO7d extends FreeMarkertUtil {

	

	/**
	@author attilax 老哇的爪子
		@since  o79 j448$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  j448   o79 
		rootPath="D:\\workspace\\vodx\\Webroot";
		Tmpltpath = rootPath+"\\tmplt\\o7d";
		
		
		
		mod = "elemt";
	 	tits = "素材id, 描述,分类,应用分类,播放时长,上传时间,生效时间,失效时间,创建人";	
		
		
//		mod = "prgrm";
//		tits = "节目单id,描述,总时长,分屏区,素材数量,区域类型abc,创建人,创建时间";
//		
//		
//		mod = "publish";
//		tits = "节目表,门店,类型,插播时间,起止时间,状态,发布人,发布时间,审核人,审核时间";
//		
//		mod = "monitor";
//		tits = "设备id,门店,Ip,终端在线状态,播放状态,当前素材,时间";
//		// geneModPrptyJsonCfgfile(tits);
//		
//		mod = "playrec";
//		tits = "素材,播放单,门店,下载时间,成功失败";
//		
//		mod = "device";
//		tits = "设备id,描述,机身串码,启用状态,门店";
		
//		geneListData(mod, tits);
	 	geneListPage(tits);
		
		geneEditPage(tits);
		System.out.println("--ok");

	}
	/**
	@author attilax 老哇的爪子
		@since  o79 m939$
	
	 * @param tits
	 */
	private static void geneModPrptyJsonCfgfile(String tits) {
		// attilax 老哇的爪子  m939   o79 
//		final String[] tits_a = tits.split(",");
//		Map m=listUtil.reduceO6(tits_a, new HashMap<String, String>(), new Ireduce<String,Map>(){
//
//			@Override public Map $(String o, Map lastRetOBj) {
//				// attilax 老哇的爪子  mcu   o79 
//				lastRetOBj.put(o, value);
//				return lastRetOBj;
//			 
//				
//			}});
//		for (int i = 0; i < tits_a.length; i++){
//			Map m
//		}
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o79 l57q$
	
	 * @param tits
	 */
	private static void geneEditPage(String tits) {
		// attilax 老哇的爪子  l57q   o79 
		final String[] tits_a = tits.split(",");
		Map<String, Object> rootMap = new HashMap<String, Object>() {
			{
				put("listO7", new ArrayList<Map>() {
					{
						for (int i = 0; i < tits_a.length; i++) {
							final String tit = tits_a[i];
							add(new HashMap<String, String>() {
								{
									put("tit", tit);

									String fld = ChineseToPinYin.getPingYin(tit);
									put("fld", fld);
								}
							});

						}
					}
				});
				put("mod",mod);

			}
		};
		modPath =rootPath+ "\\" + mod + "\\";
		String pagePath = mod + "_edit.jsp";
		String geneFile = modPath + pagePath;
		core.log("---geneFile::"+geneFile);
		analysisTemplate(Tmpltpath, "tmplt_add.html", "utf-8", rootMap, geneFile);
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o79 j54v$
	
	 * @param tits
	 */
	private static void geneListPage(String tits) {
		// attilax 老哇的爪子  j54v   o79 
		final String[] tits_a = tits.split(",");
		geneListPage(tits_a);
		
	}
	//  attilax 老哇的爪子 j448   o79   

	/**
	@author attilax 老哇的爪子
		@since  o79 j549$
	
	 * @param mod
	 * @param tits
	 */
	private static void geneListData(String mod, String tits) {
		// attilax 老哇的爪子  j549   o79 
		final String[] tits_a = tits.split(",");
		geneListData(mod,tits_a);
		
	}

	private static void geneListPage(final String[] tits_a) {
		Map<String, Object> rootMap = new HashMap<String, Object>() {
			{
				put("listO7", new ArrayList<Map>() {
					{
						for (int i = 0; i < tits_a.length; i++) {
							final String tit = tits_a[i];
							add(new HashMap<String, String>() {
								{
									put("tit", tit);

									String fld = ChineseToPinYin.getPingYin(tit);
									put("fld", fld);
								}
							});

						}
					}
				});
				put("mod",mod);

			}
		};
		modPath =rootPath+ "\\" + mod + "\\";
		String listPagePath = mod + "_list.jsp";
		analysisTemplate(Tmpltpath, "tmplt_list.html", "utf-8", rootMap, modPath + listPagePath);
		core.copy(Tmpltpath+"\\templete_list.js", modPath+mod+"_list.js");
		String manJs = modPath+mod+"_list_man.js";
		if(!new File(manJs).exists())
			filex.save("//empty", manJs);
		else
			core.log("---info::: file is exit:"+manJs );
	}
}

//  attilax 老哇的爪子