package cn.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.web.model.Product;
import cn.web.service.ProductServiceImpl;

// 1: HttpServlet价值可以提供一个访问URL地址可以通过注解指定：@WebServlet
// Servlet智能做三件事：1：获取前端数据  2：调用业务逻辑 3：返回页面(结果数据)
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

	// Servlet默认是单例模式: 第一个访问的时候被创建,之后就常驻内存
	public ProductServlet() {
		System.out.println("ProductServlet().......");
	}

	// 在单例模式下,绝对不能有可以被用户修改的成员属性,否则会出现线程安全问题
	// 解决方案: 每个用户都存储自己的查询关键字,在JSP中提供了session内置对象,可以满足此需求
	// private String keyword;

	// jsp->servlet->service->dao->db

	private ProductServiceImpl productService = new ProductServiceImpl();

	// doGet只能接受get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// doPost只能接受post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 获取每个用户自己的session
		HttpSession session = request.getSession();
		System.out.println("session" + session.getId());
		// 根据不同的type的值,来进行不同的判断操作
		String type = request.getParameter("type");
		if (type.equals("save")) {
			// 前端的所有请求数据,都被封装到request对象中(体现了封装的思想)
			// request可以通过form表单里面指定的名称来获取数据
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String remark = request.getParameter("remark");
			Product product = new Product();
			product.setName(name);
			product.setPrice(new BigDecimal(price));
			product.setRemark(remark);
			productService.save(product);
			// 返回页面(1:重定向,2:跳转)
			response.sendRedirect("/webpro/query.jsp");
		} else if (type.equals("query")) {
			// 1:获取前端数据
			String keyword = request.getParameter("keyword");
			// 把关键字存储在session中,后面当前用户会使用
			session.setAttribute("keyword", keyword);
			// 2:调用业务逻辑
			List<Product> proList = productService.queryByName(keyword);
			// System.out.println(proList.size());
			// 3: 返回页面(结果数据)
			// 1: web中提供了 request,session,application用来存储数据
			request.setAttribute("proList", proList);
			// 2: 如果重定向则不能共享request中的数据,但是转发可以,转发默认自带工程名
			// response.sendRedirect("/webpro/query.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
			// 在转发过程中,共用了之前request,repseon
			dispatcher.forward(request, response);
		} else if (type.equals("getById")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = productService.getById(id);
			// 把查询的结果保存到request中,并跳转到update.jsp页面(原始数据要回显)
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
			dispatcher.forward(request, response);
		} else if (type.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String remark = request.getParameter("remark");
			Product product = new Product();
			product.setName(name);
			product.setPrice(new BigDecimal(price));
			product.setRemark(remark);
			product.setId(id); // 如果没有指定id则默认id=0 不会报错但是更新失败
			productService.update(product);
			// 跳转到目标页面
			response.sendRedirect("/webpro/query.jsp");
		} else if (type.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			productService.delete(id);
			// 采用之前的关键字进行查询操作(之前的关键字已经存储在session中)
			String keyword = (String) session.getAttribute("keyword");
			List<Product> proList = productService.queryByName(keyword);
			request.setAttribute("proList", proList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
			dispatcher.forward(request, response);
		}
	}

}
