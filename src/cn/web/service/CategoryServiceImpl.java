package cn.web.service;

import java.util.List;

import cn.web.dao.CategoryDaoImpl;
import cn.web.model.Category;

// 创建业务逻辑类,此类用来实现Product的业务逻辑
// 正常 Jps --> Servlet --> Service ---> Dao ---> DB
// 显示层一般又会采用 MVC设计模式   V视图(JSP)   C控制层(Servlet)   M模型(Product)
public class CategoryServiceImpl {

	private CategoryDaoImpl categoryDao = null;

	public void setCategoryDao(CategoryDaoImpl categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> queryByName(String keyword) {
		return categoryDao.queryByName(keyword);
	}
}
