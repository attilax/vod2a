package com.focusx.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ecs.xhtml.object;

/** 
 * com.focusx.util
 * Utils.java 
 * author:vincente  2013-10-11 
 */
public class Utils {
	public static String toUnicodeStr(String srcStr){
		        //StringBuilder支持可变字符串，是非线程安全类，速度较StringBuffer快
		        StringBuilder resultStr=new StringBuilder("");
		        for(int i=0;i<srcStr.length();i++){
		            char c=srcStr.charAt(i);
		            String hexCodeStr=Integer.toHexString((int)c);
		            int len=hexCodeStr.length();
		            resultStr.append("\\u");
		            //如果字符的unicode编码不足4位，前面补0
		            if(len<4){
		                switch(4-len){
		                case 1:resultStr.append("0");break;
		                case 2:resultStr.append("00");break;
		                case 3:resultStr.append("000");break;
		                case 4:resultStr.append("0000");break;
		                }
		            }
		            resultStr.append(hexCodeStr);
		        }
		        return resultStr.toString();
		    }
	
	
	/**
	 * 字符串转码
	 * @param a_str  字符串值
	 * @param a_orig: 如 "ISO-8859-1"
	 * @param a_dest: 如 "UTF-8"
	 * @return 转码后的值
	 * @throws UnsupportedEncodingException
	 * @author 罗军
	 */
	public static String convertEncode(String a_str, String a_orig,
		String a_dest) throws UnsupportedEncodingException {
		byte[] temp = a_str.getBytes(a_orig);
		return new String(temp, a_dest);
	}
	
	/**
	 * 
	  * Description:转换器 obj转timestamp 
	  *  
	  * CreateTime: 2014-7-28 下午3:33:06
	  *
	  * @param obj
	  * @return
	  * @throws UnsupportedEncodingException
	 */
	public static Timestamp convertObjectToTimestamp(String obj) {
		if(obj!=null && !"null".equals(obj) && !obj.equals("")){
			return Timestamp.valueOf(obj.toString());
		}else{
			return null;
		} 
	}
	
	
	/**
	 * 
	  * Description:转换器String转Integer 
	  *  
	  * CreateTime: 2014-7-28 下午3:33:06
	  *
	  * @param obj
	  * @return
	  * @throws UnsupportedEncodingException
	 */
	public static Integer convertStringToInteger(String obj) {
		if(obj!=null && !"null".equals(obj) && !obj.equals("")){
			return Integer.valueOf(obj.toString());
		}else{
			return null;
		} 
	}	/**
	 * 
	  * Description:转换器String转Integer 
	  *  
	  * CreateTime: 2014-7-28 下午3:33:06
	  *
	  * @param obj
	  * @return
	  * @throws UnsupportedEncodingException
	 */
	public static Double convertStringToDouble(String obj) {
		if(obj!=null && !"null".equals(obj) && !obj.equals("")){
			return Double.valueOf(obj.toString());
		}else{
			return null;
		} 
	}

	/**
	 * 将字符串以char分割, 如: 字符串 "1,2,3,4,5" 被字符','分割成含字符串"1","2","3","4","5"的String数组
	 * 
	 * @param a_str
	 *        被分隔的字符串
	 * @param a_ch
	 *        分隔符
	 * @return 分割后的字符串数组
	 * @author 罗军
	 */
	public static String[] split(String a_str, char a_ch) {
		char[] splitter = { a_ch };
		return split(a_str, new String(splitter));
	}

	/**
	 * 将字符串以String分割, 如: 字符串 "1,2,3,4,5" 被字符串","分割成含字符串"1","2","3","4","5"的String数组
	 * 
	 * @param a_str
	 *        被分隔的字符串
	 * @param a_ch
	 *        分隔字符串
	 * @return 分割后的字符串数组
	 * @author 罗军
	 */
	public static String[] split(String a_str, String a_ch) {
		// 如果传入字符串为空时，则只返回一个空的字符串数组
		if (a_str == null) {
			return new String[0];
		}
		Vector ret = new Vector();
		int pos = 0;
		while (a_str.length() > 0) {
			String tmp = null;
			pos = a_str.indexOf(a_ch);
			if (pos != -1) { // still has splitter
				tmp = a_str.substring(0, pos);
				a_str = a_str.substring(pos + a_ch.length());
			} else {
				tmp = a_str;
				a_str = "";
			}
			ret.add(tmp);
		}

		// 将Vector中数据导入数组
		int count = ret.size();
		String[] results = new String[count];
		for (int i = 0; i < count; i++) {
			results[i] = (String) ret.elementAt(i);
		}
		return results;
	}

