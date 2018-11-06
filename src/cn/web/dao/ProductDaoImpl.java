package cn.web.dao;

import java.util.List;

import cn.web.model.Product;

// 完成Product的CRUD操作(增、删、改、查)
public class ProductDaoImpl extends BaseDao<Product> {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		// Product product = new Product();
		// product.setName("华为手机");
		// product.setPrice(5999.99);
		// product.setRemark("测试一下!!");
		// product.setId(5);
		// daoImpl.update(product);
		// daoImpl.delete(5);
		Product product = daoImpl.getById(1);
		System.out.println(product);
		List<Product> proList = daoImpl.queryByName("西服");
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		List<Product> proList = super.queryByName(Product.class, sql, id);
		return proList.size() > 0 ? proList.get(0) : null;
	}

	// ctrl + D 删除当前行
	// ctrl + shift + o 导入导出包
	// ctrl + 2,L
	// shift + alt + A 开启列删除
	public List<Product> queryByName(String keyword) {
		String sql = "select * from product where name like ?";
		return super.queryByName(Product.class, sql, "%" + keyword + "%");
	}

	public int update(Product product) {
		String sql = "update product set name = ? ,price = ? ,remark = ? where id = ?";
		return super.update(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.update(sql, id);
	}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		return super.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}
}
