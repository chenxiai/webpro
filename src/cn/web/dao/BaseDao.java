package cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.web.utils.JdbcUtils;

// 编写一个公共的dao父类,此类用来抽取共性的代码,子dao都需要继承此类
// 只要类中有T类型,那么必须把当前类设置为T类
public abstract class BaseDao<T> {

	// 父类定义抽象方法,子类根据自身业务获取查询的结果集
	protected abstract T getRow(ResultSet rs) throws SQLException;

	// 可变参数就是数组,但是它比数组写法更灵活
	public List<T> queryByName(String sql, Object... param) {
		List<T> tList = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			pre = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				T t = this.getRow(rs);
				tList.add(t);
			}
			return tList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(conn, pre, rs);
		}
	}

	// // T: 只能代表某一个类型,具体可以交给子类指定
	// protected T getById(String sql, int id) {
	// T model = null;
	// Connection conn = null;
	// PreparedStatement pre = null;
	// ResultSet rs = null;
	// try {
	// conn = JdbcUtils.getConnection();
	// pre = conn.prepareStatement(sql);
	// pre.setInt(1, id);
	// rs = pre.executeQuery();
	// if (rs.next()) {
	// model = this.getRow(rs);
	// }
	// return model;
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// } finally {
	// JdbcUtils.close(conn, pre);
	// }
	// }

	// 通常把数据的插入、更新、和删除理解成广义更新
	protected int update(String sql, Object... param) {
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
		} finally {
			JdbcUtils.close(conn, pre);
		}
	}

}
