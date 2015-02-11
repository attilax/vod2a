package com.attilax.win;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.attilax.seo.AppIco;

public class SysTrayX {
	private static SystemTray st;
	private static PopupMenu pm;

	public static void main(String[] args) {

		if (SystemTray.isSupported()) {// 判断当前平台是否支持系统托盘
			st = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(
					AppIco.class.getResource("/com/attilax/ico/ati.png"));// 定义托盘图标的图片
			  createPopupMenu();
			TrayIcon ti = new TrayIcon(image, "Desktop Demo Tray", pm);
			try {
				st.add(ti);
			} catch (AWTException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void createPopupMenu() {

		pm = new PopupMenu();
		MenuItem openBrowser = new MenuItem("Open My Blog");
		openBrowser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// openBrowser("http://hi.baidu.com/riffling/blog");
			}
		});

		MenuItem sendMail = new MenuItem("Send Mail to me");
		sendMail.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// sendMail("mailto:chinajash@yahoo.com.cn");
			}
		});

		MenuItem edit = new MenuItem("Edit Text File");
		sendMail.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// edit();
			}
		});

		MenuItem exitMenu = new MenuItem("&Exit");
		exitMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		pm.add(openBrowser);
		pm.add(sendMail);
		pm.add(edit);
		pm.addSeparator();
		pm.add(exitMenu);
	}

}
