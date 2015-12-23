package cn.scau.edu.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.scau.edu.dao.ArticleEvent;
import cn.scau.edu.dao.ArticleEventImp;
import cn.scau.edu.dao.RegisterEvent;
import cn.scau.edu.dao.RegisterEventImp;
import cn.scau.edu.pojo.Author;

/**
 * 属于mvc架构中的control层，逻辑处理的层次
 * @author Administrator
 *
 */
public class Serv extends HttpServlet {
	private static ArticleEvent ae = new ArticleEventImp();
	private static RegisterEvent re = new RegisterEventImp();
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
		String operation = request.getParameter("operation");
		if("regist".equals(operation)) {   							//如果是注册操作
			Author author = new Author();
			author.setName(request.getParameter("username"));
			author.setPassword(request.getParameter("pwd"));
			author.setSex(request.getParameter("sex"));
			String slikes = "";
			String[] likes = request.getParameterValues("slikes");
			for(int i = 0; i < likes.length - 1; i++) {
				slikes += likes[i] + "-";
			}
			slikes += likes[likes.length - 1]; 
			author.setSlikes(slikes);
			String mlikes = "";
			String[] likess = request.getParameterValues("mlikes");
			for(int i = 0; i < likess.length - 1; i++) {
				mlikes += likess[i] + "-";
			}
			mlikes += likess[likess.length - 1]; 
			author.setMlikes(mlikes);
			author.setPrivince(request.getParameter("province"));
			author.setIntroduce(request.getParameter("intro"));
			int flag = -1;
			try {
				flag = re.addRegister(author);
			} catch (SQLException e) {
				flag = -1;
				e.printStackTrace();
			}
			if(flag != -1) {
				HttpSession session = request.getSession();
				session.setAttribute("login", "login");
				session.setAttribute("uname", author.getName());
				session.setAttribute("id", flag);
				request.getRequestDispatcher("article.jsp").forward(request, response);
			} else {
				request.setAttribute("warn", "敬爱的用户，由于服务器出现暂时的抽风，你的注册没有成功，请稍后再注册，在这过程中给您造成的不便，妾身在这里向您赔不是了!!!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
//System.out.println(author);
		}
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
