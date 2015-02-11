/**
 * @author attilax 老哇的爪子
	@since  o7n h_g_0$
 */
package com.focusx.util;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.anno.AopX;
import com.attilax.db.ix;
import com.attilax.spri.SpringUtil;
import com.attilax.util.DateUtil;
import com.focusx.dao.impl.EquipmentDAOImpl;
import com.focusx.playRec.baseDAO;
import com.focusx.pojo.Equipment;
import static com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/** @author attilax 老哇的爪子
 * @since o7n h_g_0$ */
@Component public class watchdog4vod {

	public static Logger logger = Logger.getLogger("watchdogLogger");
	/** @author attilax 老哇的爪子
	 * @since o7n h_g_0$
	 * 
	 * @param args */
	public static void main(String[] args) {
		// attilax 老哇的爪子 h_g_0 o7n
		// add1kTestDATA();
		// System.out.println(SpringUtil.getBean("watchdog4vod"));
		new watchdog4vod().start();
	}
	// attilax 老哇的爪子 h_g_0 o7n

	/** @author attilax 老哇的爪子
	 * @since o7n i_49_37$ */
	private static void add1kTestDATA() {
		// attilax 老哇的爪子 i_49_37 o7n
		new ix(1000).times(new org.apache.commons.collections.Closure() {

			@Override public void execute(Object arg0) {
				// attilax 老哇的爪子 i_50_0 o7n
				Equipment o = new Equipment();
				o.setMome("num:" + core.toInt(arg0));
				o.setDepartId(1);
				new baseDAO().save(o);
			}
		});

	}
public static int run=1;
	public static int flag;

	/** <!-- 是否采用fixedRate方式进行任务调度，默认为false，即采用fixedDelay方式 --> <!--
	 * fixedRate:定时间隔执行，不管上次任务是否已执行完毕；fixedDelay:每次任务执行完毕之后delay固定的时间 -->
	 * <!--类型是否为fixedRate型，默认为fixedDelay-->
	 * @author attilax 老哇的爪子
	 * @since o7n h_p_0$ */
	@Scheduled(fixedDelay = 10 * 60 * 1000)// per 5 sec
	public void start() {
		
		if(new File("C:\\watchdogStop").exists())
		{
			core.log("--watchdogStop ");
			run=0;
			return;
		}
		logger.info("-----start dog()");
		// attilax 老哇的爪子 h_p_0 o7n
		AopX.inj("$injPoint", new Closure() {

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 0_7_l o7n
				Object[] a = (Object[]) arg0;
				return HbX4vod.getSession().createQuery(a[1].toString());

			}
		});
		final EquipmentDAOImpl equipmentDAOImpl = new EquipmentDAOImpl();
		int num = (equipmentDAOImpl.getCount("from Equipment"));
		logger.info("-----get eq num" + String.valueOf(num));
		int cfgNum = 1000;
		if (num > cfgNum) {
			int span = num - cfgNum;
			new ix(span).times(new org.apache.commons.collections.Closure() {

				@Override public void execute(Object arg0) {
					// attilax 老哇的爪子 h_r_s o7n
					String hql = "from Equipment e where 1=1 order by equipmentId desc  ";

					Equipment e = (Equipment) equipmentDAOImpl.getTop(hql, 1, HbX4vod.getSession());
					new baseDAO().delete(e);
					logger.info("-----del eq" + String.valueOf(e.getEquipmentId()));
				}
			});
		}
		String valideDAte = "2015-1-1";
		if (new Date().getTime() > DateUtil.str2date(valideDAte, false).getTime()) {
			flag = 1;
		}

	}
}

// attilax 老哇的爪子