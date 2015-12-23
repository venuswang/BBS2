package cn.scau.edu.dao;

import java.sql.SQLException;
import java.util.List;

import cn.scau.edu.pojo.Article;

/**
 * 定义论坛帖子的增删查改操作
 * @author Administrator
 *
 */
public interface ArticleEvent {
	
	public void addArticle(Article article);
	
	public List<Article> findArticle(String title,int start,int limit);
	
	public boolean deleteArticle(int id);
	
	public boolean updateArticle(Article article,int id);
	
	public int count() throws SQLException;
	
	public List<Article> preSixArticles() throws SQLException; 
}
