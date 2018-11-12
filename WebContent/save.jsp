<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>添加商品</title>
<link href="/webpro/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" src="/webpro/js/jquery.min.js">
</script>
<script type="text/javascript">
    $(function(){
    	// 1: 发送http的ajax请求给后台controller
    	$.get("/webpro/category/query.mvc",null,function(result){
    		// 2: 获取后台数据
    		console.info(result.length)  // [{id:.. name:...},{}]
    		// 3: 把数据提交给select下拉列表框
			$(result).each(function(){
				console.info("this-->" + this)
				// this代表当前循环的那个对象
				$("#sel").append('<option value=' + this.cid + '>'+this.cname+'</option>')
			})
    	},"json")
    })
</script>

</head>
<body>
	<div class="container">
		<form style="margin-top: 20px" class="form-horizontal"
			action="/webpro/product/save.mvc" method="post">
			<div class="form-group">
				<label class="col-sm-2 control-label">商品名称</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="name"
						placeholder="商品名称">
				</div>
			</div>
			<!-- 
			<div class="form-group">
				<label class="col-sm-2 control-label">商品价格</label>
				<div class="col-sm-6">
					<input type="number" class="form-control" name="price"
						placeholder="商品单价">
				</div>
			</div>
			-->
			<div class="form-group">
				<label class="col-sm-2 control-label">商品价格</label>
				<div class="input-group col-sm-5" style="padding-left: 15px">
					<div class="input-group-addon">$</div>
					<input type="number" class="form-control" name="price" placeholder="0.00">
					<div class="input-group-addon">.00</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">商品备注</label>
				<div class="col-sm-6">
					<textarea class="form-control" name="remark" rows="10" cols="80"></textarea>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">所属类别</label>
				<div class="col-sm-4">
					<select id="sel" name="category.cid" class="form-control">
					</select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<button type="submit" class="btn btn-danger">添加商品</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>