	/**
	 * 将字符串拆分为ArrayList格式.
	 * 
	 * @param strRoleIdList  要拆分的内容
	 * @param strDelim 分隔符
	 * @return 拆分为ArrayList格式后的内容
	 * @author 罗军
	 */
	public static ArrayList toArrayList(String strSrc, String strDelim) {
		ArrayList alList = new ArrayList();
		if (strSrc == null || strSrc.trim().length() == 0) {
			return alList;
		}

		StringTokenizer token = new StringTokenizer(strSrc, strDelim);
		while (token.hasMoreElements()) {
			alList.add(token.nextElement());
		}

		return alList;
	}

	/**
	 * 合并两个List，重复元素只保存一份.
	 * 
	 * @param list1
	 *        要合并的第一个List
	 * @param list2
	 *        要合并的第二个List
	 * @return 合并后的list
	 * @author 罗军
	 */
	public static List combineTwoLists(List list1, List list2) {
		for (int i = 0, n = list1.size(); i < n; i++) {
			if (!list2.contains(list1.get(i))) {
				list2.add(list1.get(i));
			}
		}

		return list2;
	}

	/**
	 * 计算字符串是否为空, 空是指: null 或 空串 或 全是空格的字符串
	 * @param strs 一个或多个字符串
	 * @return 如果是空（null或""）,返回true;否则返回false
	 * @author 罗军
	 */
	public static boolean isEmpty(String... strs) {
		if (strs != null) {
			for (String a_value : strs) {
				if (a_value == null || a_value.trim().equals("")
					|| "null".equals(a_value) || "undefined".equals(a_value)) {
					return true;
				} 
			}
			return false;
		}
		return true;
	}

