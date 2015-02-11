/**
 * @author attilax 老哇的爪子
	@since  o9s h_1_3$
 */
package com.attilax.up;
import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.net.websitex;
import com.attilax.zip.ZipX;
 import static  com.attilax.core.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.io.*;
import netscape.javascript.JSObject;
/**
 * @author  attilax 老哇的爪子
 *@since  o9s h_1_3$
 */
public class ftpApp2 extends Applet {

	private Object apppath;
	/**
	 * Constructor of the applet.
	 *
	 * @exception HeadlessException if GraphicsEnvironment.isHeadless()
	 * returns true.
	 */
	public ftpApp2() throws HeadlessException {
		super();
	}
	//  attilax 老哇的爪子 h_1_3   o9s   

	/**
	 * Called by the browser or applet viewer to inform
	 * this applet that it is being reclaimed and that it should destroy
	 * any resources that it has allocated. The <code>stop</code> method
	 * will always be called before <code>destroy</code>. <p>
	 *
	 * A subclass of <code>Applet</code> should override this method if
	 * it has any operation that it wants to perform before it is
	 * destroyed. For example, an applet with threads would use the
	 * <code>init</code> method to create the threads and the
	 * <code>destroy</code> method to kill them. <p>
	 */
	public void destroy() {
		// Put your code here
	}

	/**
	 * Returns information about this applet. An applet should override
	 * this method to return a <code>String</code> containing information
	 * about the author, version, and copyright of the applet. <p>
	 *
	 * @return  a string containing information about the author, version, and
	 * copyright of the applet.
	 */
	public String getAppletInfo() {
		return "This is my default applet created by Eclipse";
	}

	/**
	 * Called by the browser or applet viewer to inform
	 * this applet that it has been loaded into the system. It is always
	 * called before the first time that the <code>start</code> method is
	 * called. <p>
	 *
	 * A subclass of <code>Applet</code> should override this method if
	 * it has initialization to perform. For example, an applet with
	 * threads would use the <code>init</code> method to create the
	 * threads and the <code>destroy</code> method to kill them. <p>
	 */
	public void init() {
		// Put your code here
	}

	/**
	 * Called by the browser or applet viewer to inform
	 * this applet that it should start its execution. It is called after
	 * the <code>init</code> method and each time the applet is revisited
	 * in a Web page. <p>
	 *
	 * A subclass of <code>Applet</code> should override this method if
	 * it has any operation that it wants to perform each time the Web
	 * page containing it is visited. For example, an applet with
	 * animation might want to use the <code>start</code> method to
	 * resume animation, and the <code>stop</code> method to suspend the
	 * animation. <p>
	 */
	public void start() {
		// Put your code here
		System.out.println("---=================attilax start down11====================aaa");
		String apppath=getApppath();
		System.out.println("--getApppath:"+apppath);
		String downurl=apppath+"/upx_upx.zip";
		String 	outputDirectory="c:";
		String savepath="";
		try {
			  savepath = "c:\\upx_upx.zip";
			if(!new File(savepath).exists())
				websitex.down(downurl,savepath);
		} catch (IOException e1) {
			//  attilax 老哇的爪子 1_50_52   o9t   
			e1.printStackTrace();
			showErr(getTrace(e1));
			return;
		}
		System.out.println("---================= upzip ====================aaa");
		try {
			if(!new File(outputDirectory+"\\upx_upx").exists())
			ZipX.extract(savepath, outputDirectory, "gbk");
		} catch (Exception e1) {
			//  attilax 老哇的爪子 1_54_46   o9t   
			e1.printStackTrace();
			showErr(getTrace(e1));
			return ;
		}
		
		System.out.println("---================= start boot ftp ====================aaa");
		String ftp = "c:\\upx_upx\\FlashFXP_4.3.1.1969_ati\\flashfxp.exe";
		if (!new File(ftp).exists()) {
			ftp = "d:\\upx_upx\\FlashFXP_4.3.1.1969_ati\\flashfxp.exe";
			if (!new File(ftp).exists()) ftp = "e:\\upx_upx\\FlashFXP_4.3.1.1969_ati\\flashfxp.exe";
		}
		try {
			System.out.println("---exe:"+ftp);
			
			Runtime.getRuntime().exec(ftp);
			showInvokeOK(ftp);
		} catch (IOException e) {
			// attilax 老哇的爪子 h_3_53 o9s
			showInvokeOK(getTrace(e));
			writeFile("c:\\appE" + getUUid() + ".txt", getTrace(e), "utf-8");
			try {
				e.printStackTrace();
				this.gx.drawString(getTrace(e), 5, 35);
				System.out.println("---=====================================aaa");
			} catch (Exception e2) {
				e.printStackTrace();
			}

		}
	}
	
