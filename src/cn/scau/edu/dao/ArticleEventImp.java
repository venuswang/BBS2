package cn.scau.edu.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
	public void addArticle(Article article) {
		

	}

	@Override
	public List<Article> findArticle(String title,int start,int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteArticle(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArticle(Article article, int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 计算帖子的总和
	 */
	@Override
	public int count() throws SQLException {
		int num = 0;
		String sql = "select count(Id) as num from article where pid = 0";
		con = JdbcUtil.getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()) {
			num = rs.getInt("num");
//System.out.println(num);
		}	
		ConnectionnResourseFree.free(con, ps, rs);
		return num;
	}

	/**
	 * 得到前六个帖子
	 * 使用反射的方式动态的生成一个个的Article的JavaBean
	 */
	@Override
	public List<Article> preSixArticles() throws SQLException {
		articles = new ArrayList<Article>();
		Class article = Article.class;
		String sql = "select Id,Pid,Rootid,Title,Cont,Pdate,Isleaf from article where pid = 0 order by pdate desc limit 0,6";
		con = JdbcUtil.getConnection();
		ps = con.prepareStatement(sql);
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
		ConnectionnResourseFree.free(con, ps, rs);
		if(null != msmd) {
			msmd = null;
		}
		if(null != article) {
			article = null;
		}
		return articles;
	}

	

}
