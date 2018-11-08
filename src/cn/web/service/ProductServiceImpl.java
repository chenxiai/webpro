package cn.web.service;

import java.util.List;

import cn.web.dao.ProductDaoImpl;
import cn.web.model.Product;

// 创建业务逻辑类,此类用来实现Product的业务逻辑
// 正常 Jps --> Servlet --> Service ---> Dao ---> DB
// 显示层一般又会采用 MVC设计模式   V视图(JSP)   C控制层(Servlet)   M模型(Product)
public class ProductServiceImpl {

	private ProductDaoImpl productDao = new ProductDaoImpl();

	public Product getById(int id) {
		return productDao.getById(id);
	}

	public List<Product> queryByName(String keyword) {
		return productDao.queryByName(keyword);
	}

	public int update(Product product) {
		return productDao.update(product);
	}

	public int delete(int id) {
		return productDao.delete(id);
	}

	public int save(Product product) {
		return productDao.save(product);
	}

}
