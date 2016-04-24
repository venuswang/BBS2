package cn.scau.edu.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.scau.edu.util.DataBasePool2;

/**
 * 作用：使用单例模式  获得连接池中的连接
 * @author Administrator
 *	param DataBasePool2 ： 数据库连接池对象  可以获得已经初始化的数据库连接
 */
public final class JdbcUtil {
	private static DataBasePool2 dbp2 = null;
	private static Log log = LogFactory.getLog(JdbcUtil.class);
	private JdbcUtil() {
		
	}
	public static void check() throws SQLException {
		if(null == dbp2) {
			dbp2 = new DataBasePool2();
		} else {
			if((System.currentTimeMillis() - dbp2.getLastUseTime()) / 1000 > 25200) {
				log.debug("超过7小时，重新检验数据库连接池中的所有连接的有效性");
				dbp2.checkConnection();
			}
		}
	}
	
	public static Connection getConnection() throws SQLException {
		if(null == dbp2) {
			dbp2 = new DataBasePool2();
		} 
		return dbp2.getConnection();
	}

}
