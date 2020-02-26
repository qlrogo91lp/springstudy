<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/bootstrap.css">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#save").on("click", function() {
				$("#form").attr("action", "/board/modify");
				$("#form").attr("method", "post");
				$("#form").submit();
			});
			$("#cancel").on("click", function() {
				location.href = "/board/listAll?page=${cri.page}&perPageNum=${cri.perPageNum}"
								+ "&searchType=${cri.searchType&keyword=${cri.keyword}}";
			});
		});
	</script>
</head>
<body>
<form>
	<input type="hidden" id="page" name="page" value="${cri.page}">
	<input type="hidden" id="perPageNum" name="perPageNum" value="${cri.perPageNum}">
	<input type="hidden" name="searchType" value="${cri.searchType}">
	<input type="hidden" name="keyword" value="${cri.keyword}">
</form>
<div class="box-body">
	<form name="form" id="form">
		<table class="table table-striped">
			<tr>
				<th>BNO</th>
			</tr>
			<tr>
				<td>
					<input type="text" name="bno" value="${boardVO.bno}" readonly="readonly" class="form-control">
				</td> 
			</tr>
			<tr>
				<th>Title</th>
			</tr>
			<tr>
				<td>
					<input type="text" name='title' value="${boardVO.title}" class="form-control">
				</td>
			</tr>
			<tr>
				<th>Content</th>
			</tr>
			<tr>
				<td>
					<textarea rows="3" name='content' class="form-control">${boardVO.content}</textarea>
				</td>
			</tr>
			<tr>
				<td>Writer</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="writer" value="${boardVO.writer}" class="form-control">
				</td>
			</tr>
		</table>
	</form>
	<div class="box-footer">
		<button type="submit" id="save" class="btn btn-success">SAVE</button>
		<button type="submit" id="cancel" class="btn btn-warning">CANCEL</button>
	</div>
</div>
</body>
</html>