package cn.scau.edu.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.scau.edu.pojo.Article;
import cn.scau.edu.util.ConnectionnResourseFree;
import cn.scau.edu.util.JdbcUtil;

/**
 * DAO层  用来处理关于帖子的所有关于数据库的操作
 * @author Administrator
 *
 */
public class ArticleEventImp implements ArticleEvent {
	private List<Article> articles = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Connection con = null;
	private static Log log = LogFactory.getLog(ArticleEventImp.class);
	
	@Override
	public void addArticle(Article article) throws SQLException {
		try {
			String sql = "insert into article values (null,0,-1,?,?,now(),0,1,?,0,?)";
			con = JdbcUtil.getConnection();
			boolean autoCommit = con.getAutoCommit();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, article.getTitle());
			ps.setString(2, article.getCont());
			ps.setInt(3, article.getAuthorid());
			ps.setInt(4, article.getAuthorid());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
			}
			Statement statement = con.createStatement();
			statement.executeUpdate("update article set rootid = " + id + " where id = " + id);
			con.commit();
			con.setAutoCommit(autoCommit);
			if(null != statement) {
				statement.close();
				statement = null;
			}
		} finally {
			ConnectionnResourseFree.free(con, ps, rs);
		}
	}
	
	/**
	 * 找出所有的子帖
	 * @param rootid  主帖的根地址
	 * @param flag 判断是否是用户第一次点击该页面，是的话就等于true
	 */
	@Override
	public List<Article> findArticle(int rootid,boolean flag) throws SQLException {
		articles = new ArrayList<Article>();
		Class article = Article.class;
		String sql = "select Id,Pid,Rootid,Title,Cont,Pdate,Isleaf,Scan,Authorid,Reply,Latestreply from article where rootid = ? order by pdate";
		con = JdbcUtil.getConnection();
		boolean autoCommit = false;
		try {
			if(flag) {
				autoCommit = con.getAutoCommit();
				con.setAutoCommit(false);
				Statement stm = con.createStatement();
				stm.executeUpdate("update article set scan = scan + 1 where id = " + rootid);
				if(stm != null) {
					stm.close();
					stm = null;
				}
			}
			ps = con.prepareStatement(sql);
			ps.setInt(1, rootid);
			rs = ps.executeQuery();
			ResultSetMetaData msmd = rs.getMetaData();
			int count = msmd.getColumnCount();
	//msmd.getColumnLabel(column)
	//article.getClass().getDeclaredMethod("set" + msmd.getColumnLabel(i));
			Method[] method = article.getDeclaredMethods();
			Article at = null;
			while(rs.next()) {
	//article = new Article();
				try {
					at = (Article)article.newInstance();
				} catch (InstantiationException e1) {
					log.error(e1);
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					log.error(e1);
					e1.printStackTrace();
				}
				
				for(int j = 0; j < method.length; j++) {
					for(int i = 1; i <= count; i++) {
						if(method[j].getName().equals("set" + msmd.getColumnLabel(i))) {
							try {
	//System.out.println(method[j].getName() + "\t" + rs.getObject(msmd.getColumnLabel(i)));
								method[j].invoke(at, rs.getObject(msmd.getColumnLabel(i)));
							} catch (IllegalAccessException e) {
								log.error(e);
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								log.error(e);
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								log.error(e);
								e.printStackTrace();
							}
						}
					}
				}
				articles.add(at);
	//System.out.println(at.getId());
			}
			for(int i = 0; i < articles.size(); i++) {
				int aid = articles.get(i).getAuthorid();
				Statement sta = con.createStatement();
				ResultSet rsa = sta.executeQuery("select name from voucher where voucherid = " + aid);
				if(rsa.next()) {
					articles.get(i).setAuthorName(rsa.getString("name"));
				}
				if(sta != null) {
					sta.close();
					sta = null;
				}
				if(rsa != null) {
					rsa.close();
					rsa = null;
				}
			}
			if(flag) {
				con.commit();
				con.setAutoCommit(autoCommit);
			}
			if(null != msmd) {
				msmd = null;
			}
			if(null != article) {
				article = null;
			}
		} finally {
			ConnectionnResourseFree.free(con, ps, rs);
		}
		return articles;
	}

	@Override
	public boolean deleteArticle(int rootid) throws SQLException {
		boolean flag = false;
		try {
			con = JdbcUtil.getConnection();
			String sql = "delete from article where rootid = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rootid);
			int num = ps.executeUpdate();
			if(num > 0) {
				flag = true;
			}
		} finally {
			ConnectionnResourseFree.free(con, ps, rs);
		}
		return flag;
	}
	
	//更新帖子列表
	@Override
	public boolean updateArticle(Article article) throws SQLException {
		boolean flag = false;
		try {
			String sql = "insert into article values (null,?,?,?,?,now(),0,1,?,0,?)";
			con = JdbcUtil.getConnection();
			boolean autoCommit = con.getAutoCommit();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setInt(1, article.getPid());
			ps.setInt(2, article.getRootid());
			ps.setString(3, article.getTitle());
			ps.setString(4, article.getCont());
			ps.setInt(5, article.getAuthorid());
			ps.setInt(6, article.getAuthorid());
			int num1 = ps.executeUpdate();
			Statement statement = con.createStatement();
			int num2 = statement.executeUpdate("update article set isleaf = 1 , reply = reply + 1 , latestreply = " + article.getAuthorid() + " where id = " + article.getPid());
			Statement statement1 = null;
			int num3 = 1;
			if(article.getPid() != article.getRootid()) {
				statement1 = con.createStatement();
				num3 = statement1.executeUpdate("update article set isleaf = 1 , reply = reply + 1 , latestreply = " + article.getAuthorid() + " where id = " + article.getRootid());
			}
			con.commit();
			con.setAutoCommit(autoCommit);
			if(null != statement) {
				statement.close();
				statement = null;
			}
			if(article.getPid() != article.getRootid()) {
				if(null != statement1) {
					statement1.close();
					statement1 = null;
				}
			}
			if(num1 > 0 && num2 > 0 && num3 > 0) {
				flag = true;
			}
		} finally {
			ConnectionnResourseFree.free(con, ps, rs);
		}
		return flag;
	}
	
	/**
	 * 计算帖子的总和
	 */
	@Override
	public int count() throws SQLException {
		int num = 0;
		try {
			String sql = "select count(Id) as num from article where pid = 0";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				num = rs.getInt("num");
	//System.out.println(num);
			}	
		} finally {
			ConnectionnResourseFree.free(con, ps, rs);
		}
		return num;
	}

	/**
	 * 得到帖子
	 * 使用反射的方式动态的生成一个个的Article的JavaBean
	 * @param start  表示要开始查的帖子的起始帖在数据库中的位置，在页面表示要展示第几页的帖子
	 * @param scope  表示要展示的每页的帖子数
	 */
	@Override
	public List<Article> getArticles(int start,int scope) throws SQLException {
		articles = new ArrayList<Article>();
		try {
			Class article = Article.class;
			String sql = "select Id,Pid,Rootid,Title,Cont,Pdate,Isleaf,Scan,Authorid,Reply,Latestreply from article where pid = 0 order by pdate desc limit ?,?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, scope);
			rs = ps.executeQuery();
			ResultSetMetaData msmd = rs.getMetaData();
			int count = msmd.getColumnCount();
	//msmd.getColumnLabel(column)
	//article.getClass().getDeclaredMethod("set" + msmd.getColumnLabel(i));
			Method[] method = article.getDeclaredMethods();
			Article at = null;
			while(rs.next()) {
	//article = new Article();
				try {
					at = (Article)article.newInstance();
				} catch (InstantiationException e1) {
					log.error(e1);
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					log.error(e1);
					e1.printStackTrace();
				}
				
				for(int j = 0; j < method.length; j++) {
					for(int i = 1; i <= count; i++) {
						if(method[j].getName().equals("set" + msmd.getColumnLabel(i))) {
							try {
	//System.out.println(method[j].getName() + "\t" + rs.getObject(msmd.getColumnLabel(i)));
								method[j].invoke(at, rs.getObject(msmd.getColumnLabel(i)));
							} catch (IllegalAccessException e) {
								log.error(e);
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								log.error(e);
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								log.error(e);
								e.printStackTrace();
							}
						}
					}
				}
				articles.add(at);
	//System.out.println(at.getId());
			}
			for(int i = 0; i < articles.size(); i++) {
				int aid = articles.get(i).getAuthorid();
				int lid = articles.get(i).getLatestreply();
				Statement sta = con.createStatement();
				ResultSet rsa = sta.executeQuery("select name from voucher where voucherid = " + aid);
				if(rsa.next()) {
					articles.get(i).setAuthorName(rsa.getString("name"));
				}
				Statement stl = con.createStatement();
				ResultSet rsl = sta.executeQuery("select name from voucher where voucherid = " + lid);
				if(rsl.next()) {
					articles.get(i).setLreplyName(rsl.getString("name"));
				}
				if(sta != null) {
					sta.close();
					sta = null;
				}
				if(stl != null) {
					stl.close();
					stl = null;
				}
				if(rsa != null) {
					rsa.close();
					rsa = null;
				}
				if(rsl != null) {
					rsl.close();
					rsl = null;
				}
			}
			if(null != msmd) {
				msmd = null;
			}
			if(null != article) {
				article = null;
			}
		} finally {
			ConnectionnResourseFree.free(con, ps, rs);
		}
		return articles;
	}

}
