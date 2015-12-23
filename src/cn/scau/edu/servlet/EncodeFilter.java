package cn.scau.edu.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * 对所有的请求界面的跳转，使数据在它们之间流通的编码格式都采用“utf-8”编码
 * @author Administrator
 * 服务器启动时，该类就被初始化，每次的请求转移都会先执行该类的dofilter方法
 */
public class EncodeFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("----filter destroy----");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws IOException, ServletException {
		System.out.println("-----doFilter----");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		fc.doFilter(request, response); // 如果有下一个过滤器则跳转到下一个过滤器,否则跳转到目标页面
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("----filter init----");
	}
	
}
