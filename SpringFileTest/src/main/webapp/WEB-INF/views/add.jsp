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
	<!-- add.jsp -->
	<main>
		<h1>글쓰기 <small>파일업로드(1개)</small></h1>
		
		<!-- 파일 업로드 테이블 -->
		<!-- *** enctype="multipart/form-data" 설정 꼭 ! *** -->
		<form method="POST" action="/file/addok.do" enctype="multipart/form-data">
		<table border width="500">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" required></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="attach"></td>
			</tr>
		</table>
		
		<!-- 글쓰기btn -->
		<div>
			<input type="submit" value="글쓰기">
		</div>
		</form>
	</main>


	<script>
		
	</script>
</body>
</html>





