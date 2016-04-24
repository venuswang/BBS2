package cn.scau.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

/**
 * 
 * @author Administrator 创建一个线程池 ININUM ： 初始化线程数 MAXNUM ： 最大的线程数 presentNumber
 *         ：　现阶段连接池里有的数据库连接数
 */
public class DataBasePool2 {
	private static Log log = LogFactory.getLog(DataBasePool2.class);
	private static String url = "jdbc:mysql://localhost:3306/bbs1";
	private static String user = "root";
	private static String password = "8880967wgj";
	private static String className = "com.mysql.jdbc.Driver";
	private static LinkedList<Connection> dbpool = new LinkedList<Connection>();
	private long lastUseTime = 0;
	private int ININUM = 100;
	private int MAXNUM = 1000;
	private int presentNumber;

	private MyProxyHandle myph = null;

	static {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			log.error(e);
			e.printStackTrace();
			throw new MyException("没有找到数据库驱动类！！！");
		}
	}

	public long getLastUseTime() {
		return lastUseTime;
	}

	public void setLastUseTime(long lastUseTime) {
		this.lastUseTime = lastUseTime;
	}
	
	public void checkConnection() throws SQLException {
		this.setLastUseTime(System.currentTimeMillis());
		for(int j = 0; j < dbpool.size(); j++) {
			Connection connection = dbpool.get(j);
			Statement state = null;
			ResultSet rs = null;
			try {
				state = connection.createStatement();
				rs = state.executeQuery("select voucherid from voucher where voucherid = 1");
			} catch(CommunicationsException e) {
				log.error(e);
				connection = null;
				this.dbpool.set(j,null);
				connection = DriverManager.getConnection(url, user, password);
				myph = new MyProxyHandle(this);
				this.dbpool.set(j, myph.bind(connection));
			} finally {
				connection = null;
				ConnectionnResourseFree.free(null, state, rs);
			}
		}
	}
	
	public int getPresentNumber() {
		return presentNumber;
	}

	public void setPresentNumber(int presentNumber) {
		this.presentNumber = presentNumber;
	}

	public DataBasePool2() throws SQLException {
		lastUseTime = System.currentTimeMillis();
		synchronized (DataBasePool2.class) {
			for (int i = 0; i < ININUM; i++) {
				Connection connection = createConnection();
				// MyConnection mc = new MyConnection(this,connection);
				myph = new MyProxyHandle(this);
				this.dbpool.addLast(myph.bind(connection));
			}
		}
	}

	// 创建数据库连接
	private Connection createConnection() throws SQLException {
		// if(this.presentNumber < MAXNUM) {
		presentNumber++;
		return DriverManager.getConnection(url, user, password);
		// }
	}

	public void free(Connection connection) {
		this.dbpool.addLast(connection);
		// this.presentNumber ++;
	}

	/**
	 * 往线程池中添加数据库连接 用到了动态代理 对连接的close方法进行处理 使其能重复使用
	 * 
	 * @throws SQLException
	 */
	private void addConnection() throws SQLException {
		if (this.presentNumber < MAXNUM) {
			Connection connection = createConnection();
			// MyConnection mc = new MyConnection(this,connection);
			myph = new MyProxyHandle(this);
			this.dbpool.addLast(myph.bind(connection));
			// this.dbpool.addLast(mc);
		} else {
			log.error("连接池已满");
			throw new InstantiationError("连接池已满");
		}
	}

	// 得到数据库连接
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		if (0 != dbpool.size()) {
			connection = dbpool.removeFirst();
			Statement state = null;
			ResultSet rs = null;
			try {
				 log.debug("开始检验连接的有效性");
				 state = connection.createStatement();
				 rs = state.executeQuery("select voucherid from voucher where voucherid = 1");
			} catch(CommunicationsException e) {
				log.debug(e);
				connection = null;
				connection = DriverManager.getConnection(url, user, password);
				myph = new MyProxyHandle(this);
				connection = myph.bind(connection);
			} finally {
				ConnectionnResourseFree.free(null, state, rs);
			}
			// this.presentNumber --;
		} else {
			addConnection();
			connection = dbpool.removeFirst();
			// this.presentNumber --;
		}
		// System.out.println(connection);
		log.debug(connection);
		return connection;
	}
}
