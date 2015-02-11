package com.focusx.dictionary.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.rails.mapImp;
import com.attilax.spri.SpringUtil;
import com.focusx.dictionary.dao.impl.GvDictionaryDaoImpl;
import com.focusx.dictionary.entity.GvDictionary;
import com.focusx.dictionary.service.IGvDictionaryServcie;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
import com.focusx.push.PrgrmNoticer;
import com.focusx.push.TaskNoticer;
import com.focusx.util.MyCacher;
import com.focusx.util.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 *     
 * @author：luojun
 * 
 * @Program Name：starx BS坐席
 *
 * @type_name:DictionaryAction    
 *
 * @Description:   
 *
 * @CreateTime：2014-7-14 下午02:52:46  
 * @version     
 *
 */ @Controller  
public class GvDictionaryAction extends ActionSupport {
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @since Ver 1.1    
	 */    
	
	private static final long serialVersionUID = 1L;
	
	protected static Logger logger = Logger.getLogger(GvDictionaryAction.class);

	protected MyCacher myCacher = MyCacher.getInstance();
	
	//数据字典服务对象
	private IGvDictionaryServcie dictionaryServcie;
	//分页对象
	private Pager<GvDictionary> pager; 
	//返回数据对象
	private boolean status;
	//返回对象
	private Object objectJson;
	//返回数据列表
	private List<GvDictionary> dataList;
	//数据字典对象
	private GvDictionary gvDictionary;
	
    //查询条件属性
	private Integer id ;
	private String DCode;
	private String DName;
	private String DPcode;
	private String DIsvalid;
	private Integer DLevel;
	private String DRemar;
	private String code;
	
	private String deleteList;
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:获取数据列表(分页)
	  *  
	  * @CreateTime: 2014-7-14 下午03:03:52
	  *
	  * @return
	 */
	public String queryDictionaryList(){ 
		extractData(); 
		return "list";
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:获取数据列表(分页)
	  *  
	  * @CreateTime: 2014-7-14 下午03:03:52
	  *
	  * @return
	 */
	public String selectDictionaryList(){ 
		extractData(); 
		return "select";
	}


	/**
	 *  
	  * Description:提取数据
	  *  
	  * CreateTime: 2014-8-18 上午10:03:28
	  *
	 */
	private void extractData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionMap = new HashMap<String, Object>();  
		if(Utils.isNotEmpty(DCode)){
			conditionMap.put("DCode", DCode.trim());
		}
		if(Utils.isNotEmpty(DName)){
			conditionMap.put("DName", DName.trim());
		}
		if(Utils.isNotEmpty(DPcode)){
			conditionMap.put("DPcode", DPcode.trim());
		} 
		if(Utils.isNotEmpty(DIsvalid)){
			conditionMap.put("DIsvalid", DIsvalid);
		} 
		if(DLevel!=null){
			conditionMap.put("DLevel", DLevel);
		}
		if(Utils.isNotEmpty(DRemar)){
			conditionMap.put("DRemar", DRemar.trim());
		} 
		
		
		pager = new Pager<GvDictionary>();
		PageUtil.init(pager, request);
		List<GvDictionary> data = dictionaryServcie.getDictionaryList(pager, conditionMap);
		pager.setData(data);
	}
	
	
	/**
	 * [
    {
    "DCode": "1",
    "DIsvalid": "",
    "DLevel": 2,
    "DName": "视频",
    "DPcode": "WJLX",
    "DRemar": "",
    "createTime":     {
      "date": 31,
      "day": 4,
      "hours": 20,
      "minutes": 52,
      "month": 6,
      "nanos": 637000000,
      "seconds": 23,
      "time": 1406865143637,
      "timezoneOffset": 420,
      "year": 114
    },
    "id": 32
  },
	@author attilax 老哇的爪子
		@since  2014-8-7 下午06:42:03$
	
	 * @param args
	 */
	public static void main(String[] args) {
		PrgrmNoticer.run=0;
		TaskNoticer.run=0;
		
		String cate = "WJLX";
		
		
		String jsonStrO88 = ajaxDictionaryList(cate);
		System.out.println(jsonStrO88);
	}
	
