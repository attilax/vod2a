/**
 * 
 */
package com.attlax.swing.ui;

import java.awt.Image;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * @author ASIMO
 *
 */
public class SizeEff extends Effec {
	
	ClickTipsStar ctsWin;
	
	public void start() {
		time_span = 1000 / fps;
		int fps_count = fps * time_millsec / 1000;

		float h_cur=win.getHeight();
		float w_cur=win.getWidth();
		 final float w_span_perFps = (float) (win.getWidth() - 0) / (float) fps_count;
		 final float h_span_perFps = (float) (win.getHeight() - 0) / (float) fps_count;

		// timer_move.s
		timer_move = new Timer();
		timer_move.schedule(new TimerTask() {

			@Override
			public void run() {
				 float w_now = win.getWidth() - w_span_perFps;
				 float h_now = win.getHeight() - h_span_perFps;
			 	 win.setSize((int)w_now,(int) h_now);
			 	if(w_now<5)
			 		timer_move.cancel();
				System.out.println(w_now);
				Image image = win.getIconImages().get(0);
				ImageIcon img=UiX. getImageIcon(new ImageIcon(image), (int)w_now   ,(int)h_now);
				ctsWin=(ClickTipsStar) win;
				JLabel lab=	(JLabel) ctsWin.getContentPane().getComponent(0);
				lab.setIcon(img);
			//	ctsWin.setImg(img);

			}
		}, delay, time_span);

	}

}