	 /**
	@author attilax 老哇的爪子
		@since  o9t 1_z_c   
	
	 * @return
	 */
	private String getApppath() {
		// attilax 老哇的爪子  1_z_c   o9t 
		 try{ 
		        JSObject window=JSObject.getWindow(this);     // 获取JavaScript窗口句柄，引用当前文档窗口 
		        // 调用JavaScript的alert()方法 
		     //   window.eval("alert(/"This alert comes from Java!/")"); 
		 
		        // 调用页面上的js方法 show(message) 
		        Object obj[] = new Object[1]; 		        obj[0] =".."; 
		      this.apppath=  window.call("apppath", obj);//参数用数组的形势表示。 
		      return (String) this.apppath;
		 
		    }  
		    catch(Exception e){ 
		        System.out.println("Exception :" + e.toString()); 
		        e.printStackTrace();
		        showErr(getTrace(e));
		    }  
		return null;
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o9s l_p_7   
	
	 * @param ftp
	 */
	private void showInvokeOK(String ftp) {
		// attilax 老哇的爪子  l_p_7   o9s 
		 try{ 
		        JSObject window=JSObject.getWindow(this);     // 获取JavaScript窗口句柄，引用当前文档窗口 
		        // 调用JavaScript的alert()方法 
		     //   window.eval("alert(/"This alert comes from Java!/")"); 
		 
		        // 调用页面上的js方法 show(message) 
		        Object obj[] = new Object[1]; 		        obj[0] =ftp; 
		        window.call("downok", obj);//参数用数组的形势表示。 
		 
		    }  
		    catch(Exception e){ 
		        System.out.println("Exception :" + e.toString()); 
		        e.printStackTrace();
		    }  
	}
	
	private void showErr(String msg) {
		// attilax 老哇的爪子  l_p_7   o9s 
		 try{ 
		        JSObject window=JSObject.getWindow(this);     // 获取JavaScript窗口句柄，引用当前文档窗口 
		        // 调用JavaScript的alert()方法 
		     //   window.eval("alert(/"This alert comes from Java!/")"); 
		 
		        // 调用页面上的js方法 show(message) 
		        Object obj[] = new Object[1]; 		        obj[0] =msg; 
		        window.call("showErr", obj);//参数用数组的形势表示。 
		 
		    }  
		    catch(Exception e){ 
		        System.out.println("Exception :" + e.toString()); 
		        e.printStackTrace();
		    }  
	}
	public static String getTrace(Throwable t) {
	        StringWriter stringWriter= new StringWriter();
	        PrintWriter writer= new PrintWriter(stringWriter);
	        t.printStackTrace(writer);
	        StringBuffer buffer= stringWriter.getBuffer();
	        return buffer.toString();
	    }
	 
	 public static String getUUid( ) {
		 try {
			 java.util.Date   dt   =   new   java.util.Date(System.currentTimeMillis());  
		     SimpleDateFormat   fmt   =   new   SimpleDateFormat("MMdd_HHmmss_SSS");  
		     String   fileName=   fmt.format(dt);  
		     return fileName;
		} catch (Exception e) {
			return "excpt";
		}
		
	 }
	 public static void writeFile(String fileName, String fileContent,String encode)   
		{     
		    try   
		    {      
		        File f = new File(fileName);      
		        if (!f.exists())   
		        {       
		            f.createNewFile();      
		        }      
		        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),encode);      
		        BufferedWriter writer=new BufferedWriter(write);          
		        writer.write(fileContent);      
		        writer.close();     
		    } catch (Exception e)   
		    {      
		        e.printStackTrace();     
		        System.out.println("");
		    }  
		}
	
	public Graphics gx;
	 public void paint(Graphics g )

	 {

	  //向外输出的字符串
this.gx=g;
	  g.drawString("...!",5,35);

	 }

	/**
	 * Called by the browser or applet viewer to inform
	 * this applet that it should stop its execution. It is called when
	 * the Web page that contains this applet has been replaced by
	 * another page, and also just before the applet is to be destroyed. <p>
	 *
	 * A subclass of <code>Applet</code> should override this method if
	 * it has any operation that it wants to perform each time the Web
	 * page containing it is no longer visible. For example, an applet
	 * with animation might want to use the <code>start</code> method to
	 * resume animation, and the <code>stop</code> method to suspend the
	 * animation. <p>
	 */
	public void stop() {
		// Put your code here
	}
}

//  attilax 老哇的爪子