	/**
	 * 计算字符串是否为空, 空是指: null 或 等于0
	 * 
	 * @param a_double
	 * @return 如果是空（null或0）,返回true;否则返回false
	 * @author 张健雄
	 */
	public static boolean isEmpty(Double a_double) {
		if (a_double == null || a_double == 0.0) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * 判断是否字符串不为空 isEmpty方法的取非
	 * @param strs 一个或多个字符串
	 * @return 如果有任何一个为空（null或""）,返回false;否则返回true 
	*/ 
	public static boolean isNotEmpty(String... strs) {
		return !isEmpty(strs);
	}

	public static boolean isEmpty(Object[] objs) {
		if (objs == null || objs.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object[] objs) {
		return !isEmpty(objs);
	}

	/**
	 * description:判断集合对象是否为空或没有内容
	 * @param collection
	 * @return 为空返回true，不为空返回false
	 * @author 罗军
	 */
	public static boolean isEmpty(Collection collection) {
		boolean isEmpty = false;
		if (collection == null || collection.size() == 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * description:判断集合对象是否为空或没有内容
	 * 
	 * @param collection
	 * @return 为空返回false，不为空返回true
	 * @author 罗军
	 */
	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

	/**
	 * 判断Map对象是否为空或没有内容
	 * 
	 * @param map
	 * @return 为空返回true，不为空返回false
	 * @author 罗军
	 */
	public static boolean isEmpty(Map map) {
		boolean isEmpty = false;
		if (map == null || map.size() == 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * 判断Map对象是否为空或没有内容
	 * 
	 * @param map
	 * @return 为空返回true，不为空返回false
	 * @author 罗军
	 */
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	/**
	 * 获得一个类的名字，不含包名的
	 * 
	 * @param a_class
	 * @return 类名
	 * @author 罗军
	 */
	public static String getAClassName(Class a_class) {
		if (a_class.getPackage() == null) {
			return a_class.getName();
		} else {
			String packageName = a_class.getPackage().getName();
			String className = a_class.getName();
			return className.substring(packageName.length() + 1);
		}
	}

	/**
	 * 储存SESSION
	 * 
	 * @param FieldName
	 *        SESSOIN名
	 * @param Value
	 *        SESSION值
	 * @param request
	 *        HttpServletRequest
	 * @author 罗军
	 */
	public static void setSession(String FieldName, Object Value,
		HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			session.setAttribute(FieldName, Value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 得到SEESION值
	 * 
	 * @param FieldName
	 *        SESSOIN名
	 * @param DefaultValue
	 *        默认值
	 * @param request
	 *        HttpServletRequest
	 * @return session中的值
	 * @author 罗军
	 */
	public static Object getSession(String FieldName, Object DefaultValue,
		HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Object value = session.getAttribute(FieldName);
			if (value == null) {
				value = DefaultValue;
			}
			return value;
		} catch (Exception ex) {
			ex.printStackTrace();
			return DefaultValue;
		}

	}

	/**
	 * 去除将字符的头或尾的空“”格
	 * 
	 * @param Value
	 * @return
	 * @author 罗军
	 */
	public static String trimString(String Value) {
		return Value == null ? "" : Value.trim();
	}

	/**
	 * 处理SQL条件中的单引号问题，单引号转换为双引号
	 * 
	 * @param value
	 *        需要转化的字符串
	 * @return 转化后的字符串
	 * @author 罗军
	 */
	public static String HandleQuotes(String value) {
		return value.replace('\'', '\"');
	}

	/**
	 * 将String类型转换为int类型
	 * 
	 * @param value
	 *        要转换的字符串
	 * @return int类型，当字符串为空时返回0
	 * @author 罗军
	 */
	public static int stringToInt(String value) {
		int _result = 0;
		if (value != null && !value.equals("")) {
			_result = Integer.parseInt(value);
		}
		return _result;
	}

	/**
	 * 将String类型转换为int类型
	 * 
	 * @param value
	 *        要转换的字符串
	 * @return int类型，当字符串为空时返回0
	 * @author 罗军
	 */
	public static double stringToDouble(String value) {
		double _result = 0;
		if (value != null && !value.equals("")) {
			_result = Double.parseDouble(value);
		}
		return _result;
	}

	/**
	 * 检查 string b是否包含string a
	 * 
	 * @param String类型
	 * @param String类型
	 * @return boolean
	 * @author 罗军
	 */
	public static boolean isInclude(String str, String model) {
		boolean revalue = false;
		int len = str.length();

		String[] FSTRING2 = { model };
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < FSTRING2.length; j++) {
				if (str.indexOf(FSTRING2[j]) < 0) {
					revalue = false;
				} else {
					revalue = true;
					break;
				}
			}
		}
		return revalue;
	}

	/**
	 * 处理Memo类型字段在HTML的显示，空格转换为&nbsp;，回车转换为<br>
	 * 
	 * @param value
	 *        Memo类型字段的值
	 * @return
	 * @author 罗军
	 */
	public static String HandleMemo(String value) {
		if (value == null) {
			return null;
		}
		value = value.replace(" ", "&nbsp;");//
		value = value.replace("\r\n", "<br>");

		return value;
	}

	/**
	 * 替换匹配的第一个字符串
	 * 
	 * @param Value
	 *        源字符串
	 * @param matchString
	 *        匹配的字符串
	 * @param replaceString
	 *        要替换的字符串
	 * @return
	 * @author 罗军
	 */
	public static String replaceFirst(String Value, String matchString,
		String replaceString) {
		int pos = Value.indexOf(matchString);
		int len = matchString.length();
		if (pos < 0) {
			return Value;
		}
		String firstString = Value.substring(0, pos);
		String endString = Value.substring(pos + len);
		return firstString + replaceString + endString;

	}

	/**
	 * 得到一个字符串的真正长度，如果字符串包含汉字，则一个汉字当作两个字符
	 * 
	 * @param Value
	 *        字符串
	 * @return 字符串真正长度
	 * @author 罗军
	 */
	public static int getTrueSizeFromString(String Value) {
		if (Value == null) {
			return 0;
		}
		int intLen = Value.getBytes().length;
		return intLen;

	}

	/**
	 * 将一对象转换为字符串
	 * 
	 * @param obj
	 *        对象
	 * @return 字符串
	 * @author 罗军
	 */
	public static String getString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();

	}
	
	/**
	 * 将一对象转换为整数类别
	 * @param obj 对象
	 * @return 整数型
	 * @author 张健雄
	 */
	public static Integer getInteger(Object obj) {
		if (obj == null) {
			return 0;
		}
		String objString = obj.toString().trim();
		return Integer.valueOf(objString);

	}
	
	/**
	 * 将一对象转换为长整数型
	 * @param obj 对象
	 * @return 长整数型
	 * @author 张健雄
	 */
	public static Long getLong(Object obj) {
		if (obj == null) {
			return 0L;
		}
		String objString = obj.toString().trim();
		return Long.valueOf(objString);

	}
	
	/**
	 * 将一对象转换为双精度类型
	 * @param obj 对象
	 * @param accuracy 小数位
	 * @return 双精度类型
	 * @author 张健雄
	 */
	public static Double getDouble(Object obj, int accuracy) {
		if (obj == null) {
			return 0.0;
		}
		String objString = obj.toString().trim();
		return getDouble(objString,accuracy);

	}

	/**
	 * 将字符串转换为double
	 * 
	 * @param value
	 *        数字类型字符串
	 * @return Double 默认2位小数
	 * @author 徐明
	 */
	public static Double getDouble(String value) {
		return getDouble(value, 2);
	}

	/**
	 * 将字符串转换为double
	 * 
	 * @param value 数字类型字符串
	 * @param accuracy 小数位
	 * @return Double
	 * @author 徐明
	 */
	public static Double getDouble(String value, int accuracy) {
		String val = "0";
		if (Utils.isNotEmpty(value)) {
			val = value;
		}
		StringBuffer format = new StringBuffer("0.");
		for (int i = 0; i < accuracy; i++) {
			format.append("0");
		}
		java.text.DecimalFormat myformat = new java.text.DecimalFormat(format
			.toString());
		val = myformat.format(Double.parseDouble(val));
		return Double.parseDouble(val);
	}
 

	/**
	 * 将数据中的数据用'1','2','3'……的格式来格式化
	 * 
	 * @param ids
	 *        要拆分的数据
	 * @return 拆分的结果,如果输入的参数为null或者内容为空，则返回""
	 */
	public static String ListPKidToString(List<Long> ids) {
		if (ids != null && ids.size() > 0) {
			StringBuffer sf = new StringBuffer("'" + ids.get(0) + "'");
			for (int i = 1; i < ids.size(); i++) {
				sf.append(",'" + ids.get(i) + "'");
			}
			return sf.toString();
		}
		return "";
	}

	/**
	 * 将字符串中的集合拼成一个查询范围集 <br/>
	 * 
	 * @param list
	 * @return
	 */
	public static String ListStringToString(List<String> list) {
		if (list != null && list.size() > 0) {
			StringBuffer sf = new StringBuffer("'" + list.get(0) + "'");
			for (int i = 1; i < list.size(); i++) {
				sf.append(",'" + list.get(i) + "'");
			}
			return sf.toString();
		}
		return "";
	}

	/**
	 * 自动生成业务编号(业务编号标识不能超过50位，如果超过则只取前50个字符)
	 * 
	 * @param header
	 *        业务标识,业务标识长度不能超过13位。如果超过则只取前13位
	 * @return 自动生成业务NO.（业务标识_UUID）
	 * @author 张健雄
	 * @author 罗军 修改 2010-10-21 09:47
	 * @Date: 2009-05-31
	 */

	public static String createAutoNo(String header) {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		String rs = s.substring(0, 8) + s.substring(9, 13)
			+ s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
		if (!Utils.isEmpty(header)) {
			if (header.length() > 13) {
				header = header.substring(0, 13);
			}
			rs = header + "_" + rs;
		}
		return rs;
	}

	/**
	 * 字符处理,将指定的字符串以“,”分开，用“',”来重新结合起来；
	 * 
	 * @param actDefRoleNo
	 * @return
	 * @author 罗军
	 */
	public static String splitStringForIds(String oldStr) {
		return splitStringForIds(oldStr, ",");
	}

	/**
	 * 字符处理,将指定的字符串以splitChar分开，用“',”来重新结合起来；
	 * 
	 * @param actDefRoleNo
	 * @return
	 * @author 罗军
	 */
	public static String splitStringForIds(String oldStr, String splitChar) {
		if (Utils.isEmpty(oldStr)) {
			return "''";
		}
		StringBuffer sf = new StringBuffer();
		String[] strs = oldStr.split(splitChar);
		if (strs.length == 0) {
			sf.append("''");
		} else {
			sf.append("'" + strs[0] + "'");
			for (int i = 1; i < strs.length; i++) {
				sf.append(",'" + strs[i] + "'");
			}
		}
		return sf.toString();
	}

	/**
	 * 数组信息处理,将指定的数组分开，用“',”来重新结合起来；
	 * 
	 * @param array
	 * @return
	 * @author 罗军
	 */
	public static String splitStringForArray(String[] array) {
		StringBuffer sf = new StringBuffer();
		if (array == null) {
			// 如果数组为空，则只返回""
		} else if (array.length == 0) {
			sf.append("''");
		} else {
			sf.append("'" + array[0] + "'");
			for (int i = 1; i < array.length; i++) {
				sf.append(",'" + array[i] + "'");
			}
		}
		return sf.toString();
	}

	/**
	 * 数组信息处理,将指定的数组分开，用“',”来重新结合起来；
	 * 
	 * @param array
	 * @return
	 * @author 罗军
	 */
	public static String splitStringForList(List<String> array) {
		StringBuffer sf = new StringBuffer();
		if (array == null) {
			sf.append("''");
		} else if (array.size() == 0) {
			sf.append("''");
		} else {
			sf.append("'" + array.get(0) + "'");
			for (int i = 1; i < array.size(); i++) {
				sf.append(",'" + array.get(i) + "'");
			}
		}
		return sf.toString();
	}

	/**
	 * 数组信息处理,将指定的数组用splitS来重新结合起来；
	 * 
	 * @param array
	 *        拼接的字符集合
	 * @param splitS
	 *        分割的字符
	 * @return
	 * @author 徐明
	 */
	public static String splitStringForList(List<String> array, String splitS) {
		StringBuffer sf = new StringBuffer();
		if (array == null) {
			// 如果数组为空，则只返回""
		} else if (array.size() == 0) {
			sf.append("");
		} else {
			for (int i = 0; i < array.size(); i++) {
				sf.append("" + array.get(i) + "");
				if (i != array.size() - 1) {
					sf.append(splitS);
				}
			}
		}
		return sf.toString();
	}

	/**
	 * 判断列表对象是否为空或列表对象中没有对象信息（如为空则返回空值）
	 * 
	 * @param list
	 *        需要判断的列表对象
	 * @return 返回判断信息
	 * @author David - 张健雄
	 * @date 2009-06-19
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEmptyList(List list) {
		boolean isEmpty = false;
		if (list == null || list.size() == 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static boolean isNotEmptyList(List list) {
		return !isEmptyList(list);
	}

	/**
	 * 两个双精度数值的除法运算方法
	 * 
	 * @param dividend
	 *        被除数
	 * @param divisor
	 *        除数
	 * @param median
	 *        保留小数点后几位小数(精度)
	 * @return 返回除法运算后的结果双精度数
	 * @author David - 张健雄
	 */
	public static Double divide(Double dividend, Double divisor, int median) {
		if (dividend == null || dividend == 0.0 || divisor == null
			|| divisor == 0.0) {
			return 0.0;
		}
		BigDecimal dividendObj = new BigDecimal(dividend);// 时间差的毫秒数
		BigDecimal divisorObj = new BigDecimal(divisor);// 一个时间的毫秒数
		double number = dividendObj.divide(divisorObj, median,
			BigDecimal.ROUND_HALF_UP).doubleValue();
		return number;
	}
 

	/**
	 * 将一个字符串进行格式化,得到我们正常编码规则的字符串,第一个字母小写.主要用于变量 主要规划为:第一个字母小写，其后所有以“_”为单个单词每个单词的首写字母大写
	 * 
	 * @param str
	 *        旧的字符串
	 * @return 得到旧字符串转换为变量的新的字符串。
	 * @author 罗军
	 */
	public static String getnewStringByoldString(String str) {
		if (str != null && !str.equals("")) {
			String[] strs = str.split("_");
			StringBuffer newStr = new StringBuffer();
			newStr.append(strs[0].toLowerCase());
			for (int i = 1; i < strs.length; i++) {
				String st = strs[i];
				newStr.append(st.toUpperCase().charAt(0)
					+ st.substring(1, st.length()));
			}
			return newStr.toString();
		}
		return null;
	}

	/**
	 * 将一个字符串进行格式化,得到我们正常编码规则的字符串,第一个字母大写.主要用于对象；
	 * 规则是:第一个字母大小，以后每遇到_的另一个单词的首字母大写;其他字母不变（如果需要处理在外部可以将入参全部转小写）
	 * 
	 * @param str
	 *        需要转化的字符串
	 * @return 处理后的字符串
	 * @author 罗军
	 */
	public static String getStringToMethod(String str) {
		if (str != null && !str.equals("")) {
			String[] strs = str.split("_");
			StringBuffer newStr = new StringBuffer();
			for (int i = 0; i < strs.length; i++) {
				// String st = strs[i].toLowerCase();
				newStr.append(strs[i].toUpperCase().charAt(0));
				newStr.append(strs[i].substring(1, strs[i].length()));
			}
			return newStr.toString();
		}
		return null;
	}

	/**
	 * 判断传入参数是否为整数字符串
	 * 
	 * @param num
	 *        传入字符串
	 * @return 如传入参数为整数字符串，则返回TRUE
	 * @author 张健雄
	 * @date 2009-10-27
	 */
	public static boolean isNumeric(String num) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(num);
		if (Utils.isEmpty(num) || !isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断此字符是否为中文字符，包括是否为中文状态下输入的标点 GENERAL_PUNCTUATION 判断中文的“号 CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
	 * HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
	 * 
	 * @param c
	 *        传入需要判断的字符
	 * @return 如字符为中文字符，则返回ture，否则返回false
	 * @author 张健雄
	 * @date 2009-11-06
	 */
	public static boolean isChinese(char c) {
		// 将字符转换成UNICODE码
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
			|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
			|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
			|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
			|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
			|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断此字符串是否包含有中文字符，包括是否包含中文状态下输入的标点
	 * 
	 * @param string
	 *        传入需要判断的字符串
	 * @return 如字符包含有中文字符，则返回ture，否则返回false
	 * @author 张健雄
	 * @date 2009-11-06
	 */
	public static boolean isContainsChinese(String string) {
		char[] ch = string.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				// 如果字符串中含有中文字符，则直接返回true
				return isChinese(c);
			} else {
				// 如果字符不为中文字符，则继续检测
				continue;
			}
		}
		return false;
	}

	/**
	 * 判断指定的字符串数组中是否有指定的字符串内容；
	 * 
	 * @param args
	 *        字符串数组
	 * @param str
	 *        字符串内容
	 * @return 如果存在返回true;否则返回false
	 * @author 罗军 2010-02-11
	 */
	public static boolean isContants(String[] args, String str) {
		if (Utils.isEmpty(args) || Utils.isEmpty(str)) {
			return false;
		}
		for (int i = 0; i < args.length; i++) {
			if (Utils.isEmpty(args[i])) {
				continue;
			}
			if (args[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 过滤特殊字符(清除掉所有特殊字符),特殊字符主要包括： `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？
	 * 
	 * @param str
	 *        需要过滤的字符串,如果字符串为空则直接返回str
	 * @return 过滤后的字符串
	 * @throws PatternSyntaxException
	 */
	public static String stringFilter(String str) throws PatternSyntaxException {
		// 清除掉所有特殊字符
		if (Utils.isEmpty(str)) {// 先要做是否为空的判断   2010-01-23 11:00
			return str;
		}
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 取有效值
	 * 
	 * @author 罗军
	 * @date 2008.08.27
	 */
	public static String getStrValue(String strSource) {
		String strR = strSource;
		if (strR == null)
			strR = "";
		return strR;
	}

	/***********************************************************************************************
	 * 格式化HQL或SQL中的特殊字符，如：单引号(')
	 * 
	 * @return 格式化后的字符串，如果为null值，则返回空串“”
	 * @param fldName
	 * @author 罗军
	 * @date 2010-03-20
	 */
	public static String getFmtStr(String strFld) {
		if (strFld == null)
			return "";
		return strFld.replace("'", "''");
	}

	/**
	 * ip校验
	 * 
	 * @param s 字符
	 * @author 罗军
	 * @return 
	 */
	public static Boolean isIpAddress(String s) {
		String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * 获取客户端真实ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientAddress(HttpServletRequest request) {
		String address = request.getHeader("X-Forwarded-For");
		if (address != null && isIpAddress(address)) {
			return address;
		}
		return request.getRemoteAddr();
	}
		}

