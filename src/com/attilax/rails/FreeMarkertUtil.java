package com.attilax.rails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.attilax.cn.ChineseToPinYin;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.util.dirx;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/** freemarker 模板工具
 * 
 * @author Ying-er
 * @time 2010-2-6下午04:07:27
 * @version 1.0 */
public class FreeMarkertUtil {

	public static String Tmpltpath;
	public static String mod;
	public static String tits;
	public static String modPath;
	/** @author attilax 老哇的爪子
	 * @since o78 m4545$
	 * 
	 * @param args */
	public static void main(String[] args) {

		Tmpltpath = "D:\\workspace\\vod\\tmplt";
		mod = "prgrm";
		modPath = "D:\\workspace\\vod\\" + mod + "\\";

		tits = "节目单id,总时长,分屏区,素材数量,描述,区域类型,创建人,创建时间,";
		final String[] tits_a = tits.split(",");
		geneListData(mod, tits_a);

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
				put("mod", mod);

			}
		};

		analysisTemplate(Tmpltpath, "templete_list.htm", "utf-8", rootMap, modPath + mod + "_list.htm");
		System.out.println("--ok");
	}

	public static void analysisTemplate(String pathname, String templateName, Map<?, ?> rootMap, String file2save) {
		analysisTemplate(pathname, templateName, "utf-8", rootMap, file2save);
	}

	/** @author attilax 老哇的爪子
	 * @since o79 W4359$
	 * 
	 * @param mod
	 * @param tits_a */
	public static void geneListData(String mod, final String[] tits_a) {

		final int sum = 7;
		Map<String, Object> rootMap = new HashMap<String, Object>() {
			{
				put("listO7", new ArrayList<Map>() {
					{
						for (int i = 0; i < sum; i++) {
							final int i2 = i;
							// add columns
							add(new HashMap<String, List>() {
								{
									put("tits", new ArrayList<Map>() {
										{
											for (int j = 0; j < tits_a.length; j++) {
												add(TitObj(j));
											}
										}

										private Map TitObj(int j) {
											// attilax 老哇的爪子 Xo51 o79
											Map titObj = new HashMap<String, Object>();

											final String tit = tits_a[j];
											String fld = ChineseToPinYin.getPingYin(tit);
											titObj.put("fld", fld);
											titObj.put("val", tit + String.valueOf(i2));

											return titObj;

										}
									});

								}
							});

						}
					}
				});
				put("sum", sum);

			}
		};
		listUtil.print(rootMap);
		modPath = rootPath + "\\" + mod + "\\";
		String dataFilePath = mod + "_list.json";
		analysisTemplate(Tmpltpath, "templete_list.json", rootMap, modPath + dataFilePath);

	}
	static String rootPath;
	/** @author attilax 老哇的爪子
	 * @since o79 W429$
	 * 
	 * @param tits_a */

	/** @param templateName 模板文件名称
	 * @param templateEncoding 模板文件的编码方式
	 * @param root 数据模型根对象 */
	public static void analysisTemplate(String pathname, String templateName, String templateEncoding, Map<?, ?> root, String file2save) {
		try {
			/** 创建Configuration对象 */
			Configuration config = new Configuration();
			/** 指定模板路径 */
			// = "templates";
			File file = new File(pathname);
			/** 设置要解析的模板所在的目录，并加载模板文件 */
			config.setDirectoryForTemplateLoading(file);
			/** 设置包装器，并将对象包装为数据模型 */
			config.setObjectWrapper(new DefaultObjectWrapper());

			/** 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致 */
			Template template = config.getTemplate(templateName, templateEncoding);
			/** 合并数据模型与模板 */
			filex.createAllPath(file2save);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file2save), "utf-8");
			// Writer out = new OutputStreamWriter(System.out);
			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}
}
