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

	<!-- index.jsp -->

	<main>
		<h1>REST Client Page</h1>
		
		<form id="form1">
		<table border width="300">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="tel" name="tel"></td>
			</tr>
		</table>
		</form>
		<input type="button" value="추가하기" id="btn1">

	</main>

	<!-- JQuery Script -->
	<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>
	
	<script>
		$("#btn1").click(()=>{
			
			$.ajax({
				type: 'POST',
				url: '/address',
				data: $('#form1').serialize(),
				dataType: 'json',
				success: function(result){
					alert(result);
				},
				error: function(a, b, c) {
					console.log(a, b, c);
				}
			});
			
		});	
	</script>
	
</body>
</html>





