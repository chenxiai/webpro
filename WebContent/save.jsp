<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>添加商品</title>
<link href="/webpro/css/bootstrap.css" rel="stylesheet" />
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
			<div class="form-group">
				<label class="col-sm-2 control-label">商品价格</label>
				<div class="col-sm-6">
					<input type="number" class="form-control" name="price"
						placeholder="商品单价">
				</div>
			</div>

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
					<textarea class="form-control" rows="10" cols="80"></textarea>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">所属类别</label>
				<div class="col-sm-6">
					<select class="form-control">
						<option>1</option>
						<option>2</option>
						<option>3</option>
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