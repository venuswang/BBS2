package cn.scau.edu.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionnResourseFree {
	public static void free(Connection con,Statement statement,ResultSet rs) throws SQLException {
		if (null != statement) {
			statement.close();
			statement = null;
		}
		if (null != rs) {
			rs.close();
			rs = null;
		}
		if (null != con) {
			con.close();
			con = null;
		}
	}
}
