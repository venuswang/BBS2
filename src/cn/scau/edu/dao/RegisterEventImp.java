package cn.scau.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.scau.edu.pojo.Author;
import cn.scau.edu.util.JdbcUtil;

/**
 * DAO层 用来处理关于用户的有关数据库存取及读取的操作
 * @author Administrator
 *
 */
public class RegisterEventImp implements RegisterEvent {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int addRegister(Author author) throws SQLException {
		int flag = -1;
		String sql = "insert into author(id,sex,slikes,mlikes,privince,introduce) values (null,?,?,?,?,?)";
		con = JdbcUtil.getConnection();
		ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, author.getSex());
		ps.setString(2, author.getSlikes());
		ps.setString(3, author.getMlikes());
		ps.setString(4, author.getPrivince());
		ps.setString(5, author.getIntroduce());
		con.setAutoCommit(false);
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		int id = 0;
		if(rs.next()) {
			id = rs.getInt(1);
		}
		String xsql = "insert into voucher(voucherid,name,password) values (?,?,?)";
		ps = con.prepareStatement(xsql);
		ps.setInt(1, id);
		ps.setString(2, author.getName());
		ps.setString(3, author.getPassword());
		ps.executeUpdate();
		con.setAutoCommit(true);
		flag = id;
		return flag;
	}

}
