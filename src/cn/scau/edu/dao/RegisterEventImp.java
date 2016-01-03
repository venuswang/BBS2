package cn.scau.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.scau.edu.pojo.Article;
import cn.scau.edu.pojo.Author;
import cn.scau.edu.util.ConnectionnResourseFree;
import cn.scau.edu.util.JdbcUtil;

/**
 * 用来处理关于注册用户的操作
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
	
	/**
	 * 用于检查新登录用户是否已在数据库中存在，存在返回true
	 */
	@Override
	public synchronized boolean checkUser(String name) throws SQLException {
		boolean flag = false;
		con = JdbcUtil.getConnection();
		String sql = "select count(*) as num from voucher where name = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt("num");
		}
		if (count > 0) {
			flag = true;
		}
		ConnectionnResourseFree.free(con, ps, rs);
		return flag;
	}
	
	/*返回用户信息*/
	@Override
	public Author show(int id) throws SQLException {
		Author author = null;
		String sql = "select * from author where id = ?";
		con = JdbcUtil.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		rs = ps.executeQuery();
		if(rs.next()) {
			author = new Author();
			author.setSex(rs.getString("sex"));
			author.setSlikes(rs.getString("slikes"));
			author.setMlikes(rs.getString("mlikes"));
			author.setIntroduce(rs.getString("introduce"));
		}
		ConnectionnResourseFree.free(con, ps, rs);
		return author;
	}

	/**
	 * 用于检查新登录用户是否已在数据库中存在，存在返回Author(voucherid,name)
	 */
	@Override
	public Author checkAuthor(String username, String password)
			throws SQLException {
		Author author = null;
		con = JdbcUtil.getConnection();
		String sql = "select voucherid,name from voucher where name = ? and password = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		rs = ps.executeQuery();
		if (rs.next()) {
			author = new Author();
			author.setId(rs.getInt("voucherid"));
			author.setName(rs.getString("name"));
		}
		ConnectionnResourseFree.free(con, ps, rs);
		return author;
	}
}
