<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询商品页面</title>
</head>
<body>
	<form action="/webpro/ProductServlet" method="get">
		    商品名称:<input type="text" name="keyword"/><br />
		 <button type="submit">给我搜</button>
	</form>
	<%=request.getAttribute("proList") %>
</body>
</html>