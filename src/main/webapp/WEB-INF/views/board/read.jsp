<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>read</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/bootstrap.css">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#modify").on("click", function() {
				$("#form").attr("action", "/board/modify");
				$("#form").attr("method", "get");
				$("#form").submit();
			});
			$("#remove").on("click", function() {
				$("#form").attr("action", "/board/remove");
				$("#form").attr("method", "get");
				$("#form").submit();
			});
			$("#listall").on("click", function() {
				location.href = "/board/listAll?page=${cri.page}&perPageNum=${cri.perPageNum}";
			});
		});
	/*
		val form = document.getElementById("form");
		
		form.action = "/board/modify";
		form.method = "get";
		form.submit();
	*/
	</script>
</head>
<body>
	<form name="form" id="form">
		<input type="hidden" name="bno" value="${boardVO.bno}">
		<input type="hidden" name="page" value="${cri.page}">
		<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
		<input type="hidden" name="searchType" value="${cri.searchType}">
		<input type="hidden" name="keyword" value="${cri.keyword}">
	</form>
	<div class="box-body">
	<h3>게시글 조회</h3>
		<table class="table table-striped">
		<tr>
			<th>Title</th>
		</tr>
		<tr>
			<td>
				<input type="text" name='title' value="${boardVO.title}" readonly="readonly" class="form-control">
			</td>
		</tr>
		<tr>
			<th>Content</th>
		</tr>
		<tr>
			<td>
				<textarea rows="3" name='content' readonly="readonly" class="form-control">${boardVO.content}</textarea>
			</td>
		</tr>
		<tr>
			<td>Writer</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="writer" value="${boardVO.writer}" readonly="readonly" class="form-control">
			</td>
		</tr>
	</table>
	<div class="box-footer">
		<button type="submit" name="modify" id="modify" class="btn btn-warning">수정</button>
		<button type="submit" name="remove" id="remove" class="btn btn-danger">삭제</button>
		<button type="submit" name="listall" id="listall" class="btn btn-primary">목록</button>
	</div>
</div>
</body>
</html>