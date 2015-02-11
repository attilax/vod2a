/**
 * 
 */
package com.attlax.swing.ui;

import java.util.Date;
import java.util.Timer;

import javax.swing.JWindow;

/**
 * @author ASIMO
 *
 */
public class Effec {
	int fps=30;
	int time_millsec;
	protected int delay=100;
	JWindow win;
	public int getFps() {
		return fps;
	}
	/**
	 * @author attilax 老哇的爪子
	 * @since obh g_3_54
	 */
	public void setObj(JWindow win2) {
		this.win = win2;

	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public int getTime_millsec() {
		return time_millsec;
	}

	public void setTime_millsec(int time_millsec) {
		this.time_millsec = time_millsec;
	}

	int time_span;
	Timer timer_move;
}
