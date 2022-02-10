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
	<!-- madd.jsp -->
	<main>
		<h1>글쓰기 <small>파일업로드(N개)</small></h1>
		
		<!-- 파일 업로드 테이블 -->
		<form method="POST" action="/file/maddok.do" enctype="multipart/form-data">
		<table border width="500">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" required></td>
			</tr>
		</table>
		<hr>
		
		<!-- 첨부파일 테이블 -->
		<table border width="500" id="filetable">
			<tr>
				<th>파일</th>
				<td><input type="file" name="attach1"></td>
			</tr>
		</table>
		
		<!-- 첨부파일 추가하기btn -->
		<div>
			<input type="button" value="첨부파일 추가하기" id="btn">
		</div>
		
		<!-- 글쓰기btn -->
		<div>
			<input type="submit" value="글쓰기">
		</div>
		</form>
		
	</main>


	
	<script>
		let filetable = document.getElementById("filetable");
		let btn = document.getElementById("btn");
		let n = 2;
		
		btn.onclick = function(){
			let temp = '<tr><th>파일</th><td><input type="file" name="attach' + n + '"></td></tr>';
			filetable.children[0].innerHTML += temp;
			n++;
		};
		
	</script>
</body>
</html>





