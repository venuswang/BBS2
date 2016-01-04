package cn.scau.edu.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
	private static int showNum = 8;
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
		} else if("loginCheck".equals(operation)) {  //检查用户登录的密码和账号是否准确
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
		} else if("postArticle".equals(operation)) {  //提交主帖操作
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
			int autherid = 0;
			try {
				autherid = Integer.parseInt(authid);
			} catch(NumberFormatException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，您的输入有问题，找不到您想找的页面...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			Article article = new Article();
			article.setAuthorid(autherid);
			article.setCont(cont);
			article.setTitle(title);
			try {
				ae.addArticle(article);
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			request.getRequestDispatcher("replyDeal.jsp").forward(request, response);
			return;
		} else if("showArticles".equals(operation)) {  //展示帖子
			String pageNum = request.getParameter("pageNo");
			int pageNo = 1;
			if(null == pageNum || pageNum.trim().equals("")) {
				pageNo = 1;
			}
			try {
				pageNo = Integer.parseInt(pageNum);
			} catch(NumberFormatException e) {
				pageNo = 1;
			}
			if(pageNo < 1) {
				pageNo = 1;
			}
			int total = 0;
			try {
				total = ae.count();
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			int totalMaxNum = (total % showNum == 0) ? (total / showNum) : (total / showNum + 1);
			if(pageNo > totalMaxNum) {
				pageNo = totalMaxNum;
			}
			int start = (pageNo - 1) * showNum;
			List<Article> articles = null;
			try {
				articles = ae.getArticles(start, showNum);
			} catch(SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("total", totalMaxNum);
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("article.jsp").forward(request, response);
			return ;
		} else if("detailArticles".equals(operation)) {  //查看子帖
			String id = request.getParameter("rootid");
			if(id == null || id.trim().equals("")) {
				request.setAttribute("warn", "敬爱的用户，找不到您想找的页面...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			int tid = 0;
			try {	
				tid = Integer.parseInt(id);
			} catch(NumberFormatException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，找不到您想找的页面...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			List<Article> articles = null;
			boolean flag = false;
			if(request.getSession().getAttribute("" + tid) == null) {
				flag = true;
			}
			try {
				articles = ae.findArticle(tid,flag);
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			if(articles == null || articles.size() == 0) {
				request.setAttribute("warn", "敬爱的用户，找不到您想找的页面...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			if(flag) {
				request.getSession().setAttribute(("" + tid),tid);
			}
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("detail.jsp").forward(request, response);
			return;
		} else if("replyArticle".equals(operation)) {  //回复帖子
			String title = request.getParameter("title");
			String cont = request.getParameter("cont");
			String authid = request.getParameter("authorid");
			String pid = request.getParameter("pid");
			String rootid = request.getParameter("rootid");
			String floor = request.getParameter("floor");
			String replyName = request.getParameter("replyName");
			if(title == null || "".equals(title.trim()) || cont == null || "".equals(cont.trim()) || authid == null || "".equals(authid.trim())) {
				request.setAttribute("warn", "敬爱的用户，您的输入有问题，标题或主题可能为空...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			if(pid == null || "".equals(pid.trim()) || rootid == null || "".equals(rootid.trim()) || replyName == null || "".equals(replyName.trim())) {
				request.setAttribute("warn", "敬爱的用户，您的输入有问题，标题或主题可能为空...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			title = title.trim();
			cont = cont.trim();
			authid = authid.trim();
			pid = pid.trim();
			rootid = rootid.trim();
			replyName = replyName.trim();
			cont = "to:#" + floor + "&nbsp;" + replyName + "<br />"  + cont;
			int autherid = 0;
			int tpid = 0;
			int trootid = 0;
			try {
				autherid = Integer.parseInt(authid);
				tpid = Integer.parseInt(pid);
				trootid = Integer.parseInt(rootid);
			} catch(NumberFormatException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，您的输入有问题，找不到您想找的页面...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			Article article = new Article();
			article.setAuthorid(autherid);
			article.setPid(tpid);
			article.setRootid(trootid);
			article.setCont(cont);
			article.setTitle(title);
			try {
				ae.updateArticle(article);
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的用户，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			request.getRequestDispatcher("replyDeal.jsp").forward(request, response);
			return;
		} else if("hloginCheck".equals(operation)) {  //用于后台管理员的登录
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(null == username || "".equals(username.trim()) || null == password || "".equals(password.trim())) {
				request.setAttribute("warning", "敬爱管理员，您输入的账号或密码错误...");
				request.getRequestDispatcher("loginer.jsp").forward(request, response);
				return ;
			}
			username = username.trim();
			password = password.trim();
			boolean flag = false;
			try {
				flag = re.checkLoginer(username, password);
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warning", "敬爱管理员，您输入的账号或密码错误...");
				request.getRequestDispatcher("loginer.jsp").forward(request, response);
				return ;
			}
			if(flag == false) {
				request.setAttribute("warning", "敬爱管理员，您输入的账号或密码错误...");
				request.getRequestDispatcher("loginer.jsp").forward(request, response);
				return ;
			}
			request.getSession().setAttribute("loginer", "loginer");
			request.getRequestDispatcher("article.jsp").forward(request, response);
			return ;
		} else if("deleteArticle".equals(operation)) {  //管理员删除帖子
			System.out.println("delete......");
			String loginer = (String)request.getSession().getAttribute("loginer");
			if(loginer == null || !loginer.equals("loginer")) {
				request.setAttribute("warning", "敬爱管理员，请先登录...");
				request.getRequestDispatcher("loginer.jsp").forward(request, response);
				return ;
			}
			String rootid = request.getParameter("rootid");
			if(null == rootid || "".equals(rootid.trim())) {
				request.setAttribute("warn", "敬爱的管理员，您的操作有误,请正确操作...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			rootid = rootid.trim();
			int id = 0;
			try {
				id = Integer.parseInt(rootid);
			} catch(NumberFormatException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的管理员，您的操作有误,请正确操作...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			boolean flag = false;
			try {
				flag = ae.deleteArticle(id);
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("warn", "敬爱的管理员，服务器出错，请稍后再试...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			}
			if(!flag) {
				request.setAttribute("warn", "敬爱的管理员，删除失败，可能该帖已经在数据库中不存在了...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return ;
			} else {
				request.getRequestDispatcher("article.jsp").forward(request, response);
				return ;
			}
		} else {   //默认返回帖子首页
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
