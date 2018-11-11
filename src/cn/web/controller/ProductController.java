package cn.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.web.model.Product;
import cn.web.service.ProductServiceImpl;

// 并没有继承HttpServlet,它也是单例模式
// @RequestMapping: 用来指定访问当前controller的url地址
//注解本质上就是代码,这些注释并不是系统默认注释,因此需要开启注解扫描功能
@RequestMapping(value = "/product")
// @Controller // 等同在xml中配置bean
public class ProductController {

	// 根据类型注入request session application
	@Resource
	private HttpServletRequest request;
	@Resource
	private HttpSession session;

	// 此注解取代xml<bean>配置, 当前Controller依赖productService
	private ProductServiceImpl productService = null;

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}

	@RequestMapping("/save")
	public String insert(Product product) {
		productService.save(product);
		return "redirect:/query.jsp";
	}

	@RequestMapping("/query")
	public String query(String keyword) {
		session.setAttribute("keyword", keyword);
		List<Product> proList = productService.queryByName(keyword);
		request.setAttribute("proList", proList);
		return "forward:/query.jsp";
	}

	@RequestMapping("/getById")
	public String getById(Integer id) {
		Product product = productService.getById(id);
		// 把查询的结果保存到request中,并跳转到update.jsp页面(原始数据要回显)
		request.setAttribute("product", product);
		return "forward:/update.jsp";
	}

	@RequestMapping("/update")
	public String update(Product product) {
		productService.update(product);
		// 跳转到目标页面
		return "redirect:/query.jsp";
	}

	@RequestMapping("/delete")
	public String delete(Integer id) {
		productService.delete(id);
		String keyword = (String) session.getAttribute("keyword");
		List<Product> proList = productService.queryByName(keyword);
		request.setAttribute("proList", proList);
		// 跳转到目标页面
		return "forward:/query.jsp";
	}

}
