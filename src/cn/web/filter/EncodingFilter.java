package cn.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// 当前类是过滤器,可以在web.xml中配置过滤的编码
// filter: 是单例模式,项目启动的时候就会创建
public class EncodingFilter implements Filter {

	@Override // 项目关闭的时候销毁
	public void destroy() {
		System.out.println("----destroy---");
	}

	@Override // 符合条件的请求，都会进入到此方法
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("---doFilter---");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 跳转到目标页面(并且把配置了编码的request,response传入)
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("---------init---------");
	}

}
