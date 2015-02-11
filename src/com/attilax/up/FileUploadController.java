package com.attilax.up;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 

import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.util.WebCfgRead;

@Controller
public class FileUploadController {
	
	public static void main(String[] args) {
		   ProcessInfo pri = new ProcessInfo();  
	       pri.setItemNum( 2);
	       pri.readSize = 3;
	      
	       core.log(   core.toJsonStr(pri));
	}
	Logger log = Logger.getLogger(FileUploadController.class);
	static final ThreadLocal<Integer> threadLocal4listerInvkTimes=new ThreadLocal<Integer>();;
  	String upFileSavePath ="";
	private boolean upfinish=false;
	/**
	 * upload  上传文件
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public void upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final HttpSession hs = request.getSession();
		//ModelAndView mv = new ModelAndView();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			//return mv;
			return;
		}
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
	//	int listtenerInvkTimes=0;
		threadLocal4listerInvkTimes.set(0);
	
		//note must befer  parseREq....beris not run lister
		 
		   setProcessLister(hs, upload);  //o8f
		   
		   
		List items = upload.parseRequest(request);
		// Parse the request
		// Process the uploaded items
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();
		    if (item.isFormField()) {
		        String name = item.getFieldName();
		        String value = item.getString();
		        System.out.println("this is common feild!"+name+"="+value);
		    } else {
		    	System.out.println("this is file feild!");
		    	 String fieldName = item.getFieldName();
		    	    String fileName = item.getName();
		    	    String contentType = item.getContentType();
		    	    boolean isInMemory = item.isInMemory();
		    	    long sizeInBytes = item.getSize();
		    	//    String pathname = "c:\\000\\"+fileName;
		    	    String ext=filex.getExtName(fileName);
		    	      upFileSavePath = WebCfgRead.getValue("uppath")+"/"+filex.getUUidName()+"."+ext;
		    	   
		    	      String	pathname=pathx.webAppPath() + "/" + upFileSavePath;
		    	    filex.createAllPath(pathname);
		    	    filex.save_safe(pathname, "c:\\uplog.txt");
		    	    core.log("---save file:"+pathname);
					File uploadedFile = new File(pathname);
		    	    item.write(uploadedFile);
		    	   this.upfinish=true;
		    	    
		    }
		}
	//	return mv;
	}


	private void setProcessLister(final HttpSession hs, ServletFileUpload upload) {
		upload.setProgressListener(new ProgressListener(){
			   public void update(long pBytesRead, long pContentLength, int pItems) {
				   ProcessInfo pri = new ProcessInfo();  
			       pri.setItemNum( pItems);
			       pri.readSize = pBytesRead;
			       pri.totalSize = pContentLength;
			  //     pri.show = pBytesRead+"/"+pContentLength+" byte";
			       pri.rate = Math.round(new Float(pBytesRead) / new Float(pContentLength)*100);
			 
			   //ati o81    
			       pri.show =String.valueOf(  pri.rate)+"%";
			   //    if(!upFileSavePath.equals(""))
			       pri.path=upFileSavePath;
			       /////end ati
			       hs.setAttribute("proInfo", pri);
			       int newtimes=threadLocal4listerInvkTimes.get()+1;				       
			       core.log("---- listerInvkTimes times:"+String.valueOf(newtimes));
			       core.log(   core.toJsonStr(pri));
			       threadLocal4listerInvkTimes.set(newtimes);
			      
			   }
			});
	}
	
	
	/**
	 * process 获取进度
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/process.json", method = RequestMethod.GET)
	@ResponseBody
	public Object process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		core.log("=========process.json");
		ProcessInfo pi = ( ProcessInfo)request.getSession().getAttribute("proInfo");
		
		//o8f todox cant get correct path  fix
		if(pi.getRate()==100 )
			if(!upfinish)
			{
				pi.setRate(99);
				pi.setPath(this.upFileSavePath);
			}
			else
				pi.setPath(this.upFileSavePath);
		return pi;
	}
	
	
	/**
	@author attilax 老哇的爪子
		@since  o81 2_53_52$

	 */
	private void kaka() {
		// attilax 老哇的爪子  2_53_52   o81 
		{}
		{}
		{}

	}
	
}
