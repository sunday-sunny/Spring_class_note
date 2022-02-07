<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<%@ include file="/inc/asset.jsp"%>
<style>
	.container {
		width: 800px;
	}
</style>
</head>
<body>
	<!-- add.jsp -->
	<div class="container">

		<%@ include file="/inc/header.jsp"%>

		<!-- 메모쓰기 테이블 -->
		<form method="POST" action="/memo/addok.do">
			<table class="table table-bordered">
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" class="form-control" required></td>
				</tr>
				<tr>
					<th>메모</th>
					<td><textarea name="memo" class="form-control" required style="height: 200px;"></textarea></td>
				</tr>
			</table>
			<hr>

			<!-- Buttons -->
			<div>
				<input type="button" value="돌아가기" class="btn btn-default" onclick="location.href='/memo/list.do';"> 
				<input type="submit" value="메모쓰기" class="btn btn-default">
			</div>
		</form>


	</div>

	<script>
		
	</script>
</body>
</html>