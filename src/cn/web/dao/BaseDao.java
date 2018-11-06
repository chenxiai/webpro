package cn.web.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.web.utils.JdbcUtils;

// 编写一个公共的dao父类,此类用来抽取共性的代码,子dao都需要继承此类
// 只要类中有T类型,那么必须把当前类设置为T类
public class BaseDao<T> {

	// 可变参数就是数组,但是它比数组写法更灵活
	public List<T> queryByName(Class clazz, String sql, Object... param) {
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
			// 获取关于 ResultSet 对象中列的类型和属性信息的对象(id,name)
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				// 表中的每条记录对应Java中一个对象
				T model = (T) clazz.newInstance();
				// 获取当前行字段信息
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					// 根据列索引获取列名称(id,name,price,remark)
					String colName = metaData.getColumnName(i);
					// 根据字段名,通过反射获取属性名
					Field field = clazz.getDeclaredField(colName);
					// 取消Java的语法检查
					field.setAccessible(true);
					field.set(model, rs.getObject(colName));
				}
				// 把赋值成功的对象交给集合
				tList.add(model);
			}
			return tList;
		} catch (Exception e) {
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
