<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>查询商品页面</title>
<link href="/webpro/css/bootstrap.css" rel="stylesheet" />
</head>
<script type="text/javascript">
	
</script>
<body>
	<!-- bootstrap所有内容都需要放到 class="container"样式 -->
	<div class="container">
		<!-- form-inline 称为内联表单  -->
		<form style="margin: 10px 0px" action="/webpro/product/query.mvc"
			method="get" class="form-inline">
			<!-- 每个组通过:form-group来管理,表单组里面有: label标签和表单元素  -->
			<div class="form-group">
				<!-- for与id捆绑,选中lable标签文本框也会高亮 -->
				<label for="example">查询关键字</label> <input type="text"
					class="form-control" name="keyword" placeholder="请输入查询关键字">
			</div>
			<button type="submit" class="btn btn-primary">
				<span class="glyphicon glyphicon-search"></span> 给我搜
			</button>
		</form>


		<!-- jsp目前不推荐使用%(可维护性差),可以采用jstl标签来显示数据 -->
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>价格</th>
				<th>备注</th>
				<th>日期</th>
				<th>所属类别</th>
				<th>操作</th>
			</tr>
			<!-- item是标签的属性,接受集合或者数组  EL表达式，可以获取request session application数据 -->
			<!-- 集合中每个对象都存储在var变量中 -->
			<c:forEach items="${requestScope.proList}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${product.remark}</td>
					<td>${product.date}</td>
					<td>${product.category.cname}</td>
					<td><a href="/webpro/product/getById.mvc?id=${product.id}">更新</a>|
						<a href="/webpro/product/delete.mvc?id=${product.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<!-- 
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		 -->
	</div>
</body>
</html>