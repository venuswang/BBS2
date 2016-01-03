package cn.scau.edu.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
/**
 * 服务器一启动就加载log4j的配置文件，用于记录项目的运行情况
 * @author Administrator
 *
 */
public class Log4JInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		
		 System.out.println("*** Log4JInitServlet 正在初始化 log4j日志设置信息 ***");  
	        String log4jLocation = config.getInitParameter("log4j-properties-location");  

	        ServletContext sc = config.getServletContext();  

	        if (log4jLocation == null) {  
	            System.err.println("*** 没有 log4j-properties-location 初始化的文件, 所以使用 BasicConfigurator初始化 ***");  
	            BasicConfigurator.configure();  
	        } else {  
	            String webAppPath = sc.getRealPath("/");  
	            String log4jProp = webAppPath + log4jLocation;  
	            File file = new File(log4jProp);  
	            if (file.exists()) {  
	                System.out.println("使用: " + log4jProp+"初始化日志设置信息");  
	                PropertyConfigurator.configure(log4jProp);  
	            } else {  
	                System.err.println("*** " + log4jProp + " 文件没有找到， 所以使用 BasicConfigurator初始化");  
	                BasicConfigurator.configure();  
	            }  
	        }
	        
	        super.init(config);  
	}

}
