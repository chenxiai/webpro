package cn.web.dao;

import cn.web.model.Product;

// 完成Product的CRUD操作(增、删、改、查)
public class ProductDaoImpl extends BaseDao {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		// Product product = new Product();
		// product.setName("华为手机");
		// product.setPrice(5999.99);
		// product.setRemark("测试一下!!");
		// product.setId(5);
		// daoImpl.update(product);
		daoImpl.delete(5);
	}

	public int update(Product product) {
		String sql = "update product set name = ? ,price = ? ,remark = ? where id = ?";
		return super.update(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.update(sql, new Object[] { id });
	}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		return super.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}
}
