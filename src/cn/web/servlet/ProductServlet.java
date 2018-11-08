package cn.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.web.model.Product;
import cn.web.service.ProductServiceImpl;

// 1: HttpServlet价值可以提供一个访问URL地址可以通过注解指定：@WebServlet
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

	// jsp->servlet->service->dao->db

	private ProductServiceImpl productService = new ProductServiceImpl();

	// doGet只能接受get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// doPost只能接受post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	}

}