	/**
	 *   o88 spr mvc  produces avoid lwemer
	@author attilax 老哇的爪子
		@since  o87 i_v_c$
	/spr/ajaxDictionaryList?cate=WJLX
	 * @param cate
	 * @return
	 */
	@RequestMapping(value = "/ajaxDictionaryList" ,produces="text/plain;charset=utf-8")
	@ResponseBody 
	private static String ajaxDictionaryList(String cate) {
		GvDictionaryAction a=(GvDictionaryAction) SpringUtil.getBean(GvDictionaryAction.class);
		a.code=cate;
		a.ajaxDictionaryList();
		final Map m=new mapImp<String,String>();
	Object li=	CollectionUtils.each_safe(a.dataList, new Closure() {
			
			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子  i_i_50   o87 
				GvDictionary d=(GvDictionary) arg0;
			m.put(d.getDCode(), d.getDName());
		 
				return m;
				
			}
		});
		
		String jsonStrO88 = core.toJsonStrO88(m);
		jsonStrO88=jsonStrO88.replaceAll("\r\n", "");
		jsonStrO88=jsonStrO88.replaceAll("\n", "");
		jsonStrO88=jsonStrO88.replaceAll("\r", "");
		return jsonStrO88;
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:数据字典根据父类编码查询之类数据
	  *  
	  * @CreateTime: 2014-7-16 上午10:09:25
	  *
	  * @return
	 */
	public String ajaxDictionaryList(){ 
		logger.info("数据字典根据编码查询 code:"+code); 
		dataList =	dictionaryServcie.getDictionaryList(code);
		return Action.SUCCESS;
	}
	

	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:数据字典根据父类编码查询之类数据
	  *  
	  * @CreateTime: 2014-7-16 上午10:09:25
	  *
	  * @return
	 */
	public String ajaxDictionary(){ 
		logger.info("数据字典根据编码查询 DCode:"+DCode);  
		List<GvDictionary> lis = getSetCacher();
		if(Utils.isNotEmpty(lis)){
					for (GvDictionary gvDictionary : lis) {
						if(DCode.equals(gvDictionary.getDCode())){
					objectJson = gvDictionary; 
							break;
						}
					}
				}
		return Action.SUCCESS;
	}
	

 
	/**
	 * 验证数据字典是否唯一
	  * Description:
	  *  
	  * CreateTime: 2014-8-18 上午10:11:57
	  *
	  * @return
	 */
	public String ajaxIsExistDictionary(){ 
		logger.info("验证数据字典是否唯一 DCode:"+DCode +"  DPcode:"+DPcode);  
		status = false;
		GvDictionary gvDictionary = dictionaryServcie.getDictionaryByCodeAndPcode(DCode, DPcode);
		if(gvDictionary!=null){ 
			status = true;
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:添加数据字典对象
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String add(){  
		status = dictionaryServcie.insert(gvDictionary);
		//更新缓存
		String pcode = gvDictionary.getDPcode();
		if(!"0".equals(pcode)){
			updateCacher(gvDictionary.getDPcode()); 
		}
		return queryDictionaryList();
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:添加数据字典
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String edit(){ 
		status = dictionaryServcie.update(gvDictionary);
		//更新缓存
		String pcode = gvDictionary.getDPcode();
		if(!"0".equals(pcode)){
			updateCacher(gvDictionary.getDPcode()); 
		}
		return queryDictionaryList();
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:添加数据字典
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String delete(){  
		GvDictionary gv = dictionaryServcie.getDictionary(id);
		status = dictionaryServcie.delete(id);
		//更新缓存
		String pcode = gv.getDPcode();
		if(!"0".equals(pcode)){
			updateCacher(pcode); 
		}
		return queryDictionaryList();
	}
	
	/**
	 * 批量删除
	  * Description:
	  *  
	  * CreateTime: 2014-8-18 上午10:08:41
	  *
	  * @return
	 */
	public String batchdelete(){
		String[] strs = deleteList.split(","); 
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i].trim();
			if(!"no".equals(str)){
				ids.add(new Integer(strs[i].trim()));
			}
		} 
		if(Utils.isNotEmpty(ids)){
			//更新缓存
			for (Integer id : ids) {
				GvDictionary gv = dictionaryServcie.getDictionary(id);
				String pcode = gv.getDPcode();
				if(!"0".equals(pcode)){
					updateCacher(pcode); 
				}
			}
		}
		status = dictionaryServcie.batchdelete(ids);
		return queryDictionaryList(); 
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:打开添加页面
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String showAdd(){ 
		if(id!=null){
			gvDictionary = dictionaryServcie.getDictionary(id);	
			gvDictionary.setDLevel(gvDictionary.getDLevel() + 1);
		}else{
			gvDictionary = new GvDictionary();
			gvDictionary.setDLevel(1);
			gvDictionary.setDCode("0");
		}
		
		return "add";
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:打开编辑页面
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String showEdit(){ 
		gvDictionary = dictionaryServcie.getDictionary(id);
		return "edit";
	}
	
	
	/**
	 * 
	  * Description:获取或者写入缓存
	  *  
	  * CreateTime: 2014-8-8 下午4:00:38
	  *
	  * @param dictionaryServcie
	  * @return
	 */
	private boolean updateCacher(String key) {
		List<GvDictionary> list = null;
	    //获取缓存对象 没有改对象的缓存返回null 
	    	 logger.info("更新缓存数据字典,父级编码为"+DPcode);
	    	 list = dictionaryServcie.getDictionaryList(key); 
	    	 boolean msg =	 myCacher.putCache(key, list, Integer.MAX_VALUE-10);
	    	 logger.info("缓存更新："+msg);
		return msg;
	}
	
	/**
	 * 
	  * Description:获取或者写入缓存
	  *  
	  * CreateTime: 2014-8-8 下午4:00:38
	  *
	  * @param dictionaryServcie
	  * @return
	 */
	private List<GvDictionary> getSetCacher() {
		List<GvDictionary> list = null;
	    //获取缓存对象 没有改对象的缓存返回null 
	    Object obj = myCacher.getCache(DPcode);
	    if(obj==null){
	    	 logger.info("初始化缓存数据字典,父级编码为"+DPcode);
	    	 list = dictionaryServcie.getDictionaryList(DPcode); 
	    	 if(Utils.isNotEmpty(list)){
		    	 myCacher.putCache(DPcode, list, Integer.MAX_VALUE-10);
	    	 }
	    }else{
	    	logger.info("取缓存数据字典,父级编码为"+DPcode);
	    	list = (List<GvDictionary>)obj;
	    }
		return list;
	}
	
	public IGvDictionaryServcie getDictionaryServcie() {
		return dictionaryServcie;
	}
	public void setDictionaryServcie(IGvDictionaryServcie dictionaryServcie) {
		this.dictionaryServcie = dictionaryServcie;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Pager<GvDictionary> getPager() {
		return pager;
	}
	public void setPager(Pager<GvDictionary> pager) {
		this.pager = pager;
	}

	public GvDictionary getGvDictionary() {
		return gvDictionary;
	}

	public void setGvDictionary(GvDictionary gvDictionary) {
		this.gvDictionary = gvDictionary;
	}

	public String getDCode() {
		return DCode;
	}

	public void setDCode(String dCode) {
		DCode = dCode;
	}

	public String getDName() {
		return DName;
	}

	public void setDName(String dName) {
		DName = dName;
	}

	public String getDPcode() {
		return DPcode;
	}

	public void setDPcode(String dPcode) {
		DPcode = dPcode;
	}

	public String getDIsvalid() {
		return DIsvalid;
	}

	public void setDIsvalid(String dIsvalid) {
		DIsvalid = dIsvalid;
	}

	public Integer getDLevel() {
		return DLevel;
	}

	public void setDLevel(Integer dLevel) {
		DLevel = dLevel;
	}

	public String getDRemar() {
		return DRemar;
	}

	public void setDRemar(String dRemar) {
		DRemar = dRemar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(String deleteList) {
		this.deleteList = deleteList;
	}

	public List<GvDictionary> getDataList() {
		return dataList;
	}

	public void setDataList(List<GvDictionary> dataList) {
		this.dataList = dataList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public Object getObjectJson() {
		return objectJson;
	}
	public void setObjectJson(Object objectJson) {
		this.objectJson = objectJson;
	}
 
	 
}
