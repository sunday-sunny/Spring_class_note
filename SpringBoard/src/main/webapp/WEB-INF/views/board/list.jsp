<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
	.list th, .list td { text-align: center;}
	.list th:nth-child(1) {width: 50px;}
	.list th:nth-child(2) {width: auto;}
	.list th:nth-child(3) {width: 70px;}
	.list th:nth-child(4) {width: 110px;}
	.list td:nth-child(2) { text-align: left;}

</style>

<!-- 글목록 테이블 -->
<table class="table table-bordered list">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>날짜</th>	
	</tr>
	
	<c:forEach items="${list}" var="dto">
	<tr>
		<td>${dto.seq}</td>
		<td>
			<a href="/view?seq=${dto.seq}">${dto.subject}</a>
			<c:if test="${not empty dto.filename}">
			<span>💾</span>
			</c:if>
		</td>
		<td>${dto.name}</td>
		<td>
			<fmt:parseDate value="${dto.regdate}" var="test" pattern="yyyy-MM-dd"></fmt:parseDate>
			<fmt:formatDate value="${test}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	</c:forEach>
</table>

<!-- Btn  -->
<div class="btns">
	<input type="button" value="작성하기" class="btn btn-default" onclick="location.href='/add';">
</div>
