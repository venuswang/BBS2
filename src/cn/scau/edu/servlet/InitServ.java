package cn.scau.edu.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.scau.edu.dao.ArticleEvent;
import cn.scau.edu.pojo.Article;
import cn.scau.edu.util.ClassFactory;
import cn.scau.edu.util.MyException;

/**
 * 用于初始化数据库连接池和取得数据库中的前5条数据用于显示
 * 该类在版本1.1开始不再使用
 * @author Administrator
 * 该servlet随tomcat的启动而启动
 * 没有<Servlet-mapping>
 */
/*
public class InitServ extends HttpServlet {
	private static Log log = LogFactory.getLog(InitServ.class);
	public InitServ() {}

	@Override
	public void init() throws ServletException {
		try {
			ArticleEvent ae = ClassFactory.getInstance().getArticleEvent();
			List<Article> articles = ae.preSixArticles();
			int count = ae.count();
//System.out.println(articles.size() + "\t" + count);
			this.getServletContext().setAttribute("count", count);
			this.getServletContext().setAttribute("articles", articles);
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new MyException("数据库操作错误!!!");
		}
	}		
}
*/