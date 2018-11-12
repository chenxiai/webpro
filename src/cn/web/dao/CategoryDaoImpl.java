package cn.web.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.web.model.Category;

// 完成Product的CRUD操作(增、删、改、查)
public class CategoryDaoImpl {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		System.out.println("setJdbcTemplate...........");
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Category> queryByName(String keyword) {
		String sql = "select * from category where cname like ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class), "%" + keyword + "%");
	}
}
