<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="board" type="kr.or.ddit.model.BoardVo"
	required="false"%>
<%@ attribute name="buttonType" type="java.lang.String" required="true"%>

<form action="/board/insertBoard" method="get">
	게시판 이름 <input type="text" name="board_title" value=${board.board_title }>
	<select name="board_status">
		<option value="Y" <c:if test="${board.board_status == 'Y'}">selected</c:if>>사용</option>
		<option value="N" <c:if test="${board.board_status == 'N'}">selected</c:if>>미사용</option>
	</select>
	<button type="submit" class="btn btn-default">${buttonType }</button>
	<input type="hidden" name="buttonType" value="${buttonType }">
	<input type="hidden" name="board_id" value="${empty board.board_id ? 0 : board.board_id }">
</form>
