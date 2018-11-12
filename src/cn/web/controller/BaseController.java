package cn.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.web.service.CategoryServiceImpl;
import cn.web.service.ProductServiceImpl;

// 此类不接受任何请求访问，仅仅是存储共性的代码
public class BaseController {

	// 根据类型注入request session application
	// mvc已经做了封装,request和session都是当前线程的request与session
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpSession session;

	// 此注解取代xml<bean>配置, 当前Controller依赖productService
	protected ProductServiceImpl productService = null;

	protected CategoryServiceImpl categoryService = null;

	public void setCategoryService(CategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}
}
