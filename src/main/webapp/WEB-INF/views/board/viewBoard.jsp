<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="/js/jquery-1.12.4.js"></script>
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/design/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<script>
	$(function() {
		$(".goDetail").on("click", function() {
			$("#posts_id").val($(this).data("posts_id"));
			$("#frm").submit();
		});
	});
</script>
<body>
	<form id="frm" action="/posts/postsDetail" method="get">
		<input type="hidden" name="posts_id" id="posts_id"> 
<%-- 		<input type="hidden" name="boardVo" id="board" value="${board }"> 왜 VO자체를 못넘기나요??--%>
		<input type="hidden" name="board_id" id="board" value="${board.board_id }">
		<input type="hidden" name="board_title" id="board" value="${board.board_title }">
	</form>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${board.board_title }</h2>
						<div class="table-responsive">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>게시글번호</th>
										<th>제목</th>
										<th>작성자 아이디</th>
										<th>작성일시</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${postsList}" var="vo">
										<c:choose>
											<c:when test="${vo.posts_status == 'Y'}">
												<tr data-posts_id="${vo.posts_id }" class="goDetail">
													<td>${vo.posts_id}</td>
													<td>${vo.posts_title}</td>
													<td>${vo.std_id}</td>
													<td><fmt:formatDate value="${vo.posts_date}"
															pattern="yyyy-MM-dd" /></td>
												<tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="4">삭제된 게시글입니다.</td>
												<tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="text-center">
							<ul class="pagination">
								<%=request.getAttribute("pageNavi")%>
							</ul>
						</div>
						<a class="btn btn-default pull-right"
							href="/posts/postsWrite?board_title=${board.board_title }&board_id=${board.board_id }">새글쓰기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
