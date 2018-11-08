<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加商品</title>
</head>
<body>
	<ul>
		<li>1: 404: 当前web容器已经启动成功,但是资源地址出错!
		<li>2: 500: 当前web容器已经启动成功,而且资源已找到,但是tomcat在解析jsp页面的时候抛出异常
		<li>3: 无法建立到 localhost:8080 服务器的连接,则说明可能是服务器未启动, 或者端口不正确,
			或者输入https
	</ul>
	<b>注意：WebConent(存储前端资源),与src(存储.java源文件)都是虚拟目录.编译之后都会消失</b>
	<ul>
		<li>由于jsp发送的是http请求,它是不可能直接访问Java类,此问题要交给控制层Servlet来解决</li>
		<li>Servlet也是一个Java类,但是它可以接受Http。</li>
		<li>正确解决思路:jsp->servlet->service->dao->db</li>
	</ul>
	<p>web中的乱码问题:项目编码、页面编码、request、连接数据库编码必须一致</p>
	<!-- 
	    请求: get请求,数据通过url地址栏提交,不安全.并且不能提交多媒体数据
	      post请求: 支持多媒体和XML数据的提交,但是比较耗资源
	 -->
	 <!-- jsp中所有的请求都应该从工程名开始 -->
	<form action="/webpro/ProductServlet" method="post">
		    商品名称:<input type="text" name="name"/><br />
		    商品价格:<input type="text" name="price" /><br />
		    商品备注:<input type="text" name="remark"/><br />
		 <button type="submit">提交</button>
	</form>
	<p>下图为web开发层次结构图</p>
	<img title="没事看一看" src="img/web.png" />
</body>
</html>