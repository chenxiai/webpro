package cn.web.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.web.model.Product;

// 完成Product的CRUD操作(增、删、改、查)
public class ProductDaoImpl {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		System.out.println("setJdbcTemplate...........");
		this.jdbcTemplate = jdbcTemplate;
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class), id);

	}

	// ctrl + D 删除当前行
	// ctrl + shift + o 导入导出包
	// ctrl + 2,L
	// shift + alt + A 开启列删除
	public List<Product> queryByName(String keyword) {
		String sql = "select * from product where name like ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), "%" + keyword + "%");
	}

	public int update(Product product) {
		String sql = "update product set name = ? ,price = ? ,remark = ? where id = ?";
		return jdbcTemplate.update(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}
}
