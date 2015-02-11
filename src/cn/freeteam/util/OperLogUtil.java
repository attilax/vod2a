package cn.freeteam.util;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.attilax.core;
import com.attilax.io.filex;


import cn.freeteam.dao.OperlogsMapper;
import cn.freeteam.model.Operlogs;


/**
 * 操作日志工具类
 * @author freeteam
 *2011-3-6
 */
public class OperLogUtil {

	public static void log(final String loginname,final String content,final HttpServletRequest request){
		core.execMeth_Ays(new Runnable() {
			
			@Override
			public void run() {
				try {
					if (content!=null && content.trim().length()>0) {
						OperlogsMapper mapper=MybatisSessionFactory.getSession().getMapper(OperlogsMapper.class);
						Operlogs log=new Operlogs();
						log.setId(UUID.randomUUID().toString());
						log.setContent(content);
						try {
							log.setIp(request.getRemoteAddr());
						} catch (Exception e) {
							log.setIp("cant get ip");
						}
						
						log.setLoginname(loginname);
						log.setOpertime(new Date());
						
						mapper.insert(log);
						MybatisSessionFactory.getSession().commit();
					}
				} catch (Exception e) {
					core.log(e);
					try {
						MybatisSessionFactory.getSession().rollback();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				
			}
		}, "oplogerThread"+filex.getUUidName());
		
	}
}
