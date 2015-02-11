package com.attilax.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import com.attilax.io.filex;
import com.attilax.io.pathx;

//import mole.Config;

public class WebCfgRead {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws DocumentException,
			IOException {

     //    m.WebCfgRead.getValue("SubServere")
		// Attribute key= (Attribute) add.
	//	System.out.print("value:" + WebCfgRead.getValue("SubServer"));
	String webxml=	pathx.webAppPath()+"/WEB-INF/web.xml";
	String txt=filex.read(webxml);
	System.out.println(getValue("uppath", txt));
	System.out.println("--");

	}
	public static String getValue(String keyName ) 
	{	String webxml=	pathx.webAppPath()+"/WEB-INF/web.xml";
	String txt=filex.read(webxml);
		
		return getValue(keyName,txt);
	}
	static String getConfigXml() throws IOException {
		return null;
//		// if WEB-INF webapp else desktopApp
//		// webApp path=
//		//		String curpath = (System.getProperty("user.dir")
//		//				+ java.io.File.separator + "src" + File.separator);
//		//		curpath += "mole.config.xml";
//		String curpath = "/mole.config.xml";
//		URL url = Thread.currentThread().getContextClassLoader()
//				.getResource("");
//		String url2 ="";// m.path.parentDir(url.getPath());
//		String springCfgPath = url2 + File.separator + "web.xml";
//		//	InputStream resourceAsStream = new Config().getClass().getResourceAsStream(curpath);
//
//		//String fileContent = mole.io.FileService.read(curpath);
//		String fileContent = mole.io.FileService.read(springCfgPath);
//		//		resourceAsStream=null;
//		//		System.gc();
//		return fileContent;

	}
	@SuppressWarnings("all")
	public static String getValue(String keyName,String xmlStr)  {

        try{
//		String xmlStr = "  <config> "
//				+ "<item key=\"aa_key\" value=\"aa_value\" /> "
//				+ "<item key=\"bb_key\" value=\"bb_value\" />" + "</config>";
//		xmlStr = getConfigXml();
		//  <config> <item key="aa_key" value="aa_value" /> <item key="bb_key" value="bb_value" /></config>
		org.dom4j.Document document = DocumentHelper.parseText(xmlStr);
		Node root = document.getRootElement();
		//	root.selectNodes(arg0, arg1)
		String defaultNamespace = document.getRootElement().getNamespaceURI();
		//// defaultNamespace= http://java.sun.com/xml/ns/javaee  ,,, m_48_51 o7n  老哇的爪子  Attilax
		 HashMap nsMap = new HashMap();
		nsMap.put("ns", defaultNamespace);
		XPath xsub = document.createXPath("ns:context-param");
		xsub.setNamespaceURIs(nsMap);//加入命名空间

		List list = xsub.selectNodes(root);

		String returnValue =null;
		for (int i = 0; i < list.size(); i++) {
			Node item = (Node) list.get(i);
			XPath x2 = document.createXPath("ns:param-name");
			x2.setNamespaceURIs(nsMap);//加入命名空间
			Node paraName = x2.selectSingleNode(item);

			String key = paraName.getStringValue();

			XPath x3 = document.createXPath("ns:param-value");
			x3.setNamespaceURIs(nsMap);//加入命名空间
			String value = x3.selectSingleNode(item).getStringValue();
			if (key.equals(keyName)) {
				returnValue = value;
				break;
			}
		}
		return returnValue;
        }catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

	}

}
