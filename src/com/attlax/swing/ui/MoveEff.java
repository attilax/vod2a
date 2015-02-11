/**
 * 
 */
package com.attlax.swing.ui;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JWindow;

import com.attilax.Closure;

/**
 * @author ASIMO
 * 
 */
public class MoveEff extends Effec {

	
	private float y_span_perFps;
 

	/**
	 * @author attilax 老哇的爪子
	 * @since obh g_8_0
	 */
	public void start() {
		time_span = 1000 / fps;
		int fps_count = fps * time_millsec / 1000;

		y_span_perFps = (float) (win.getY() - 0) / (float) fps_count;

		// timer_move.s
		timer_move = new Timer();
		timer_move.schedule(new TimerTask() {

			@Override
			public void run() {
				float y_cur = win.getY() - y_span_perFps;
			 	win.setLocation(win.getX(), (int) y_cur);
			 	if(y_cur<0-win.getHeight())
			 		timer_move.cancel();
			//	System.out.println(y_cur);

			}
		}, delay, time_span);

	}

}
