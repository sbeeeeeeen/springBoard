<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" tagdir="/WEB-INF/tags"%>
<meta charset="UTF-8">
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div class="blog-header">게시판 생성</div>
	<div class="row">
		<s:useSelect buttonType="insert" />
		<br>
		<c:forEach items="${boardList }" var="board">
			<s:useSelect board="${board }" buttonType="update" />
		</c:forEach>
	</div>
</div>
</body>
</html>
