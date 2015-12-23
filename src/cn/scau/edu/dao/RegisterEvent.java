package cn.scau.edu.dao;

import java.sql.SQLException;

import cn.scau.edu.pojo.Author;

public interface RegisterEvent {
	public int addRegister(Author author) throws SQLException;
}
