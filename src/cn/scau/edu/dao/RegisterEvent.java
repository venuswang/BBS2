package cn.scau.edu.dao;

import java.sql.SQLException;

import cn.scau.edu.pojo.Article;
import cn.scau.edu.pojo.Author;

public interface RegisterEvent {
	public int addRegister(Author author) throws SQLException;
	public Author show(int id) throws SQLException;
	public boolean checkUser(String username) throws SQLException;
	public Author checkAuthor(String username,String password) throws SQLException;
}
