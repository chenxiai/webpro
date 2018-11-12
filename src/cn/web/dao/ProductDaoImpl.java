package cn.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.web.model.Category;
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
	// ctrl + 2,L // 匿名对象
	// shift + alt + A 开启列删除

	public List<Product> queryByName(String keyword) {
		String sql = "select * from product p left join category c on p.cid = c.cid where p.name like ?";
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int index) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getBigDecimal("price"));
				product.setDate(rs.getDate("date"));
				product.setRemark(rs.getString("remark"));
				Category category = new Category();
				category.setCid(rs.getInt("cid"));
				category.setCname(rs.getString("cname"));
				product.setCategory(category);
				return product;
			}

		}, "%" + keyword + "%");
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
		String sql = "insert into product (name,price,remark,cid) values (?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark(),
				product.getCategory().getCid() });
	}
}
