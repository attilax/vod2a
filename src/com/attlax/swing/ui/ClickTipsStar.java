/**
 * 
 */
package com.attlax.swing.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JWindow;

import com.attilax.Closure;
import com.attilax.io.pathx;
import com.attlax.swing.JFrameAti;

/**
 * @author ASIMO
 * 
 */
public class ClickTipsStar extends JWindow {

	/**
	 * @throws HeadlessException
	 */
	ImageIcon img;
	public ClickTipsStar() throws HeadlessException {
		// can show outter boder

		// this.setUndecorated(true);

		ini();
		 
		

		// me.setEvent(new Closure() {

	}
	private void ini() {
		String name = pathx.classPath(ClickTipsStar.class) + "star.png";
		img=	UiX.setImg(this, name);
	//	this.get
		this.setIconImage(img.getImage());
			UiX.setAllOpaque(this, name);
			this.setBackground(new Color(0, 0, 0, 0));
			
		//---blow is recomm	
		// System.out.println(name);

		// Shape shape = new Ellipse2D.Double(0, 0,400, 400);
		// this.setShape(shape);
		// UiX.setTaskbarIcon(this, name);

		// UiX.setBackgrd(this, name);

		// this.setOpacity(opacity)
		// JRootPane p= this.getRootPane();
		// p.setBackground(Color.red);
		// p.setOpaque(false);
		// JPanel gls= (JPanel) this.getGlassPane();
		// gls.setOpaque(false);
		//
		// this.setOpacity(0.5f);
		// this.setBackground(Color.CYAN);

		// this.setAlwaysOnTop(false);
		// this.setOpacity(0.5f);
		// contentPane

		//--------end recomm
		 
			JWindow thisobj = (JWindow) this;
			int time_millsec = 400;
			
			MoveEff me = new MoveEff();	
			me.setTime_millsec(time_millsec);
			me.setObj(this);
			me.start();
			//---
			SizeEff se = new SizeEff(); 
			se.setTime_millsec(time_millsec);
			se.setObj(this);
			se.start();
	}
	/**
	 * @param mousePosition
	 */
	public ClickTipsStar(Point mousePosition) {
		System.out.println(" xy:"+ this.getX());
		// MouseInfo.getPointerInfo().getLocation() relt screen 
		//this.getMousePosition()  is relt win
		this.mousePosition=mousePosition;
		this.setLocation((int)mousePosition.getX(),(int)mousePosition.getY()-120 );
		ini();
		
		System.out.println(" xy:"+ this.getX());
	 System.out.println("");
	}
	public 	Point mousePosition;
	public   ImageIcon getImg(  )
	{
		return img;
	}
	public   void setImg( ImageIcon img2 )
	{
		  img=img2;
	}
	/**
	 * @param title
	 * @throws HeadlessException
	 */

	// public class JFrameAti extends javax.swing.JFrame
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ClickTipsStar().setVisible(true);
			}
		});
		System.out.println(Integer.MAX_VALUE);
	}

}
