package cn.scau.edu.test;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Test;

import cn.scau.edu.pojo.Article;

import cn.scau.edu.util.ClassFactory;

public class TestArticleEventImp {

	@Test
	public void testAddArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindArticle() throws SQLException {
		List<Article> articles = ClassFactory.getInstance().getArticleEvent().findArticle(2, false);
		System.out.println(articles.get(0));
	}

	@Test
	public void testDeleteArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateArticle() throws SQLException {
		Article article = new Article();
		article.setAuthorid(6);
		article.setPid(3);
		article.setRootid(3);
		article.setCont("测试帖子3--by xiaoming");
		article.setTitle("测试帖子3");
		System.out.println(ClassFactory.getInstance().getArticleEvent().updateArticle(article));
	}

	@Test
	public void testCount() throws SQLException {
		int count = ClassFactory.getInstance().getArticleEvent().count();
		assertThat(count, equalTo(23));
	}

//	@Test
//	public void testInitServ() {
//		try {
//		//	InitServ is = new InitServ();
//		//	is.init();
//			//assertThat(23, equalTo());			
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void testPreSixArticles() throws ServletException {
		List<Article> articles;
		try {
			articles = ClassFactory.getInstance().getArticleEvent().getArticles(0,6);
			int count = articles.size();
			System.out.println(articles.get(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
