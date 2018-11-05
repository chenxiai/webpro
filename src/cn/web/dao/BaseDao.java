package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.web.utils.JdbcUtils;

// 编写一个公共的dao父类,此类用来抽取共性的代码,子dao都需要继承此类
public class BaseDao {

	// 通常把数据的插入、更新、和删除理解成广义更新
	protected int update(String sql, Object[] param) {
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement(sql);
			// ?的下标是从1开始
			// pre.setString(1, product.getName());
			// pre.setDouble(2, product.getPrice());
			// pre.setString(3, product.getRemark());
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			// 2: 插入操作，返回受影响行数
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
