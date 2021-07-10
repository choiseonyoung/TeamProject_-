<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<form class="write_form" method=POST>
	<h2 class="board_title">글쓰기</h2>
		<fieldset>
			<div class="write_top">
			<c:choose>
				<c:when test="${MENU == 'TIP'}">
					<select>
						<option>말머리</option>
						<option>청소&amp;세탁</option>
						<option>요리</option>
						<option>공간활용</option>
						<option>기타</option>
					</select>
				</c:when>
				<c:when test="${MENU == 'TALK'}">
					<select>
						<option>말머리</option>
						<option>정보 TALK</option>
						<option>자유 TALK</option>
					</select>
				</c:when>
				<c:when test="${MENU == 'REVIEW'}">
					<select>
						<option>말머리</option>
						<option>생활용품</option>
						<option>음식점</option>
						<option>기타</option>
					</select>
				</c:when>
			</c:choose>
				<input name="content_title" class="write_title" /><br />
				<div class="file_box">
					<label for="ex_file">&#128190;</label> <input type="file" id="ex_file" />
				</div>
			</div>
			<textarea name="content_text" class="write_text"></textarea>
			<input name="member_num" type="hidden" value="${W.member_num}"/>
			<input name="member_nname" type="hidden" value="${W.member_nname}"/>
			<input name="board_code" type="hidden" value="${W.board_code}"/>
			<input name="content_date" type="hidden" value="${W.content_date}"/>
			<input name="content_time" type="hidden" value="${W.content_time}"/>
			<input name="content_view" type="hidden" value="${W.content_view}"/>
			<input name="content_good" type="hidden" value="${W.content_good}"/>
		</fieldset>
		<div class="write_bottom">
			<button id="btn_write" type="submit">글쓰기</button>
			<button type="reset">취소</button>
		</div>
</form>