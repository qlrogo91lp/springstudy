<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
	<link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
<div class="box-body">
	<form id="form" method="post">
	<table class="table table-striped">
		<tr>
			<th>
				<label for="exampleInputEmail">Title</label>
			</th>
		</tr>
		<tr>
			<td>
				<input type="text" name="title" id="title" placeholder="Enter Title" class="form-control">
			</td>
		</tr>
		<tr>
			<th>
				<label for="exampleInputPassword1">Content</label>
			</th>
		</tr>
		<tr>
			<td>
				<textarea id="content" name="content" rows="3" placeholder="Enter Content" class="form-control"></textarea>
			</td>
		</tr>
		<tr>
			<th>
				<label for="exampleInputEmail1">Writer</label>
			</th>
		</tr>
		<tr>
			<td>
				<input type="text" name="writer" id="writer" placeholder="Enter Writer" class="form-control">
			</td>
		</tr>
	</table>
	<div class="box-footer">
		<button type="submit" class="btn btn-success">Submit</button>
	</div>
</form>
</div>
</body>
</html>