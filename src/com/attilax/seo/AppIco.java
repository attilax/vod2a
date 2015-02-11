package com.attilax.seo;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AppIco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    /** 
     * 设置窗口图标 
     */  
	public static void setWindowIcon(JFrame frm)  
    {  
  
          
        ImageIcon imageIcon = new ImageIcon( AppIco.class.getResource(  
                "/com/attilax/ico/ati.png"));  
  
        // 设置标题栏的图标为face.gif  
        frm.setIconImage(imageIcon.getImage());  
  
    }  
}
