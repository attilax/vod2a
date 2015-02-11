package com.attilax.floatWin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JWindow;

import com.attilax.win.PopMenuX;

public class FloatWin extends JWindow  {

	JWindow  frame ;//=this;
	JWindow  jFrame = frame;

	public FloatWin() {
	//	Component  
		frame=this;
		jFrame=this;
		JLabel label = new JLabel(new ImageIcon("D:/pictures/blue.jpg"));
		label.setPreferredSize(new Dimension(3000, 10));
		frame.add(label, BorderLayout.CENTER);

		frame.setBounds(500, 500, 100, 100);
	 //	frame.setUndecorated(true); // no titlbar  ,jeig defin in  java.awt.Frame
		frame.setOpacity(0.7f); 
		// Frame.setUndecorated(false) 不显示标题栏
		// AWTUtilites.setWindowOpacity(Window,boolean) 透明(非标准的类)
		// frame.pack();
		
	//	frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setVisible(true);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		
		
		JPopupMenu pm  = new JPopupMenu();
		JMenuItem exitMenu = new JMenuItem("&Exit"){{
			
			addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					System.exit(0);
				}
			});
		}};		
	 
		pm.addSeparator();
		pm.add(exitMenu);
	    PopMenuX.	addPopup(frame, pm);
	    
	    setDragable();
	}

	public static void main(String[] args) {

		new FloatWin();
	}

	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;

	//press move release
	private void setDragable() {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
			//	jFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
			public void mouseReleased(java.awt.event.MouseEvent e) {
				isDragged = false;
			//	jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isDragged) {
				//	jFrame.().x
					int x = jFrame.getBounds().x;
					int y = jFrame.getBounds().y;
					loc = new Point(x + e.getX() - tmp.x,
							y + e.getY() - tmp.y);
					jFrame.setLocation(loc);
				}
			}
		});
	}

}
