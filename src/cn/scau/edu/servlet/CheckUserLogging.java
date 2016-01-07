package cn.scau.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.scau.edu.dao.RegisterEvent;
import cn.scau.edu.util.ClassFactory;

public class CheckUserLogging extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CheckUserLogging.class);
	private static RegisterEvent register = ClassFactory.getInstance().getRegisterEvent();
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String result = "";
		String username = request.getParameter("username");
		System.out.println(username);
		if(username == null) {
			result = "0";
			pw.print(result);
			return;
		}
		username = username.trim();
		if("".equals(username)) {
			result = "0";
		} else {
			String[] token = username.split("[(,;?)#%&*'\"|=]");
			if(token.length > 1) {
				result = "3";
				pw.print(result);
				return;
			}
			if(username.length() > 35 || username.length() < 6) {
				result = "4";
				pw.print(result);
				return;
			}
			try {
				boolean flag = register.checkUser(username);
				if(flag) {
					result = "2";
				} else {
					result = "1";
				}
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
			}
		}
		//System.out.println(result);
		pw.print(result);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
