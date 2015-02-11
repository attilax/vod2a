/**
 * 
 */
package com.attilax.dwr;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.attilax.Closure2;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.designpatter.commandPkg.Command;
import com.attilax.designpatter.commandPkg.Invoker;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.persistence.PX;
import com.attilax.review.IReview;
import com.attilax.review.ReviewHistry;
import com.attilax.review.ReviewX;
import com.attilax.review.TargetObj;
import com.attilax.text.strUtil;
import com.focusx.DwrCInier;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.downtask.GvDownloadTaskSvs;
import com.focusx.elmt.GvMaterial;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.programme.action.ProgrammeAction;
import com.focusx.programme.entity.GvProgramme;

/**
 * @author ASIMO
 * 
 */
@RemoteProxy(name = "dwrC")
public class DwrC {

	/**
	 * @author attilax 老哇的爪子
	 * @throws IOException 
	 * @since ob2 h_s_0
	 */
	public static void main(String[] args) throws IOException {
	System.out.println(URLEncoder.encode("../programme/programmeManager!showEdit4rvw?progarmmeId=29", "utf-8"));	
	   PageContext pageContext = null;
	GvProgramme pub=(GvProgramme)pageContext.getAttribute("programme");
	JspWriter out = null;
		out.write(  ReviewX.stateFmt(pub.getRevi().getState()));
	}

	@SuppressWarnings("all")
	@RemoteMethod
	public Object execOb(final Map<String, String> m) {
		try {
			Class class1;
			DwrCInier dcir=IocX.getBean(DwrCInier.class);
			dcir.execute(m);
			
			
			Command.reg("queryMtrlReviewStat", new Closure2() {

				@Override
				public Object execute(Object arg0) {
					 String id_s=m.get("id");
					 ReviewX rx = IocX.getBean(ReviewX.class);
					 return (rx.reviewPassedCheck(Integer.parseInt(id_s), "mtrl"));
				//	 return s;
				}
			});
			
			Command.reg("queryPrgrmReviewStat", new Closure2() {

				@Override
				public Object execute(Object arg0) {
					 String id_s=m.get("id");
					 ReviewX rx = IocX.getBean(ReviewX.class);
					 return (rx.reviewPassedCheck(Integer.parseInt(id_s), "prgrm"));
				//	 return s;
				}
			});
			
			
			
			Command.reg("gettxt", new Closure2() {

				@Override
				public Object execute(Object arg0) {
					 String urlpart=m.get("url");
					 String f=pathx.webAppPath()+"\\"+urlpart;
					 String s=filex.read(f);
					 return s;
				}
			});
			Command.reg("saveReviewRzt", new Closure2() {

				@Override
				public Object execute(Object arg0) {
					ReviewX rx = IocX.getBean(ReviewX.class);
					return rx.save(m);
				}
			});
			Command.reg("findRevwList", new Closure2() {

				@Override
				public Object execute(Object arg0) {
				
					try {
						m.put("targetid", m.get("targetid").toString().trim());
						ReviewX rx = IocX.getBean(ReviewX.class);
						String catex = m.get("targettype").trim();
						if (catex.equals("prgrm")) {
							IReview<GvProgramme> pa=IocX.getBean(ProgrammeAction.class);
							rx.targetSvs=pa;
						}
						final List<ReviewHistry> li = (List<ReviewHistry>) rx.findRevwList(m);
						
						for (ReviewHistry hst : li) {
							
							if (catex.equals("mtrl")) {
								GvMaterialSvs mtc = IocX.getBean(GvMaterialSvs.class);
								PX px = IocX.getBean(PX.class);
								GvMaterial mt = (GvMaterial) px.findById(
										GvMaterial.class,
										Integer.parseInt(m.get("targetid")));
								TargetObj to = new TargetObj();
								to.setId(mt.getMaterialId());
								to.setName(mt.getMaterialDescription());
								hst.setTar(to);
							}
//							if (catex.equals("prgrm")) {
//								
//							}
						}
						return new HashMap(){{
							
						//	Map mp=new HashMap();
							 put("total" ,li.size());
							 put("rows",li );
						}};
					} catch (Exception e) {
						filex.saveLog(e, "c:\\e");
					     return core.toJsonStrO88(e);
					}
					
				}
			});

			Invoker ivk = new Invoker(new Command(m.get("meth")));
			return ivk.action();
		} catch (Exception e) {
			filex.saveLog(e, "c:\\e");
			return "-1";
		}
	 
	

	}
	
	@RemoteMethod  @Deprecated
	public Object execOb2(String param) {
		return param;
		
//		Command.reg("saveReviewRzt", new Closure2() {
//
//			@Override
//			public Object execute(Object arg0) {
//				ReviewX rx = IocX.getBean(ReviewX.class);
//				return rx.save(m);
//			}
//		});
//	 	Invoker ivk = new Invoker(new Command(m.get("meth")));
//		return ivk.action();

	}

	// findbyx

}
