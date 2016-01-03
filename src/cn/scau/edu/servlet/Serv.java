package cn.scau.edu.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.scau.edu.dao.ArticleEvent;
import cn.scau.edu.dao.ArticleEventImp;
import cn.scau.edu.dao.RegisterEvent;
import cn.scau.edu.dao.RegisterEventImp;
import cn.scau.edu.pojo.Article;
import cn.scau.edu.pojo.Author;
import cn.scau.edu.util.ClassFactory;

/**
 * 属于mvc架构中的control层，逻辑处理的层次
 * @author Administrator
 *
 */
public class Serv extends HttpServlet {
	private static Log log = LogFactory.getLog(Serv.class);
	private static ArticleEvent ae = ClassFactory.getInstance().getArticleEvent();
	private static RegisterEvent re = ClassFactory.getInstance().getRegisterEvent();
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
		System.out.println(operation);
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
				log.error(e);
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
		} else if("authorShow".equals(operation)) {  //展示用户信息
			if(request.getSession().getAttribute("login") == null) {
				request.setAttribute("state", "您还没登录呢...");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			String id = request.getParameter("id");
			System.out.println(id);
			if(null == id || "".equals(id.trim())) {
				request.setAttribute("warn", "敬爱的用户，你查找的用户不存在!!!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			int tid = 0;
			try {
				tid = Integer.parseInt(id);
			} catch(NumberFormatException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，你查找的用户不存在!!!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			Author author = null;
			try {
				author = re.show(tid);
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			
			if(null == author) {
				request.setAttribute("warn", "敬爱的用户，你查找的用户不存在!!!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			} else {
				request.setAttribute("author", author);
				request.getRequestDispatcher("show.jsp").forward(request, response);
				return ;
			}
		} else if("loginCheck".equals(operation)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(null == username || null == password || "".equals(username.trim()) || "".equals(password.trim())) {
				request.setAttribute("state", "用户名或密码不能为空...");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			Author author = null;
			try {
				author = re.checkAuthor(username, password);
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			if(null == author) {
				request.setAttribute("state", "用户名或密码不正确...");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("login", "login");
				session.setAttribute("uname", author.getName());
				session.setAttribute("id", author.getId());
				request.getRequestDispatcher("article.jsp").forward(request, response);
			}
		} else if("postArticle".equals(operation)) {
			String title = request.getParameter("title");
			String cont = request.getParameter("cont");
			String authid = request.getParameter("authorid");
			if(title == null || "".equals(title.trim()) || cont == null || "".equals(cont.trim()) || authid == null || "".equals(authid.trim())) {
				request.setAttribute("warn", "敬爱的用户，您的输入有问题，标题或主题可能为空...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			title = title.trim();
			cont = cont.trim();
			authid = authid.trim();
			//continue  tomarrow
		} else {
			request.getRequestDispatcher("article.jsp").forward(request, response);
			return;
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
