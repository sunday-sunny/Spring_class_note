<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Document</title>
   <style>
   
   </style>
</head>
<body>
	<!-- list.jsp -->
	<main>
		<h1>게시판</h1>
		
		<table border width="500">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>파일</th>
			</tr>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.seq}</td>
				<td>${dto.subject}</td>
				<td>
					<!-- 파일 다운로드 링크, origin이름과 현재파일 이름을 같이 준다  -->
					<a href="/file/download.do?orgfilename=${dto.orgfilename}&filename=${dto.filename}">
					${dto.orgfilename}
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<hr>
		
		<!-- 글쓰기btn -->
		<div>
			<input type="button" value="글쓰기" onclick="location.href='/file/add.do';">
		</div>

	</main>

	<script>
   
   </script>
</body>
</html>





