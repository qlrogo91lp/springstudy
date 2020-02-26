<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listAll</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/bootstrap.css">
	<style type="text/css">
		a {
			margin-right: 3px;
			margin-left: 3px;
		}
	</style>
	<script>
		var result = "${msg}";
		
		if(result == "등록" || result == "수정" || result == "삭제") {
			alert(result + "이 완료되었습니다.");
		} else {
			console.log(result);
		}
		
		$(document).ready(function() {
			$("#searchBtn").on("click", function(event) {
				location.href = "/board/listAll${pageMaker.makeQuery(1)}"
							  + "&searchType=" + $("select option:selected").val()
							  + "&keyword=" + $("#keywordInput").val(); 
			});
		});
	</script>
</head>
<body>
<div class="container">
	<div class="box-body">
		<select name="searchType">
			<option value="n"><!-- 검색 조건이 없음 -->
				<c:out value="${cri.searchType eq null? '---' : ''}"/>
			</option>
			<option value="t">
				<c:out value="${cri.searchType eq 't' ? 'selected':''}"/>제목
			</option>
			<option value="c">
				<c:out value="${cri.searchType eq 'c' ? 'selected':''}"/>내용
			</option>
			<option value="w">
				<c:out value="${cri.searchType eq 'w' ? 'selected':''}"/>작성자
			</option>
			<option value="tc">
				<c:out value="${cri.searchType eq 'tc' ? 'selected':''}"/>제목 or 내용
			</option>
			<option value="cw">
				<c:out value="${cri.searchType eq 'cw' ? 'selected':''}"/>내용 or 작성자
			</option>
			<option value="tcw">
				<c:out value="${cri.searchType eq 'tcw' ? 'selected':''}"/>제목 or 내용 or 작성자
			</option>
		</select>
		<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}"/>
		<button id="searchBtn" class="btn btn-warning">검색</button>
		<a href="/board/register" class="btn btn-success">글쓰기</a>
	</div>
	<h3 class="box-title">게시판</h3>
	<table class="table table-striped table-hover">
		<tr>
			<th>BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th>VIEWCNT</th>
		</tr>
		<c:forEach items="${list}" var="boardVO">
			<tr>
				<td>${boardVO.bno}</td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
				<td><a href="/board/read${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVO.bno}">${boardVO.title}</a></td>
				<td>${boardVO.writer}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
				<td><span>${boardVO.viewcnt}</span></td>
			</tr>
		</c:forEach>
	</table>
<!-- ------------------------------------ 페이징 ------------------------------------ -->	
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
				<li><a href="/board/listAll${pageMaker.makeSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="index">
				<c:choose>
					<c:when test="${index == 0}">
					</c:when>
					<c:when test="${pageMaker.cri.page == index}">
						<li>
							<span style="font-weight: bold; background-color: lightgrey;">
								<a href="/board/listAll${pageMaker.makeSearch(index)}">${index}</a>
							</span>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="/board/listAll${pageMaker.makeSearch(index)}">${index}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="/board/listAll${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
</div>
</body>
</html>