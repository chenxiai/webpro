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

import cn.web.model.Product;
import cn.web.service.ProductServiceImpl;

// 1: HttpServlet价值可以提供一个访问URL地址可以通过注解指定：@WebServlet
// Servlet智能做三件事：1：获取前端数据  2：调用业务逻辑 3：返回页面(结果数据)
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

	// jsp->servlet->service->dao->db

	private ProductServiceImpl productService = new ProductServiceImpl();

	// doGet只能接受get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1:获取前端数据
		String keyword = request.getParameter("keyword");
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
	}

	// doPost只能接受post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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
	}

}
