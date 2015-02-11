/**
 * 
 */
package com.attlax.swing.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JWindow;

/**
 * @author ASIMO
 * 
 */
public class UiX {

	/**
	 * @author attilax 老哇的爪子
	 * @since obh a_44_58
	 */
	public static void main(String[] args) {
		// setBackgrd("c:\\bg.jpg");

	}

	/**
	 * @author attilax 老哇的爪子
	 * @since obh a_45_p
	 */
	public static void setBackgrd(JFrame frm, String imgFile) {
		ImageIcon img = new ImageIcon(imgFile);// 背景图片
		JLabel lblPicture = new JLabel(img);
		lblPicture.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// lblPicture.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		JLayeredPane layeredPane = frm.getLayeredPane();
		// layeredPane.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		layeredPane.add(lblPicture, new Integer(Integer.MIN_VALUE));
		layeredPane.setBackground(Color.green);
		layeredPane.setOpaque(false);
		// this.setOpacity(0.7f);
		// frm.setOpacity(0.7f);
		JPanel contentPane = (JPanel) frm.getContentPane();
		((JPanel) contentPane).setOpaque(false);// jeig must yeu in customed bg
		// contentPane.setBackground(Color.blue);

	}

	public static void setBackgrd(Container frm, String imgFile) {
		ImageIcon img = new ImageIcon(imgFile);// 背景图片
		JLabel lblPicture = new JLabel(img);
		lblPicture.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// lblPicture.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		// JLayeredPane layeredPane = frm.getLayeredPane();
		// layeredPane.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		// layeredPane.add(lblPicture, new Integer(Integer.MIN_VALUE));
		// layeredPane.setBackground(Color.green);
		// layeredPane.setOpaque(false);
		// this.setOpacity(0.7f);
		// frm.setOpacity(0.7f);
		// JPanel contentPane = (JPanel) frm.getContentPane();
		// ((JPanel) contentPane).setOpaque(false);//jeig must yeu in customed
		// bg
		// contentPane.setBackground(Color.blue);

	}

	/**
	 * @author attilax 老哇的爪子
	 * @since obh a_48_i
	 */
	public static void setTaskbarIcon(JFrame frm, String name) {
		BufferedImage image;
		try {
			image = ImageIO.read(new FileInputStream(name));
			frm.setIconImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setAllOpaque(JFrame frm, String name) {
		// JLabel lblPicture = new JLabel(img);
		// );
		JLayeredPane layeredPane = frm.getLayeredPane();
		// layeredPane.setBorder(BorderFactory.createLineBorder(Color.blue, 3));

		layeredPane.setOpaque(false);
		// this.setOpacity(0.7f);
		// frm.setOpacity(0.7f);
		JPanel contentPane = (JPanel) frm.getContentPane();
		((JPanel) contentPane).setOpaque(false);// jeig must yeu in customed bg
		// contentPane.setBackground(Color.blue);
		// frm.setOpacity(opacity)
		JRootPane p = frm.getRootPane();
		// p.setBackground(Color.red);
		p.setOpaque(false);
	}

	/**
	 * @author attilax 老哇的爪子
	 * @since obh b_45_55
	 */
	public static void setAllOpaque(JWindow frm, String name) {
		// JLabel lblPicture = new JLabel(img);
		// );
		JLayeredPane layeredPane = frm.getLayeredPane();
		// layeredPane.setBorder(BorderFactory.createLineBorder(Color.blue, 3));

		layeredPane.setOpaque(false);
		// this.setOpacity(0.7f);
		// frm.setOpacity(0.7f);
		JPanel contentPane = (JPanel) frm.getContentPane();
		((JPanel) contentPane).setOpaque(false);// jeig must yeu in customed bg
		// contentPane.setBackground(Color.blue);
		// frm.setOpacity(opacity)
		JRootPane p = frm.getRootPane();
		// p.setBackground(Color.red);
		p.setOpaque(false);
	}

	/**
	 * @author attilax 老哇的爪子
	 * @since obh c_1_1
	 */
	public static ImageIcon setImg(JWindow win, String imgFile) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new FileInputStream(imgFile));
			// frm.setIconImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon img_ori = new ImageIcon(imgFile);// 背景图片
		ImageIcon img=getImageIcon(imgFile, img_ori.getIconWidth() /1 , img_ori.getIconHeight()/1);
		
		JLabel lblPicture = new JLabel();
		lblPicture.setIcon(img);
	//	lblPicture.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		// lblPicture.setBounds(0, 0, img.getIconWidth()/2,
		// img.getIconHeight()/2);
		win.add(lblPicture);
	//	win.setIconImage(image);//taskbar img
		win.setSize(img.getIconWidth()  , img.getIconHeight() );
		 
		return img;
	}
	
	
	public static ImageIcon setImg(JWindow win, ImageIcon imgIcon) {
//		BufferedImage image = null;
//		try {
//			image = ImageIO.read(new FileInputStream(imgFile));
//			// frm.setIconImage(image);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ImageIcon img_ori = new ImageIcon(imgFile);// 背景图片
 		ImageIcon img= imgIcon;
//		
		JLabel lblPicture = new JLabel();
		lblPicture.setIcon(  imgIcon);
	//	lblPicture.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		// lblPicture.setBounds(0, 0, img.getIconWidth()/2,
		// img.getIconHeight()/2);
		win.add(lblPicture);
	//	win.setIconImage(image);//taskbar img
		win.setBounds(100, 500, img.getIconWidth()  , img.getIconHeight() );
		return img;
	}
	
//	public static void getImg(JWindow win )
//	{
//		win.get
//	}
	
	 public static ImageIcon getImageIcon(  ImageIcon icon, int width, int height) {
	 
		 // ImageIcon icon = new ImageIcon(path);
		  icon.setImage(icon.getImage().getScaledInstance(width, height,
		    Image.SCALE_DEFAULT));
		  return icon;
		 }
	 public static ImageIcon getImageIcon(String path, int width, int height) {
		  if (width == 0 || height == 0) {
		   return new ImageIcon(path);
		  }
		//  Image
		  ImageIcon icon = new ImageIcon(path);
		  icon.setImage(icon.getImage().getScaledInstance(width, height,
		    Image.SCALE_DEFAULT));
		  return icon;
		 }

}
