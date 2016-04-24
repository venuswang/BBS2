package cn.scau.edu.servlet;



import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.scau.edu.util.JdbcUtil;

public class ConnectionPoolListerner implements ServletContextListener {
	private static Log log = LogFactory.getLog(ConnectionPoolListerner.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.debug("我(监听器)被销毁了...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.debug("我(监听器)被启动了");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					JdbcUtil.check();
				} catch (SQLException e) {
					log.error(e);
				}
			}
		},0,7*60*60*1000+20*60*1000); //7hours and 20mins
	}

}
