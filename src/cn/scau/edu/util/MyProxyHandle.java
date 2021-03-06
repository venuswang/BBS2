package cn.scau.edu.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 使用动态代理 修正数据库连接在调用close方法时的连接关闭问题
 * 
 * @author Administrator parame USERNUM ： 当前数据库连接已经被使用的次数 param USERMAX ：
 *         当前数据库连接最大能被使用的次数
 */
public class MyProxyHandle implements InvocationHandler {
	private Connection realConnection = null;
	private Connection reflectConnection = null;
	private static Log log = LogFactory.getLog(MyProxyHandle.class);
	private DataBasePool2 dbp2 = null;
	private long lastVisitTime = 0;
	private int USERNUM = 0;
	private int USERMAX = 1000;

	public long getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(long lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	public MyProxyHandle(DataBasePool2 dbp2) {
		this.dbp2 = dbp2;
	}

	public Connection bind(Connection conn) {
		this.realConnection = conn;
		reflectConnection = (Connection) Proxy.newProxyInstance(this.getClass()
				.getClassLoader(), new Class[] { Connection.class }, this);
		return reflectConnection;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if ("close".equals(method.getName())) {
			log.debug(reflectConnection + "close\n");
			USERNUM++;
			if (USERNUM < USERMAX) {
				dbp2.free(reflectConnection);
			} else {
				realConnection.close();
				dbp2.setPresentNumber(dbp2.getPresentNumber() - 1);
			}

		} else {
			return method.invoke(realConnection, args);
		}
		return null;
	}

}
