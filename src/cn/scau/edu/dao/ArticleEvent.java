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
	
	public void addArticle(Article article) throws SQLException;
	
	public List<Article> findArticle(int rootid, boolean flag) throws SQLException;
	
	public boolean deleteArticle(int rootid) throws SQLException ;
	
	public boolean updateArticle(Article article) throws SQLException;
	
	public int count() throws SQLException;
	
	public List<Article> getArticles(int start,int scope) throws SQLException; 
}
