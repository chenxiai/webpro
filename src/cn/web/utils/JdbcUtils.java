package cn.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// 用来连接数据库的工具类
public class JdbcUtils {

	// 很有时候有些代码只执行一次,这种代码可以存放在静态块中
	// 静态块,在当前JdbcUtils.class文件加载到JVM中执行,且执行一次
	static {
		System.out.println("static............");
		try {
			// 此处通过类全名,把驱动加载到了JVM中
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// 编写一个方法来实现数据库的连接,并且返回Connection对象
	public static Connection getConnection() {
		// 连接数据库的4个参数：账号、密码、连接字符串、驱动
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(Connection connection, Statement pre) {
		try {
			if (pre != null && !pre.isClosed()) {
				pre.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (connection != null && connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {
		Connection conn = JdbcUtils.getConnection();
		Connection conn2 = JdbcUtils.getConnection();
		System.out.println(conn);
		System.out.println(conn2);
	}
}
