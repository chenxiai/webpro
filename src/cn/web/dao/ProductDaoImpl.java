package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.web.model.Product;
import cn.web.utils.JdbcUtils;

// 完成Product的CRUD操作(增、删、改、查)
public class ProductDaoImpl {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		Product product = new Product();
		product.setName("小米音箱");
		product.setPrice(333.89);
		// daoImpl.save(product);
		daoImpl.delete(4);
	}

	public int delete(int id) {
		// 1: 连接数据库
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement("delete from product where id = ?");
			// ?的下标是从1开始
			pre.setInt(1, id);
			// 2: 插入操作，返回受影响行数
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3: 关闭资源(忽略)
	}

	public int save(Product product) {
		// 1: 连接数据库
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement("insert into product (name,price,remark) values (?,?,?)");
			// ?的下标是从1开始
			pre.setString(1, product.getName());
			pre.setDouble(2, product.getPrice());
			pre.setString(3, product.getRemark());
			// 2: 插入操作，返回受影响行数
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3: 关闭资源(忽略)
	}
}
