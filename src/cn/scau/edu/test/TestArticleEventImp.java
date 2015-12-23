package cn.scau.edu.test;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Test;

import cn.scau.edu.pojo.Article;
import cn.scau.edu.servlet.InitServ;
import cn.scau.edu.util.ClassFactory;

public class TestArticleEventImp {

	@Test
	public void testAddArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() throws SQLException {
		int count = ClassFactory.getInstance().getArticleEvent().count();
		assertThat(count, equalTo(23));
	}

	@Test
	public void testInitServ() {
		try {
			InitServ is = new InitServ();
			is.init();
			//assertThat(23, equalTo());			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPreSixArticles() throws ServletException {
		List<Article> articles;
		try {
			articles = ClassFactory.getInstance().getArticleEvent().preSixArticles();
			int count = articles.size();
			assertThat(6,lessThanOrEqualTo(count));
			assertThat(articles.get(0).getId(),equalTo(35));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
