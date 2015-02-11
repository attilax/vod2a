package com.focusx.dictionary.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.attilax.spri.SpringUtil;
import com.focusx.dictionary.entity.GvDictionary;
import com.focusx.dictionary.service.IGvDictionaryServcie;
import com.focusx.dictionary.service.impl.GvDictionaryServcieImpl;
import com.focusx.util.MyCacher;
import com.focusx.util.Utils;

public class SelectTag extends TagSupport {

	protected MyCacher myCacher = MyCacher.getInstance();
	
	protected static Logger logger = Logger
			.getLogger(SelectTag.class);
	
	//父类编码
	private String DPcode;
	
	//选中值
	private String value;   
	
	//标签名字
	private String tagName;
	
	//标签ID
	private String tagId;
	
	//事件字符串
	private String event;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
	    IGvDictionaryServcie dictionaryServcie = (IGvDictionaryServcie)SpringUtil.getBean("dictionaryServcie");   
	    
	    List<GvDictionary> list = getSetCacher(dictionaryServcie); 
	    
		if(Utils.isNotEmpty(tagId)){
			tagName = tagId;
		}
		StringBuffer sb = new StringBuffer() ;  
	    sb.append("<select name='"+tagName+"' id='"+tagId+"' " + event + " >");  
	    sb.append("<option value='' >-请选择-</option>");     
		for (GvDictionary gvDictionary : list) {
		        if(gvDictionary.getDCode().equals(value)){  
	                sb.append("<option value='"+gvDictionary.getDCode()+"' selected='selected'>");  
	            }else{  
	                sb.append("<option value='"+gvDictionary.getDCode()+"'>");  
	            }  
		        sb.append(gvDictionary.getDName()+"</option>"); 
		}
		sb.append("</select>");
		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
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
	private List<GvDictionary> getSetCacher(
			IGvDictionaryServcie dictionaryServcie) {
		List<GvDictionary> list = null;
	    //获取缓存对象 没有改对象的缓存返回null
	    Object obj = myCacher.getCache(DPcode);
	    if(obj==null){
	    	 logger.info("初始化缓存数据字典,父级编码为"+DPcode);
	    	 list = dictionaryServcie.getDictionaryList(DPcode); 
	    	 myCacher.putCache(DPcode, list, Integer.MAX_VALUE-10);
	    }else{
	    	logger.info("取缓存数据字典,父级编码为"+DPcode);
	    	list = (List<GvDictionary>)obj;
	    }
		return list;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	public String getDPcode() {
		return DPcode;
	}

	public void setDPcode(String dPcode) {
		DPcode = dPcode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
 
}
