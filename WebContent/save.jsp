<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>1: 404: 当前web容器已经启动成功,但是资源地址出错!
		<li>2: 500: 当前web容器已经启动成功,而且资源已找到,但是tomcat在解析jsp页面的时候抛出异常
		<li>3: 无法建立到 localhost:8080 服务器的连接,则说明可能是服务器未启动, 或者端口不正确,
			或者输入https
	</ul>
	
	<b>注意：WebConent(存储前端资源),与src(存储.java源文件)都是虚拟目录.编译之后都会消失</b>
</body>
</html>