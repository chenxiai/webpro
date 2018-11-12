package cn.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.web.model.Category;

// 并没有继承HttpServlet,它也是单例模式
// @RequestMapping: 用来指定访问当前controller的url地址
//注解本质上就是代码,这些注释并不是系统默认注释,因此需要开启注解扫描功能
@RequestMapping(value = "/category")
// @Controller // 等同在xml中配置bean
public class CategoryController extends BaseController {

	// 前端发送的ajax请求,因此后台返回的应该是json格式,而不是html页面
	@RequestMapping(value = "/query")
	@ResponseBody // 返回json格式,需要加载jackson三个jar包
	public Object query() {
		System.out.println("query()..........");
		List<Category> catList = categoryService.queryByName("");
		System.out.println(catList);
		return catList;
	}

}
