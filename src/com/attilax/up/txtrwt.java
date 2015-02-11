package com.attilax.up;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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
import com.attilax.Stream.Mapx;
import com.attilax.clr.garLib;
import com.attilax.coll.ListX;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.net.websitex;
import com.attilax.util.WebCfgRead;

@Controller
public class txtrwt {
	
	public static void main(String[] args) {
		   ProcessInfo pri = new ProcessInfo();  
	       pri.setItemNum( 2);
	       pri.readSize = 3;
	      
	       core.log(   core.toJsonStr(pri));
	}
	Logger log = Logger.getLogger(txtrwt.class);
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
	@RequestMapping(value = "/upload.htmlo8j", method = RequestMethod.POST)
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
		    	      upFileSavePath = WebCfgRead.getValue("uppath")+"\\"+filex.getUUidName()+"."+ext;
		    	   
		    	      String	pathname=pathx.webAppPath() + "\\" + upFileSavePath;
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
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/txtrwt", produces = "text/plain;charset=gb2312")
	// @ResponseBody
	public Object process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		core.log("=========process.json");
		String url = "" + request.getParameter("url");// +"?"+request.getQueryString();
		String url_no_host=request.getParameter("urlNoHost");
		// System.out.println(); request.getParameter("tid")
		System.out.println(request.getQueryString());
		if (url.endsWith("jpg") || url.endsWith("jpeg") || url.endsWith("gif")) {
			
			if(url_no_host.startsWith("/http://"))
			{
				String url3=url_no_host.replaceAll("/http://", "http://");
				  byte[] ba = websitex.WebpageContent_Bin(url3);
				  if( !isLjJpg(ba) )
					  outputImg(response, url);
			}
				
			else
				outputImg(response, url);
		}
		//
		else
			outputTxt(response, url,request);
		return null;
	}

	static int n9=0;
	
	@RequestMapping(value = "/imgrwt_abspath")
	// @ResponseBody
	public Object process_imgrwt_abspath(HttpServletRequest request,
			HttpServletResponse response) {
		n9++;
		core.log("===o8o3 now n is:"+String.valueOf(n9));
		core.log("=========process.json");
		String url = "" + request.getParameter("url");// +"?"+request.getQueryString();
		String url_no_host = request.getParameter("urlNoHost");
		// System.out.println(); request.getParameter("tid")
		System.out.println(request.getQueryString());
core.log("--o8o1 get pic start");
		byte[] ba = websitex.WebpageContent_Bin(url);
		core.log("--o8o1 get pic end");
		if (!isLjJpg(ba))
			outputImg(response, url);
		if(isLjJpg(ba))
			outputImg_garfile_holder(response );
		core.log("--o8o1 output pic end");
		return null;
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 8:48:02 PM$
	
	 * @param response
	 */
	private void outputImg_garfile_holder(HttpServletResponse response) {
		// attilax 老哇的爪子 8:48:02 PM Aug 23, 2014

		{
			String url = pathx.classPath() + "/com/attilax/clr/def.jpg";
			byte[] ba = filex.readImageData(url);
			outputImg(response, url, ba);
		}

	}
//public static 	garLib garlb=garLib.$();
	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 7:35:28 PM$
	
	 * @param ba
	 * @return
	 */
	private boolean isLjJpg(byte[] ba) {
		// attilax 老哇的爪子  7:35:28 PM   Aug 23, 2014 
		
		{ 
			garLib garlb=garLib.$();
		return garlb.isGarfile(ba);
		 } 
		
		
	}


	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 9:16:11 AM$
	
	 * @param response
	 * @param url
	 */
	private void outputJpg(HttpServletResponse response, String url) {
		// attilax 老哇的爪子  9:16:11 AM   Aug 23, 2014 
		 try {
		  byte[] ba = websitex.WebpageContent_Bin(url);
		  response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/jpeg");
	        ServletOutputStream responseOutputStream = response.getOutputStream();
	       
				responseOutputStream.write(ba);
			
	        responseOutputStream.flush();
	        responseOutputStream.close();
	        } catch (IOException e) {
				//  attilax 老哇的爪子 4:04:37 PM   Aug 23, 2014   
	        	core.log(e);
				throw new RuntimeException(e);
			}
		
	}
	
	private void outputImg(HttpServletResponse response, String url) {
		// attilax 老哇的爪子  9:16:11 AM   Aug 23, 2014 
		
		 String imgExtname="jpg";
		 if(url.endsWith("gif"))
			 imgExtname="gif";
		 if(url.endsWith("jpeg"))
			 imgExtname="jpeg";
		 try {
		  byte[] ba = websitex.WebpageContent_Bin(url);
		  response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/"+imgExtname);
	        ServletOutputStream responseOutputStream = response.getOutputStream();
	       
				responseOutputStream.write(ba);
			
	        responseOutputStream.flush();
	        responseOutputStream.close();
	        } catch (IOException e) {
				//  attilax 老哇的爪子 4:04:37 PM   Aug 23, 2014   
	        	core.log(e);
				throw new RuntimeException(e);
			}
		
	}

	
	private void outputImg(HttpServletResponse response,String url,   byte[] ba) {
		// attilax 老哇的爪子  9:16:11 AM   Aug 23, 2014 
		
		 String imgExtname="jpg";
		 if(url.endsWith("gif"))
			 imgExtname="gif";
		 if(url.endsWith("jpeg"))
			 imgExtname="jpeg";
		 try {
		  //byte[] ba = websitex.WebpageContent_Bin(url);
		  response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/"+imgExtname);
	        ServletOutputStream responseOutputStream = response.getOutputStream();
	       
				responseOutputStream.write(ba);
			
	        responseOutputStream.flush();
	        responseOutputStream.close();
	        } catch (IOException e) {
				//  attilax 老哇的爪子 4:04:37 PM   Aug 23, 2014   
	        	core.log(e);
				throw new RuntimeException(e);
			}
		
	}

	private void outputTxt(HttpServletResponse response, String url, HttpServletRequest request)
			throws UnsupportedEncodingException, IOException {

	 Map<String, String>  headProps =  Mapx
						.<String, String> $()
						.add("Cookie",
								"lastfid=0; lastvisit=9271%091408838229%09%2Fread.php%3Ftid%3D1715718%26fpage%3D2; ol_offset=32204; ipstate=1408836660; __utma=99888095.1396678057.1405101634.1408806230.1408837132.29; __utmz=99888095.1405101634.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmc=99888095; pin6=done; __utmb=99888095.3.10.1408837132")
						.toMap() ;

 
		String txt=websitex.WebpageContentO8f  (url,"gbk",headProps);
		
		txt=txt.replaceAll("http://", pathx.webAppPath_webfmt(request)+ "/http://");
		filex.save(txt, "c:\\p5t.txt");
		
		  byte[] ba = txt.getBytes("gbk");
	     // jpegOutputStream = new ByteArrayOutputStream();
//	        try {
//	            String sessionid = request.getSession().getId();
//	            BufferedImage challenge = imageCaptchaService.getImageChallengeForID(sessionid, request.getLocale());
//	            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
//	            jpegEncoder.encode(challenge);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        ba = jpegOutputStream.toByteArray();
	        response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("text/html");
	        ServletOutputStream responseOutputStream = response.getOutputStream();
	        responseOutputStream.write(ba);
	        responseOutputStream.flush();
	        responseOutputStream.close();
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
